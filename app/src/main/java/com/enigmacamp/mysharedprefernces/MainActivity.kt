package com.enigmacamp.mysharedprefernces

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.enigmacamp.mysharedprefernces.helper.Constant
import com.enigmacamp.mysharedprefernces.helper.PrefrencesHelper
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    lateinit var prefHelper: PrefrencesHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        prefHelper = PrefrencesHelper(this)

        buttonLogin.setOnClickListener {
            //validasi sederhana
            if (editUsername.text.isNotEmpty() && editPassword.text.isNotEmpty()) {
                //jika username dan password tidak kosong,maka isisnya akan disimpan pada sharedPref
                saveSession( editUsername.text.toString(), editPassword.text.toString() )
                showMessage( "Berhasil login" )
                moveIntent()
            }
        }
    }

    //digunakan untuk melakukan pengecekan
    override fun onStart() {
        super.onStart()
        //mengambil nilai true atau false ,dan keynya adala PREF_IS_LOGIN yang kita ambil dari class Constant
        if (prefHelper.getBoolean( Constant.PREF_IS_LOGIN )) {
            //jika dia bernilai true dia akan start activity
            moveIntent()
        }
    }

    private fun moveIntent(){
        startActivity(Intent(this, UserActivity::class.java))
        //menutup
        finish()
    }

    private fun saveSession(username: String, password: String){
        //Menyimpan data
        prefHelper.put( Constant.PREF_USERNAME, username )
        prefHelper.put( Constant.PREF_PASSWORD, password )
        prefHelper.put( Constant.PREF_IS_LOGIN, true)
    }

    private fun showMessage(message: String) {
        //notif
        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
    }

}
