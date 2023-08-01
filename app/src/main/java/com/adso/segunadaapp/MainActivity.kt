package com.adso.segunadaapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.adso.segunadaapp.agedogapp.AgeDogActivity
import com.adso.segunadaapp.imccalculator.ImcCalculatorActivity
import com.adso.segunadaapp.superheroapp.SuperHeroActivity
import com.adso.segunadaapp.superherodosapp.SuperHeroListActivity
import com.adso.segunadaapp.todoapp.TodoActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //contenido de la actividad
        setContentView(R.layout.activity_main)

        /*relación con el boton*/
        val btnIMCApp = findViewById<Button>(R.id.btnIMCApp)
        val btnToDoApp = findViewById<Button>(R.id.btnToDoApp)
        val btnAgeDogApp = findViewById<Button>(R.id.btnAgeDogApp)
        val btnSuperHeroApp = findViewById<Button>(R.id.btnSuperHeroApp)
        val btnSuperHeroDosApp = findViewById<Button>(R.id.btnSuperHeroDosApp)

        /*Instrucciones de lo que realizara cuando se le de click*/
        btnIMCApp.setOnClickListener { navigateToIMCAPP() }
        btnToDoApp.setOnClickListener { navigateToToDoAPP() }
        btnAgeDogApp.setOnClickListener { navigateAgeDogApp() }
        btnSuperHeroApp.setOnClickListener { navigateSuperHeroApp() }
        btnSuperHeroDosApp.setOnClickListener { navigateToSuperHeroDosApp() }
    }

    private fun navigateToSuperHeroDosApp() {
        //cambio de pantalla superherolist
        val intent = Intent(this, SuperHeroListActivity::class.java)
        startActivity(intent)
    }

    private fun navigateSuperHeroApp() {
        //cambio de pantalla para SuperHeroActivity
        val intent = Intent(this, SuperHeroActivity::class.java)
        startActivity(intent)
    }

    private fun navigateAgeDogApp() {
        //cambio de pantalla para agedogactivity
        val intent = Intent(this, AgeDogActivity::class.java)
        startActivity(intent)
    }

    private fun navigateToToDoAPP() {
        //cambio de pantalla para todoactivity
        val intent = Intent(this, TodoActivity::class.java)  /*this -> desde este contexto*/
        startActivity(intent)
    }

    fun navigateToIMCAPP() {
        /*para poder realizar el cambio de pantalla a ImcCalculatorActivity */
        val intent =
            Intent(this, ImcCalculatorActivity::class.java)  /*this -> desde este contexto*/
        startActivity(intent)
    }
}