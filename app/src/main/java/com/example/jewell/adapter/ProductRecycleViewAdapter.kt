package com.example.jewell.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.jewell.R


class SimpleRecyclerViewAdapter(
    internal var context: Context,
    internal var simplesValues: Array<String>
) : RecyclerView.Adapter<SimpleRecyclerViewAdapter.ViewHolder>() {
    internal lateinit var view: View
    internal lateinit var viewHolder: ViewHolder

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {

        var textView: TextView

        init {

            textView = v.findViewById(R.id.text_view_title)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SimpleRecyclerViewAdapter.ViewHolder {

        view =
            LayoutInflater.from(context).inflate(R.layout.products_view_item, parent, false)

        viewHolder = ViewHolder(view)

        return viewHolder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.textView.text = simplesValues[position]
    }

    override fun getItemCount(): Int {

        return simplesValues.size
    }
}