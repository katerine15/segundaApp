package com.adso.segunadaapp.superheroapp

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.adso.segunadaapp.R
import com.adso.segunadaapp.databinding.ActivityDetailBinding

class DetailActivity : AppCompatActivity() {
    companion object{
        const val HERO_KEY = "hero"
        const val HERO_BITMAP_KEY = "bitmap"
        /*const val AlTER_EGO_KEY = "alter_ego"
        const val BIO_KEY = "bio"
        const val POWER_KEY = "power"*/
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val extras = intent.extras!!
        val superhero = extras.getParcelable<Hero>(HERO_KEY)!!
        binding.hero = superhero
        val imagePatch = extras.getString(HERO_BITMAP_KEY)!!

        val bitmap = BitmapFactory.decodeFile(imagePatch)

        if (bitmap != null){
            binding.heroImage.setImageBitmap(bitmap)
        }
        /*val alterEgo = extras.getString( AlTER_EGO_KEY) ?:""
        val bio = extras.getString(BIO_KEY) ?:""
        val power = extras.getFloat(POWER_KEY)*/

       /* binding.heroNameText.text = superhero.name
        binding.alterEgoText.text = superhero.alterEgo
        binding.bioText.text = superhero.bio
        binding.ratingBar.rating = superhero.power*/


    }
}