package com.enigmacamp.mysharedprefernces.helper

import android.content.Context
import android.content.SharedPreferences

/*
Class ini akan berisi Base class dari sharedprefrence yang bisa kita gunakan di halaman  atau dimana pun yg kita buat nanti
 */
class PrefrencesHelper(context: Context) {
    //seperti membuat database,maka dari itu SharedPref harus membuat nama untuk penyimpanannya
    private val PREFS_NAME = "doni"
    private var sharedPref: SharedPreferences
    val editor: SharedPreferences.Editor

    //kita gunakan init agar ketika class helper ini dipanggil ,otomatis apa yang ada didalam init ini dijalankan otomatis
    //MODE_PRIVATE adalah  mode default dari shredpref,agar project ini saja yang bsa mengakses data dari sharedpref
    init {
        sharedPref = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
        editor = sharedPref.edit()
    }

    //menyimpan data di prefrence dengan type data String
    fun put(key: String, value: String) {
        editor.putString(key, value)
                .apply()
    }

    //menyimpan data di prefrence dengan type data Bollean
    fun put(key: String, value: Boolean) {
        editor.putBoolean(key, value)
                .apply()
    }

    //Mendapatkan Nilai atau value dari sharedPref dengan type data Bollean
    fun getBoolean(key: String): Boolean {
        return sharedPref.getBoolean(key, false)
    }

    //Mendapatkan Nilai atau value dari sharedPref dengan type data String
    fun getString(key: String): String? {
        return sharedPref.getString(key, null)
    }

    fun clear() {
        editor.clear()
                .apply()
    }

}