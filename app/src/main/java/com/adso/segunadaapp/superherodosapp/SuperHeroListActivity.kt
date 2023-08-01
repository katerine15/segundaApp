package com.adso.segunadaapp.superherodosapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import com.adso.segunadaapp.R
import com.adso.segunadaapp.databinding.ActivitySuperHeroListBinding
import com.adso.segunadaapp.superherodosapp.DetailSuperHeroActivity.Companion.EXTRA_ID
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SuperHeroListActivity : AppCompatActivity() {

    private lateinit var binding: ActivitySuperHeroListBinding
    private lateinit var retrofit: Retrofit

    private lateinit var adapter: SuperHeroAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySuperHeroListBinding.inflate(layoutInflater)
        setContentView(R.layout.activity_super_hero_list)
        setContentView(binding.root)

        retrofit = getRetrofit()

        initUI()

        binding.searchView
        binding.rwSuperHero
    }

    private fun initUI() {
        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            // se va a llamar cuando se pulse el de buscar
            override fun onQueryTextSubmit(query: String?): Boolean {
                searchByName(query.orEmpty())
                return false
            }

            //cada vez que nosotros escribimos
            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })

        adapter = SuperHeroAdapter{superheroId -> navigateToDetail (superheroId)}
        binding.rwSuperHero.layoutManager = LinearLayoutManager(this)
        binding.rwSuperHero.adapter = adapter
    }
    //buscar
    private fun searchByName(orEmpty: String) {
        binding.progressBar.isVisible = true
        CoroutineScope(Dispatchers.IO).launch {
            //con retrofit se pueda crear esa apliservice
            val myResponse: Response<SuperHeroDataResponse> =
                retrofit.create(ApiService::class.java).getSuperheores(orEmpty)
            if (myResponse.isSuccessful) {
                val response: SuperHeroDataResponse? = myResponse.body()
                if (response != null){
                    Log.i("ADSO", response.toString())
                    runOnUiThread {
                        adapter.updateList(response.superherores)
                        binding.progressBar.isVisible = false
                    }

                }

            } else {
                Log.i("ADOS", "No funciona")
            }
        }

    }

    private fun getRetrofit(): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl("https://superheroapi.com/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    private fun navigateToDetail (id: String){
        val intent = Intent(this, DetailSuperHeroActivity::class.java)
        intent.putExtra(EXTRA_ID,id)
        startActivity(intent)
    }
}