package com.agc.catshomeassignmet.ui.screens.list.recyclerview

import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.agc.catshomeassignmet.BuildConfig.BASE_URL_IMAGE
import com.agc.catshomeassignmet.databinding.ItemCatBinding
import com.agc.catshomeassignmet.domain.model.Cat
import com.agc.catshomeassignmet.ui.screens.utils.builImageUrl
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso

class CatListViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    private val binding = ItemCatBinding.bind(view)

    fun bind(cat: Cat, onItemSelected: (String) -> Unit) {

        binding.tvTag.text = if(cat.tags.isEmpty()){"Without Tags"}else{cat.tags[0]}
        val imageUrl:String = builImageUrl(BASE_URL_IMAGE, id=cat.id, type = "square", width = "60" )
        binding.progressBar.visibility = View.VISIBLE
        Picasso.get()
            .load(imageUrl)
            .into(binding.ivCat,  object : Callback {
                override fun onSuccess() {
                    binding.progressBar.visibility = View.GONE
                }

                override fun onError(e: Exception?) {

                    binding.progressBar.visibility = View.GONE
                  //  Toast.makeText(getActivity(), "An error occurred when The Image was Chargin", Toast.LENGTH_SHORT).show()
                }
            })
        binding.root.setOnClickListener { onItemSelected(cat.id) }
    }
}