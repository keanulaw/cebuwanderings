package com.example.cebuwanderingsagain

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.cebuwanderingsagain.sampledata.Place1Activity
import com.example.cebuwanderingsagain.sampledata.Place2Activity
import com.example.cebuwanderingsagain.sampledata.Place3Activity

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class RestaurantFragment : Fragment() {
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
        val view = inflater.inflate(R.layout.fragment_restaurant, container, false)
        val listView: ListView = view.findViewById(R.id.listViewRestaurant)

        // Populate the list with restaurant places
        if (Places.getRestaurantPlaces().isEmpty()) {
            Places.addRestaurantPlace(Place("Shannon Keanu A. Yase", 123, R.drawable.one))
            Places.addRestaurantPlace(Place("Edriane Amistoso", 321, R.drawable.two))
            Places.addRestaurantPlace(Place("Joey Velasco", 234, R.drawable.three))
        }

        val adapter = MyAdapter(requireContext(), Places.getRestaurantPlaces())
        listView.adapter = adapter

        listView.setOnItemClickListener { _, _, position, _ ->
            when (position) {
                0 -> {
                    val explicitIntent = Intent(activity, Place1Activity::class.java)
                    startActivity(explicitIntent)
                    Toast.makeText(requireContext(), "Place One", Toast.LENGTH_SHORT).show()
                }
                1 -> {
                    val explicitIntent = Intent(activity, Place2Activity::class.java)
                    startActivity(explicitIntent)
                    Toast.makeText(requireContext(), "Place Two", Toast.LENGTH_SHORT).show()
                }
                2 -> {
                    val explicitIntent = Intent(activity, Place3Activity::class.java)
                    startActivity(explicitIntent)
                    Toast.makeText(requireContext(), "Place Three", Toast.LENGTH_SHORT).show()
                }
            }
        }

        return view
    }

    companion object {
        fun newInstance(param1: String, param2: String) =
            RestaurantFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
