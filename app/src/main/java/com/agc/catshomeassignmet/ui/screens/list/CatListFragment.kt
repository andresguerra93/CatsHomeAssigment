package com.agc.catshomeassignmet.ui.screens.list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.LinearLayoutManager
import com.agc.catshomeassignmet.databinding.FragmentCatListBinding
import com.agc.catshomeassignmet.ui.screens.list.recyclerview.CatListAdapter
import dagger.hilt.android.AndroidEntryPoint
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.agc.catshomeassignmet.R
import com.agc.catshomeassignmet.ui.screens.detail.CatDetailFragment
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CatListFragment : Fragment() {

    private val viewModel: CatListViewModel by viewModels()
    private lateinit var catListAdapter: CatListAdapter
    private var _binding: FragmentCatListBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCatListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
        viewModel.loadMoreCatsFromApi()
        viewModel.loadCatsFromRoom(activity!!.assets)
    }

    private fun initUI() {
        initList()
        initUIState()
    }

    private fun initList() {
        catListAdapter = CatListAdapter(
            catList = viewModel.cats.value.toMutableList(),
            onItemSelected = {
                Log.d("MiTag", "One cat was selected with id: $it")
                val bundle = Bundle()
                bundle.putString("catId", it)

                val catDetailFragment = CatDetailFragment()
                catDetailFragment.arguments = bundle
                findNavController().navigate(
                    R.id.action_catListFragment_to_catDetailFragment,
                    bundle
                )

                //------
                //val action = CatListFragmentDirections.actionCatListFragmentToCatDetailFragment(it)
                // findNavController().navigate(action)
            })
        binding.rvCats.setHasFixedSize(true)
        binding.rvCats.layoutManager = LinearLayoutManager(context)
        binding.rvCats.adapter = catListAdapter


        binding.rvCats.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                if (dy > -1) {
                    val layoutManager = recyclerView.layoutManager as LinearLayoutManager
                    val visibleItemCount = layoutManager.childCount
                    val totalItemCount = layoutManager.itemCount
                    val firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition()

                    if (visibleItemCount + firstVisibleItemPosition >= totalItemCount) {
                        viewModel.loadMoreCatsFromApi()
                        catListAdapter.notifyItemRangeInserted(totalItemCount, totalItemCount + 10)
                    }
                }
            }
        })

    }

    private fun initUIState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {

                viewModel.cats.collect {
                    catListAdapter.updateList(it.toMutableList())

                }
            }
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}


