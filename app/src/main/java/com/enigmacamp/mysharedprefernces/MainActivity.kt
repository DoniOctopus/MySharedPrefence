package com.enigmacamp.mysharedprefernces

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.View
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity(),View.OnClickListener {

    val BROWSE_REQUEST_CODE = 777
    private val CAMERA_REQUEST_CODE = 1888
    lateinit var photo : File
    lateinit var currentPath : String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        askPermission()
        button_browse.setOnClickListener(this)
        button_camera.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        if (v == button_browse){
            browseImage()
        }else{
            dispatcherCamera()
        }
    }

    //untuk menggabil Image dari gallery
    fun browseImage(){
        val browserIntent = Intent(Intent.ACTION_PICK)
        browserIntent.type = "image/*"
        startActivityForResult(browserIntent, BROWSE_REQUEST_CODE)
    }

    //menggambil image menggunakan camera
    fun dispatcherCamera(){
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        //buat temporary filenya
        photo = createTempImageFile()
        //pakai Uri agar bisa memakai si temporary filenya
        val photoUri = FileProvider.getUriForFile(this,"com.enigmacamp.mysharedprefernces.fileprovider",photo)
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT,photoUri)
        startActivityForResult(cameraIntent, CAMERA_REQUEST_CODE)
    }

    fun createTempImageFile(): File {
        //format tanggal dan waktu untuk detail kapan image dibuat atau diambil
        val timeFileName : String = SimpleDateFormat("yyyMMddHHmmss").format(Date())
        //bila tidak memakai Envirohment maka hanya sebatas -> getExternalDir /Android/com.enimga.xxxx
        //bila memakai Envirohment -> getExternalDir /Android/com/enigma.xxxx/files/Picture
        val storageDir : File? = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        //jenis file yang akan kita buat adalah image dengan typenya .jpg
        val filenya = File.createTempFile("GAMBAR${timeFileName}",".jpg",storageDir)
        currentPath = filenya.absolutePath
        return filenya
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
//            val photo: Bitmap = data?.extras?.get("data") as Bitmap
//            imageView.setImageBitmap(photo)
            val imagaeBitmap = BitmapFactory.decodeFile(photo.absolutePath)
            imageView.setImageBitmap(imagaeBitmap)
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