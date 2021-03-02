package com.enigmacamp.mysharedprefernces

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.enigmacamp.mysharedprefernces.helper.Constant
import com.enigmacamp.mysharedprefernces.helper.PrefrencesHelper
import kotlinx.android.synthetic.main.activity_user.*

class UserActivity : AppCompatActivity() {
    lateinit var prefHelper: PrefrencesHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user)

        prefHelper = PrefrencesHelper(this)

        //untuk mendapatkan data username
        textUsername.text = prefHelper.getString( Constant.PREF_USERNAME )

        //logic unutk menghapus
        buttonLogout.setOnClickListener {
            prefHelper.clear()
            showMessage( "Keluar" )
            moveIntent()
        }
    }

    private fun moveIntent(){
        //pindah ke Login
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    private fun showMessage(message: String) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_SHORT).show()
    }
}