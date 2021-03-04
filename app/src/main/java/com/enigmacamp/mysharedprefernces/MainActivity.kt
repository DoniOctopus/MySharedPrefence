package com.enigmacamp.mysharedprefernces

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(),View.OnClickListener {

    val REQUEST_CODE = 777
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        askPermission()
        button_browse.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, REQUEST_CODE)
    }

    //isi dari ini adalah menagkap balikan dari activtyForResultnya
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode.equals(REQUEST_CODE)){
            val resultURI = data?.data
            //buat nampilih datanya masuk atau tidak
            Log.i("BROWSE_IMAGE_RESULT",resultURI.toString())
            imageView.setImageURI(resultURI)
        }
    }

    fun askPermission(){
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA)!=PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA),9090)
            return
        }
        Log.i("PERMISSION","SELESAI")
    }

}