package com.example.fragmentslab

data class President (
    val name: String,
    val from: Int,
    val to: Int,
    var desc: String
) : Comparable<President>{
    override fun compareTo(other: President): Int {
        return this.name.compareTo(other.name)
    }
}