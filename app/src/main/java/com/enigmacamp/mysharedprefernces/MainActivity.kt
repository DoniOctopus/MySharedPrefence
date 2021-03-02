package com.enigmacamp.mysharedprefernces

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    fun btnSave(v : View?) {
        if (v == btn_save){
            val sharedPreference:SharedPreference=SharedPreference(this)
            val name=edt_name.editableText.toString()
            val email=edt_email.editableText.toString()
            sharedPreference.save("name",name)
            sharedPreference.save("email",email)
            Toast.makeText(this@MainActivity,"Data Stored",Toast.LENGTH_SHORT).show()
        }
        val show = edt_name.text
        val show1 = edt_email.text
        val result = ("${show} ${show1}")
        showSharedPref.text = result
    }

    fun btnRetrive(v : View){
        val sharedPreference:SharedPreference=SharedPreference(this)
        if (v == btn_retriev){
            if (sharedPreference.getValueString("name")!=null) {
                edt_name.hint = sharedPreference.getValueString("name")!!
                Toast.makeText(this@MainActivity,"Data Retrieved",Toast.LENGTH_SHORT).show()
            }else{
                edt_name.hint="NO value found"
            }
            if (sharedPreference.getValueString("email")!=null) {
                edt_email.hint = sharedPreference.getValueString("email")!!
            }else{
                edt_email.hint="No value found"
            }
        }
    }

    fun btnClear(v:View?) {
        val sharedPreference:SharedPreference=SharedPreference(this)
        if(v == btn_clear){
            sharedPreference.clearSharedPreference()
            Toast.makeText(this@MainActivity,"Data Cleared",Toast.LENGTH_SHORT).show()
        }
    }
}
