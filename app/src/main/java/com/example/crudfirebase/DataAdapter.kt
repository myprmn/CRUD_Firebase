package com.example.crudfirebase

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.view.menu.ActionMenuItemView
import androidx.recyclerview.widget.RecyclerView
import org.w3c.dom.Text

class DataAdapter (
    private val userList: ArrayList<DataModel>): RecyclerView.Adapter<DataAdapter.MyViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DataAdapter.MyViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.view_data_user,
            parent,false)
        return MyViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: DataAdapter.MyViewHolder, position: Int) {
        val currentitem = userList[position]

        holder.name.text = currentitem.nama
        holder.email.text = currentitem.email
    }

    override fun getItemCount(): Int {
        return userList.size
    }

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val name : TextView = itemView.findViewById(R.id.tv_DataNama)
        val email : TextView = itemView.findViewById(R.id.tv_DataEmail)
    }


}