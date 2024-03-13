package com.agc.catshomeassignmet.ui.screens.list.recyclerview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.agc.catshomeassignmet.R
import com.agc.catshomeassignmet.domain.model.Cat

class CatListAdapter (private var catList: List<Cat> = emptyList(),
                      private val onItemSelected:(String) -> Unit) :
    RecyclerView.Adapter<CatListViewHolder>() {

    fun updateList(list:List<Cat>){
        catList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CatListViewHolder {
        return CatListViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_cat, parent,false)
        )
    }

    override fun getItemCount() = catList.size

    override fun onBindViewHolder(holder: CatListViewHolder, position: Int) {
        holder.bind(catList[position], onItemSelected)

    }
}