package com.example.crudfirebase

class DataModel (
    var result: ArrayList<Result>
){
    data class Result(
        val nama_user : String,
        val email : String
    )
}