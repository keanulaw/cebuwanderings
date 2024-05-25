package com.example.cebuwanderingsagain

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.cebuwanderingsagain.sampledata.ApoloActivity
import com.example.cebuwanderingsagain.sampledata.Place1Activity
import com.example.cebuwanderingsagain.sampledata.Place2Activity
import com.example.cebuwanderingsagain.sampledata.TopsActivity

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class MountainFragment : Fragment() {
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
        val view = inflater.inflate(R.layout.fragment_mountain, container, false)
        val listView: ListView = view.findViewById(R.id.listViewMountain)

        // Populate the list with mountain places
        if (Places.getMountainPlaces().isEmpty()) {
            Places.addMountainPlace(Place("Apolo", 123, R.drawable.mountain1))
            Places.addMountainPlace(Place("Tops", 321, R.drawable.mountain2))
        }

        val adapter = MyAdapter(requireContext(), Places.getMountainPlaces())
        listView.adapter = adapter

        listView.setOnItemClickListener { _, _, position, _ ->
            when (position) {
                0 -> {
                    val explicitIntent = Intent(activity, ApoloActivity::class.java)
                    startActivity(explicitIntent)
                    Toast.makeText(requireContext(), "Place One", Toast.LENGTH_SHORT).show()
                }
                1 -> {
                    val explicitIntent = Intent(activity, TopsActivity::class.java)
                    startActivity(explicitIntent)
                    Toast.makeText(requireContext(), "Place Two", Toast.LENGTH_SHORT).show()
                }
            }
        }

        return view
    }

    companion object {
        fun newInstance(param1: String, param2: String) =
            MountainFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }
}
