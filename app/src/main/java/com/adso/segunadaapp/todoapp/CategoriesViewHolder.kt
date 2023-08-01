package com.adso.segunadaapp.todoapp

import android.view.View
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.adso.segunadaapp.R

class CategoriesViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    //propiedades de la vista
    private val tvCategoryName: TextView = view.findViewById(R.id.tvCategoryName)//utilizará para mostrar el nombre de la categoría
    private val divider: View = view.findViewById(R.id.divider) //divisor visual entre elementos de categoría
    private val viewContainer: CardView = view.findViewById(R.id.viewContainer) //contiene los elementos de la categoría y permite aplicar un fondo y otros estilos

    //
    fun render(taskCategory: TaskCategory, onItemSelected: (Int) -> Unit) // se utiliza para renderizar los datos de una categoría específica en la vista del elemento de categoría
    {
        val color = if (taskCategory.isSelected) { //determinan el color de fondo
            R.color.todo_bacground_card
        } else {
            R.color.todo_bacground_disable
        }

        //setCardBackgroundColor -> para establecer el color de fondo del CardView
        // pasando como argumento el color obtenido utilizando -> ContextCompat.getColor().
        viewContainer.setCardBackgroundColor(ContextCompat.getColor(viewContainer.context,color))

        //Cuando se hace clic en el elemento, se invoca la función
        itemView.setOnClickListener { onItemSelected(layoutPosition) }

        //para determinar el nombre de la categoría y el color del divisor
        when (taskCategory) {
            TaskCategory.Business -> {
                //se establece el texto
                tvCategoryName.text = "Negocios"
                //colo division utilizado
                divider.setBackgroundColor(
                    ContextCompat.getColor(divider.context, R.color.todo_business_category)
                )
            }
            TaskCategory.Personal -> {
                tvCategoryName.text = "Personal"
                divider.setBackgroundColor(
                    ContextCompat.getColor(divider.context, R.color.todo_personal_category)
                )
            }
            TaskCategory.Other -> {
                tvCategoryName.text = "Otros"
                divider.setBackgroundColor(
                    ContextCompat.getColor(divider.context, R.color.todo_other_category)
                )
            }
        }
    }
}