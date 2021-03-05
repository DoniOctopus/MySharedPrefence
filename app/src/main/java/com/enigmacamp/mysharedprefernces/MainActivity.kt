package com.enigmacamp.mysharedprefernces

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.util.Log
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),View.OnClickListener {

    val BROWSE_REQUEST_CODE = 777
    private val CAMERA_REQUEST_CODE = 1888
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        askPermission()
        button_browse.setOnClickListener(this)
        button_camera.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        val browserIntent = Intent(Intent.ACTION_PICK)
        browserIntent.type = "image/*"
        if (v == button_browse){
            startActivityForResult(browserIntent, BROWSE_REQUEST_CODE)
        }else{
            startActivityForResult(cameraIntent, CAMERA_REQUEST_CODE)
        }
    }

    //isi dari ini adalah menagkap balikan dari activtyForResultnya
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        Log.i("INI HARUS DIPANGIL",requestCode.toString())
        //untuk menampilkan allow sebagai popup
        if (requestCode.equals(BROWSE_REQUEST_CODE)){
            val resultURI = data?.data
            //buat nampilih datanya masuk atau tidak
            Log.i("BROWSE_IMAGE_RESULT",resultURI.toString())
            imageView.setImageURI(resultURI)
        }
        if(requestCode.equals(CAMERA_REQUEST_CODE)){
            val photo: Bitmap = data?.extras?.get("data") as Bitmap
            imageView.setImageBitmap(photo)
             }
         }

    fun askPermission(){
        //mencek apakah sudah pernah di allow atau tidak
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)!=PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA),CAMERA_REQUEST_CODE)
            return
        }
        Log.i("PERMISSION","SELESAI")
    }
}