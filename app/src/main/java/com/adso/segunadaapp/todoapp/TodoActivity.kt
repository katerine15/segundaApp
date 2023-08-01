package com.adso.segunadaapp.todoapp

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.RecycledViewPool
import com.adso.segunadaapp.R
import com.adso.segunadaapp.todoapp.TaskCategory.*
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.text.FieldPosition
import java.util.TimerTask

class TodoActivity : AppCompatActivity() {

    // es una lista inmutable de categorias de tareas
    private val categories = listOf(
        Business,
        Personal,
        Other
    )

    //sub lista
    // es una lista mutable de tareas y se inicializa con 3
    private val tasks = mutableListOf(
        Task("PruebasBusiness", Business),
        Task("PruebasPersonal", Personal),
        Task("PruebasOther", Other)
    )

    /* Declaracion de variables*/

    private lateinit var rvCategories: RecyclerView
    private lateinit var categoriesAdapter: CategoriesAdapter

    //subcategoria
    private lateinit var rvTasks: RecyclerView
    private lateinit var tasksAdapter: TasksAdapter

    //boton para agregar tareas
    private lateinit var fabAddTask: FloatingActionButton

    //inicializa la actividad
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_todo) //establecer diseño
        initComponent() // inicializa los componentes
        initUI() //configura la inferz
        initListeners() //establece los listner
    }

    //configura un listener para el botón de agregar tareas -> fabAddTask
    private fun initListeners() {
        // que va a pasar cuando se seleccione el boton
        fabAddTask.setOnClickListener { showDialog() }
        // Cuando se hace clic en el botón, se llama al método showDialog
    }

    // dialogo
    //crea y muestra un diálogo personalizado que permite ingresar una nueva tarea
    private fun showDialog(){
        val dialog = Dialog(this)
        dialog.setContentView(R.layout.dialog_task)

        //referencias a elemntos de la vista del dialogo
        val btnAddTask: Button = dialog.findViewById(R.id.btnAddTask)
        val etTask: EditText = dialog.findViewById(R.id.etTask)
        val rgCategories : RadioGroup = dialog.findViewById(R.id.rgCategories)

        btnAddTask.setOnClickListener{
            //obtner el texto de la tarea ingresada
            val currentTask = etTask.text.toString()
            if (currentTask.isNotEmpty()){
                //obtner la categoria seleccionada
                val selectedId = rgCategories.checkedRadioButtonId
                val selectedRadioButton:RadioButton = rgCategories.findViewById(selectedId)
                val currentCategory:TaskCategory = when(selectedRadioButton.text){
                    getString(R.string.todo_dialog_category_business) -> Business
                    getString(R.string.todo_dialog_category_personal) -> Personal
                    else -> Other
                }
                //agrega una nueva tarea a lista
                // se recupera el texto y se convierte en str
                tasks.add(Task(currentTask, currentCategory))
                dialog.hide() //para que se cierre
                updateTasks() //Actualizar la lista de tareas
            }
        }
        dialog.show() //mostrar el dialog
    }

    private fun initComponent(){
        //referencia a las vistas del diseño la actividad
        rvCategories = findViewById(R.id.rvCategories)
        rvTasks = findViewById(R.id.rvTasks)
        fabAddTask = findViewById(R.id.fabAddTask)
    }
    private fun initUI(){
        //Configuración del adaptador de categorías y su vista correspondiente (RecyclerView)
        categoriesAdapter = CategoriesAdapter(categories){
            position -> updateCategories(position)
        }
        //se encarga de determinar si la vista es horizontal o vertical
        rvCategories.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        rvCategories.adapter = categoriesAdapter

        //sub
        //// Configuración del adaptador de tareas y su vista correspondiente (RecyclerView)
        tasksAdapter = TasksAdapter(tasks){position -> onlIntemSelected(position)}
        rvTasks.layoutManager = LinearLayoutManager(this) // vertical -> viene por defecto
        rvTasks.adapter = tasksAdapter
    }
    private fun onlIntemSelected(position:Int){
        //Cambia el estado de selección de la tarea en la posición dada
        tasks[position].isSelected = !tasks[position].isSelected
        updateTasks() //Actualiza la lista de tareas
    }
    private fun updateCategories(position: Int){
        categories[position].isSelected = !categories[position].isSelected
        categoriesAdapter.notifyItemChanged(position)
        updateTasks()
    }
    private fun updateTasks(){
        //Filtrar las tareas según las categorías seleccionadas
        val selectedCategories: List<TaskCategory> = categories.filter { it.isSelected }
        val newTask = tasks.filter { selectedCategories.contains(it.category) }
        tasksAdapter.tasks = newTask
        tasksAdapter.notifyDataSetChanged()
    }


}