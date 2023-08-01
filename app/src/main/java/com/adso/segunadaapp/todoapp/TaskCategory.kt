package com.adso.segunadaapp.todoapp

sealed class TaskCategory (var isSelected: Boolean = true){
    object Personal: TaskCategory()
    object Business: TaskCategory()
    object Other: TaskCategory()
}
//solamente coloca las que estan ahi
//List<TaskCategory>