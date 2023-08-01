package com.adso.segunadaapp.imccalculator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.adso.segunadaapp.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.slider.RangeSlider
import java.text.DecimalFormat

class ImcCalculatorActivity : AppCompatActivity() {
    // se declaran variables, que almacena informacion necesaria para el calculo del IMC

    /*Variable global*/
    // seguimiento del genero
    private var isMaleSelected: Boolean = true // en true nos indica que el género masculino está seleccionado como valor predeterminado
    private var isFemaleSelected: Boolean = false

    /* variable para controlar los valores en el peso*/
    private var currentWeight: Int = 50

    /*variable para controlar los valores de los años*/
    private var currentAge: Int = 20

    //altura
    private var currentHeight: Int = 120

    /* lateinit -> se inicia pero cuando se le diga */
    // Al utilizar lateinit, se evita tener que asignar un valor inicial a estas variables en el momento de su declaración.
    private lateinit var viewMale: CardView
    private lateinit var viewFemale: CardView
    private lateinit var tvHeight: TextView
    private lateinit var rsHeight: RangeSlider

    //para el peso
    private lateinit var btnSubtractWeight: FloatingActionButton
    private lateinit var btnPlusWeight: FloatingActionButton
    private lateinit var tvWeight: TextView

    //para los años
    private lateinit var tvAge: TextView
    private lateinit var btnSubtractAge: FloatingActionButton
    private lateinit var btnPlusAge: FloatingActionButton

    //para el boton calculate
    private lateinit var btnCalculate:Button

    companion object{
        const val IMC_KEY = "IMC_RESULT"
    }

    // onCreate -> se llama cuando se crea la actividad
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_imc_calculator) /*crea la interfaz*/
        initComponents() /*llama a los componentes*/
        initListeners() // dectecta a cual dde las card se le dio click
        initUI()
    }

    // initComponents -> se inicializa los diferentes componentes de la interfaz
    private fun initComponents() {
        // se inicializa el componente
        viewMale = findViewById(R.id.viewMale)
        viewFemale = findViewById(R.id.viewFemale)
        tvHeight = findViewById(R.id.tvHeight)
        rsHeight = findViewById(R.id.rsHeight)
        //para el peso
        btnSubtractWeight = findViewById(R.id.btnSubtractWeight)
        btnPlusWeight = findViewById(R.id.btnPlusWeight)
        tvWeight = findViewById(R.id.tvWeight)
        //para los años
        tvAge = findViewById(R.id.tvAge)
        btnSubtractAge = findViewById(R.id.btnSubtractAge)
        btnPlusAge = findViewById(R.id.btnPlusAge)
        // para el boton calcular
        btnCalculate = findViewById(R.id.btnCalculate)
    }

    // initListeners -> para los diferentes componentes de la interfaz
    private fun initListeners() {
        viewMale.setOnClickListener {
            changeGender() // se llama este metodo para cambiar el genero
            setGenderColor() // para actualizar el color de fondo
        }
        viewFemale.setOnClickListener {
            changeGender()
            setGenderColor()
        }
        rsHeight.addOnChangeListener { _, value, _ ->
            val df = DecimalFormat("#.##") //permite quitar el campo decimal
            currentHeight = df.format(value).toInt()
            tvHeight.text = "$currentHeight cm" //para que de el valor como entero
        }
        //para el peso
        btnPlusWeight.setOnClickListener {
            //para sumar
            currentWeight += 1
            //llamar metodo el cual va ajustar el peso
            setWeight()
        }
        btnSubtractWeight.setOnClickListener {
            //para restar
            currentWeight -= 1
            setWeight()
        }
        /*para los años*/
        btnSubtractAge.setOnClickListener {
            currentAge -= 1
            setAge()
        }
        btnPlusAge.setOnClickListener {
            currentAge += 1
            setAge()
        }
        // boton calcular
        btnCalculate.setOnClickListener {
            val result = calculateIMC()
            navigateToResult(result)
        }
    }
    // navigateToResult -> crea un intent para abrir una nueva actividad
    private fun navigateToResult(result: Double) {
        val intent = Intent(this, ResultIMCActivity::class.java)
        intent.putExtra(IMC_KEY, result)
        startActivity(intent) // se inicia la actividad
    }

    //BOTON CALCULAR
    private fun calculateIMC(): Double {
        // realizacion del calculo imc
        // Se utiliza toDouble para convertir los valores de peso y altura a este tipo y que admita decimales
        val df = DecimalFormat("#,##")
        val imc = currentWeight/(currentHeight.toDouble()/100 * currentHeight.toDouble()/100)
        return df.format(imc).toDouble()
        //Toast.makeText(this,"el IMC es $result", Toast.LENGTH_SHORT).show()
    }

    /* AÑOS*/
    private fun setAge() {
        tvAge.text = currentAge.toString()
    }

    private fun setWeight() {
        tvWeight.text = currentWeight.toString()
    }
    // cambiar genero seleccionado
    private fun changeGender() { // invierte los valores
        isMaleSelected = !isMaleSelected
        isFemaleSelected = !isFemaleSelected
    }

    /* para que cuando se de click, actualice el color -> relacionado a la card*/
    private fun setGenderColor() {
        getBackgroundColor(isMaleSelected)
        getBackgroundColor(isFemaleSelected)
        // getBackgroundColor ->  para obtener el color correspondiente según si el componente está seleccionado o no
        viewMale.setCardBackgroundColor(getBackgroundColor(isMaleSelected))
        // se estable el color utilizando el -> setCardBackgroundColor
        viewFemale.setCardBackgroundColor(getBackgroundColor(isFemaleSelected))
    }

    private fun getBackgroundColor(isSelectedComponent: Boolean): Int {
        val colorReference = if (isSelectedComponent) { // -> para determinar qué color retorna
            R.color.background_component_selected
        } else {
            R.color.background_component
        }
        return ContextCompat.getColor(this, colorReference)
    }

    private fun initUI() { //valores iniciales
        setGenderColor()
        setWeight() //para que inicie al momento
        setAge()
    }
}