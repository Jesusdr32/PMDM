package com.example.firstfragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.firstfragment.databinding.FragmentMiFragmentoBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [miFragmento.newInstance] factory method to
 * create an instance of this fragment.
 */
class miFragmento : Fragment() {
    private lateinit var binding : FragmentMiFragmentoBinding

    // TODO: Rename and change types of parameters
    private var texto : String? = null
    private var num : Int? = null

    private lateinit var miVista : View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            texto = it.getString(ARG_PARAM1)
            num = it.getInt(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentMiFragmentoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.tvFragment.text = texto
        binding.btnFragment.setOnClickListener {
            binding.tvFragment.text = "Trabajando con fragmentos"
            Toast.makeText(requireActivity(), "El numero era el $num", Toast.LENGTH_LONG).show()
        }
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment miFragmento.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(texto : String, num : Int) =
            miFragmento().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, texto)
                    putInt(ARG_PARAM2, num)
                }
            }
    }
}