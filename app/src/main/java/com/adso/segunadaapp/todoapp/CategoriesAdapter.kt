package com.adso.segunadaapp.todoapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.adso.segunadaapp.R

/*El adapter permite mostrar las listas*/
class CategoriesAdapter (private val categories:List<TaskCategory>, private val onItemSelected:(Int) -> Unit):
    RecyclerView.Adapter<CategoriesViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_task_categories, parent, false)
        return CategoriesViewHolder(view)
    }

    //muestra el tamaño de la lista
    override fun getItemCount(): Int {
        return categories.size //muestra la cantidad que se encuentre en aquella lista
    }

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        holder.render(categories[position], onItemSelected)
    }
}