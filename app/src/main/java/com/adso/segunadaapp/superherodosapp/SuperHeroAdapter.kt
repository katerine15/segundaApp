package com.adso.segunadaapp.superherodosapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.adapters.AdapterViewBindingAdapter.OnItemSelected
import androidx.recyclerview.widget.RecyclerView
import com.adso.segunadaapp.R

class SuperHeroAdapter(
    var superheroList: List<SuperHeroItemResponse> = emptyList(),
    private val onItemSelected: (String) -> Unit
) :
    RecyclerView.Adapter<SuperHeroViewHolder>() {

    fun updateList(list: List<SuperHeroItemResponse>) {
        superheroList = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuperHeroViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        return SuperHeroViewHolder(layoutInflater.inflate(R.layout.item_superhero, parent, false))
    }

    override fun getItemCount() = superheroList.size

    override fun onBindViewHolder(vieewholder: SuperHeroViewHolder, position: Int) {
        vieewholder.bind(superheroList[position], onItemSelected)
    }

}