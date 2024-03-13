package com.agc.catshomeassignmet.ui.screens.detail


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.agc.catshomeassignmet.BuildConfig
import com.agc.catshomeassignmet.databinding.FragmentCatDetailBinding
import com.agc.catshomeassignmet.domain.model.Cat
import com.agc.catshomeassignmet.ui.screens.MainActivity
import com.agc.catshomeassignmet.ui.screens.utils.builImageUrl
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class CatDetailFragment : Fragment() {

    //private val args: CatDetailFragmentArgs by navArgs()

    private val viewModel: CatDetailViewModel by viewModels()
    private var _binding: FragmentCatDetailBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCatDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val catId = arguments?.getString("catId", "") ?: ""
        viewModel.getCatDetail(catId)
        initUI()
    }
    private fun initUI() {
        initList()
        initUIState()
    }

        private fun initList() {
           /* binding.btnToggleTheme.setOnClickListener {

                findNavController().popBackStack()
            }*/
            

        }
    private fun initUIState() {
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.state.collect { state ->
                    when (state) {
                        is CatDetailState.Loading -> showLoading()
                        is CatDetailState.Success -> showCat(state.cat)
                        is CatDetailState.Error -> showError(state.errorMessage)
                    }
                }
            }
        }
    }

    private fun showLoading() {
        binding.progressBar.visibility = View.VISIBLE
    }

    private fun hideLoading() {
        binding.progressBar.visibility = View.GONE
    }

    private fun showCat(cat: Cat) {

        val size = "Size: ${cat.size}"
        val id = "ID: ${cat.id}"
        val type = "Type: ${cat.mimeType}"
        val createdAt = "Created ${cat.createdAt}"
        val updatedAt = "Updated ${cat.updatedAt}"

        binding.progressBar.visibility = View.VISIBLE
        val imageUrl:String = builImageUrl(BuildConfig.BASE_URL_IMAGE, id=cat.id, type = "square", width = "400" )
        Picasso.get()
            .load(imageUrl)
            .into(binding.ivCat, object : Callback {
                override fun onSuccess() {
                    binding.progressBar.visibility = View.GONE
                }

                override fun onError(e: Exception?) {
                    binding.progressBar.visibility = View.GONE
                    Toast.makeText(getActivity(), "An error occurred when The Image was Chargin", Toast.LENGTH_SHORT).show()
                }
            })

        binding.tvCatOwner.text = cat.owner ?: "Unknown owner"
        binding.tvTags.text = cat.tags.joinToString(", ")
        binding.tvId.text = id
        binding.tvSize.text = size
        binding.tvType.text = type
        binding.tvCreateDate.text = createdAt
        binding.tvUpdateDate.text = updatedAt
        hideLoading()
    }

    private fun showError(errorMessage: String) {
        Toast.makeText(getActivity(), "An error occurred when CatDetail Was called", Toast.LENGTH_SHORT).show()
        binding.progressBar.visibility = View.GONE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
