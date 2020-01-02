package com.example.uselessapp

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class UsefulAdapter internal constructor(context: Context):RecyclerView.Adapter<com.example.uselessapp.UsefulAdapter.UsefulViewHolder>(){
    //A reference to display ViewHolder
    private val inflater:LayoutInflater = LayoutInflater.from(context)

    //A reference to Useful record(s)
    private var usefulRecords = emptyList<com.example.uselessapp.Useful>()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): com.example.uselessapp.UsefulAdapter.UsefulViewHolder {
        val itemView = inflater.inflate(
            com.example.roomdata.R.layout.recycleview_item,parent,false
        )
        return UsefulViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return usefulRecords.size
    }

    override fun onBindViewHolder(holder: com.example.uselessapp.UsefulAdapter.UsefulViewHolder, position: Int) {
        val usefulRec = usefulRecords.get(position)
        holder.textViewName.text = usefulRec.name

        holder.textViewAge.text = usefulRec.age.toString()
    }

    inner class UsefulViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {
        val textViewName: TextView = itemView.findViewById(com.example.roomdata.R.id.textViewName)
        val textViewAge: TextView = itemView.findViewById(com.example.roomdata.R.id.textViewAge)
    }
    internal fun setUsefulRecords(usefulRecords: List<com.example.uselessapp.Useful>){
        this.usefulRecords = usefulRecords
        notifyDataSetChanged()
    }
}