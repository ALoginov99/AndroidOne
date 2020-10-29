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
import androidx.navigation.fragment.NavHostFragment
import kotlinx.android.synthetic.main.fragment_two.view.*

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [TwoFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class TwoFragment : Fragment() {
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
        val fragmentLayout = inflater.inflate(R.layout.fragment_two, container, false)
        val navController = NavHostFragment.findNavController(this)

        fragmentLayout.btnBack.setOnClickListener{
            navController.popBackStack()
        }

        fun loadFromSingleton() {
            fragmentLayout.textView.text = Singleton.Text
        }

        fun loadFromBundle() {
            val text = arguments!!.getString(BUNDLE_TEXT)
            fragmentLayout.textView.text = text
        }

        fun loadFromSharedPreferences() {
            val pref = this.activity?.getSharedPreferences(APP_PREFERENCES, Context.MODE_PRIVATE)
            fragmentLayout.textView.text = pref?.getString(SP_TEXT, "")
        }

        val type = TransferType.fromInt(arguments!!.getInt(DATA_TRANSFER_TYPE))
        when(type) {
            TransferType.Singleton -> loadFromSingleton()
            TransferType.Bundle -> loadFromBundle()
            TransferType.SharedPreferences -> loadFromSharedPreferences()
        }

        return fragmentLayout
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment TwoFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            TwoFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}