package com.example.cebuwanderingsagain

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import java.text.FieldPosition

class MyAdapter (
    context: Context,
    private val customers: List<Place>
): ArrayAdapter<Place>(context, R.layout.list, customers){

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val inflater = context
            .getSystemService(
                Context.LAYOUT_INFLATER_SERVICE
            ) as LayoutInflater

        val rowView = inflater.inflate(
            R.layout.list, parent, false
        )


        val tvName: TextView = rowView.findViewById(R.id.customerName)
        val tvNum: TextView = rowView.findViewById(R.id.customerNum)
        val imageView: ImageView = rowView.findViewById(R.id.imageView)

        val customer = customers[position]

        tvName.text = customer.name
        tvNum.text = customer.num.toString()
        imageView.setImageResource(customer.image)
        return rowView
    }
}
