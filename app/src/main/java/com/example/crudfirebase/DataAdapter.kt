package com.example.crudfirebase

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class DataAdapter (
    var results : ArrayList<DataModel.Result>):
        RecyclerView.Adapter<DataAdapter.MyViewHolder>(){

    class MyViewHolder (val view: View): RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)= MyViewHolder (
    LayoutInflater.from(parent.context).inflate(R.layout.view_data_user,parent,false))

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val result = results [position]
        holder.view.tv_DataNama.text = result.email
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }

//   override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MyViewHolder (
//        LayoutInflater.from(parent.context).inflate(R.layout.view_data_user,parent,false)
//    )
//
//    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
//        val result = results [position]
//        holder.view.text = result.email
//
//
//    }


}