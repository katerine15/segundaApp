package com.adso.segunadaapp.todoapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.adso.segunadaapp.R

class TasksAdapter(var tasks:List<Task>, private val onTaskSelected:(Int) -> Unit): // onTaskSelected -> invocará cuando se seleccione una tarea
    //La clase TasksAdapter hereda de RecyclerView.Adapter
    RecyclerView.Adapter<TasksViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TasksViewHolder {
        //vista
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_todo_task, parent, false)
        return TasksViewHolder(view)
    }

    //devuelve el número de elementos en la lista de tareas
    override fun getItemCount() =tasks.size //tamaño de esa lista -> size

    //onBindViewHolder -> vincula los datos de una tarea específica a un ViewHolder existente
    override fun onBindViewHolder(holder: TasksViewHolder, position: Int) {
        holder.render(tasks[position]) // actualiza la vista con los datos de la tarea en la posición dada
        holder.itemView.setOnClickListener { onTaskSelected(position) } //onTaskSelected -> para cuando se selecciona una tarea en la lista.
    }
}