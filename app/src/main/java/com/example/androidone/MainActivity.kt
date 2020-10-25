package com.example.androidone

import APP_PREFERENCES
import BUNDLE
import SP_TEXT
import BUNDLE_TEXT
import DATA_TRANSFER_TYPE
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.os.bundleOf
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    //function for text validation
    private fun checkText(text:String): Boolean {
        if(text.isNotEmpty())
            return true

        //if the line is empty, display the message
        val toast = Toast.makeText(this,"Поле не должно быть пустым!",Toast.LENGTH_SHORT)
        toast.show()
        return false
    }

    fun singletonSave(view: View) {
        if(!checkText(editText.text.toString()))
            return

        Singleton.Text = editText.text.toString()

        //start new activity
        val intent = Intent(this, SecondActivity::class.java).apply{
            //put the type of our transfer
            putExtra(DATA_TRANSFER_TYPE,TransferType.Singleton.value)
        }
        startActivity(intent)
    }

    fun sharedPreferencesSave(view: View) {
        if(!checkText(editText.text.toString()))
            return

        //get SharedPreferences and put text into it
        val pref = getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)
        val editor = pref.edit()
        editor.putString(SP_TEXT,editText.text.toString())
        editor.commit()

        //start new activity
        val intent = Intent(this, SecondActivity::class.java).apply{
            putExtra(DATA_TRANSFER_TYPE,TransferType.SharedPreferences.value)
        }
        startActivity(intent)
    }

    fun bundleSave(view: View) {
        if(!checkText(editText.text.toString()))
            return

        val bundle : Bundle = bundleOf(BUNDLE_TEXT to editText.text.toString())

        //start new activity
        val intent = Intent(this, SecondActivity::class.java).apply{
            putExtra(DATA_TRANSFER_TYPE,TransferType.Bundle.value)

            putExtra(BUNDLE,bundle)
        }
        startActivity(intent)
    }
}