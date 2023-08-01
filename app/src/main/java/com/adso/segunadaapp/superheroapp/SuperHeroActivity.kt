package com.adso.segunadaapp.superheroapp

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.Image
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.widget.ImageView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.FileProvider
import androidx.core.graphics.drawable.toBitmap
import com.adso.segunadaapp.R
import com.adso.segunadaapp.databinding.ActivitySuperHeroBinding
import java.io.File

class SuperHeroActivity : AppCompatActivity() {
    private  lateinit var heroImage: ImageView

    private var heroBitmap: Bitmap? = null
    private var picturePath = ""

    private val getContent = registerForActivityResult(ActivityResultContracts.TakePicture()){
        success ->
        if(success && picturePath.isNotEmpty()){
            heroBitmap = BitmapFactory.decodeFile(picturePath)
            heroImage.setImageBitmap(heroBitmap)
        }
        /*bitmap ->
        heroBitmap = bitmap
        heroImage.setImageBitmap(heroBitmap)*/
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivitySuperHeroBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.saveButton.setOnClickListener{
            val superheroName = binding.heroNameEdit.text.toString()
            val alterEgo = binding.alterEgoEdit.text.toString()
            val bio = binding.bioEdit.text.toString()
            val power = binding.powerBar.rating

            val hero = Hero(superheroName, alterEgo, bio, power)
            openDetailActivity(hero)
        }
        heroImage = binding.heroImage
        heroImage.setOnClickListener {
            openCamera()
        }
    }

    private fun openCamera() {
        //IMPLICITO
        val file = createImageFile()
        val uri = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N){
            FileProvider.getUriForFile(this, "$packageName.provider", file)
        }else{
            Uri.fromFile(file)
        }
        /*val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        startActivityForResult(cameraIntent, 1000)*/
        getContent.launch(uri)
    }

    private fun createImageFile(): File {
        val fileName = "superhero_image"
        val fileDirectory = getExternalFilesDir(Environment.DIRECTORY_PICTURES )
        val file = File.createTempFile(fileName, ".jpg", fileDirectory)
        picturePath = file.absolutePath
        return file
    }

    private fun openDetailActivity(hero: Hero){
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(DetailActivity.HERO_KEY, hero)
        intent.putExtra(DetailActivity.HERO_BITMAP_KEY, picturePath)
        /*intent.putExtra(DetailActivity.AlTER_EGO_KEY, alterEgo)
        intent.putExtra(DetailActivity.BIO_KEY, bio)
        intent.putExtra(DetailActivity.POWER_KEY, power)*/

        startActivity(intent)
    }

  /*  override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (resultCode == Activity.RESULT_OK && requestCode == 1000){
            val extras = data?.extras
            val heroBitmap = extras?.getParcelable<Bitmap>("data")
            heroImage.setImageBitmap(heroBitmap)
        }
    }*/

}