package com.example.crudfirebase

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import java.lang.ref.Reference
import java.util.*

class Product : AppCompatActivity() {

    private lateinit var Product : EditText
    private lateinit var Satuan : EditText
    private lateinit var Harga : EditText
    private lateinit var Simpan : Button
    private lateinit var mDbRef : DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_product)

        supportActionBar!!.hide()

        Product = findViewById(R.id.etProduct)
        Satuan = findViewById(R.id.etQty)
        Harga = findViewById(R.id.etHarga)
        Simpan = findViewById(R.id.btnSimpan)
        mDbRef = FirebaseDatabase.getInstance().reference

        Simpan.setOnClickListener {
            simpanProduct()
        }
    }

    fun simpanProduct() {
        var namaProduct = Product.text.toString()
        var Qty = Satuan.text.toString()
        var hargaProduct = Harga.text.toString()
        var uuid = UUID.randomUUID().toString()
        mDbRef.child("Product").child((uuid))
            .setValue(Barang(namaProduct,Qty.toInt(),hargaProduct.toInt()))
        Toast.makeText(this,"Berhasil Disimpan", Toast.LENGTH_SHORT).show()
        Product.text.clear()
        Satuan.text.clear()
        Harga.text.clear()
        Log.i("Database Product","Data Berhasil Disimpan di Database")
    }
}