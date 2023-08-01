package com.adso.segunadaapp.todoapp

data class Task (val name:String, val category: TaskCategory, var isSelected:Boolean = false)

//efine un modelo de datos para representar una tarea en la aplicación
// Almacena el nombre de la tarea, su categoría y su estado de selección.