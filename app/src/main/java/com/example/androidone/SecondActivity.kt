package com.example.androidone

import APP_PREFERENCES
import BUNDLE
import BUNDLE_TEXT
import DATA_TRANSFER_TYPE
import TransferType
import SP_TEXT
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        val type: TransferType = TransferType.fromInt( intent.getIntExtra(DATA_TRANSFER_TYPE,-1))
            ?: return

        when(type){
            TransferType.Singleton->loadFromSingleton()
            TransferType.Bundle->loadFromBundle()
            TransferType.SharedPreferences->loadFromSharedPreferences()
        }
    }

    private fun loadFromSingleton(){
        textView.text = Singleton.Text
    }

    private fun loadFromBundle() {
        val bundle = intent.getBundleExtra(BUNDLE)
        textView.text = bundle?.getString(BUNDLE_TEXT)
    }

    private fun loadFromSharedPreferences() {
        val pref = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)
        textView.text = pref.getString(SP_TEXT,"")
    }

    fun back(view: View) {
        finish()
    }
}