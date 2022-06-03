package com.example.crudfirebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.*

class DataUser : AppCompatActivity() {

    private lateinit var  dbref : DatabaseReference
    private lateinit var  useRecyclerView: RecyclerView
    private lateinit var  userArrayList: ArrayList<DataModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_user)

        useRecyclerView = findViewById(R.id.rvDataUser)
        useRecyclerView.layoutManager = LinearLayoutManager (this)
        useRecyclerView.setHasFixedSize(true)

        userArrayList = ArrayList<DataModel>()
        getUserData()
    }

    private fun getUserData() {
        dbref = FirebaseDatabase.getInstance().getReference("sign upss")
        dbref.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                if (snapshot.exists()){
                    for (DataSnapshot in snapshot.children){
                        val user = DataSnapshot.getValue(DataModel::class.java)
                        userArrayList.add(user!!)
                    }
                    useRecyclerView.adapter = DataAdapter(userArrayList)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        })
    }
}