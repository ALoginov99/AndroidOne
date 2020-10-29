package com.example.androidone

import APP_PREFERENCES
import BUNDLE_TEXT
import DATA_TRANSFER_TYPE
import SP_TEXT
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.navigation.fragment.NavHostFragment
import kotlinx.android.synthetic.main.fragment_one.*
import kotlinx.android.synthetic.main.fragment_one.view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [OneFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class OneFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
        val fragmentLayout = inflater.inflate(R.layout.fragment_one, container, false)

        val navController = NavHostFragment.findNavController(this)
        val editText = fragmentLayout.editText

        //function for text validation
        fun checkText(text:String): Boolean {
            if(text.isNotEmpty())
                return true

            //if the line is empty, display the message
            val toast = Toast.makeText(fragmentLayout.context,"Поле не должно быть пустым!", Toast.LENGTH_SHORT)
            toast.show()
            return false
        }

        fragmentLayout.btnSingleton.setOnClickListener {//View->
            if(checkText(editText.text.toString())){
                Singleton.Text = editText.text.toString()

                val bundle = bundleOf(DATA_TRANSFER_TYPE to TransferType.Singleton.value)
                navController.navigate(R.id.twoFragment, bundle)
            }
        }

        fragmentLayout.btnSharedPreferences.setOnClickListener {
            if(checkText(editText.text.toString())){
                //get SharedPreferences and put text into it
                val pref = this.activity?.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)
                val editor = pref?.edit()
                editor?.putString(SP_TEXT,editText.text.toString())
                editor?.commit()

                val bundle = bundleOf(DATA_TRANSFER_TYPE to TransferType.SharedPreferences.value)
                navController.navigate(R.id.twoFragment, bundle)
            }
        }

        fragmentLayout.btnBundle.setOnClickListener {
            if (checkText(editText.text.toString())) {
                val bundle: Bundle = bundleOf(
                    DATA_TRANSFER_TYPE to TransferType.Bundle.value,
                    BUNDLE_TEXT to editText.text.toString()
                )
                navController.navigate(R.id.twoFragment, bundle)
            }
        }

        // Inflate the layout for this fragment
        return fragmentLayout
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment OneFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            OneFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}