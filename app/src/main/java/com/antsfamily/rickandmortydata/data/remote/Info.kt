package com.antsfamily.rickandmortydata.data.remote

data class Info(
    val count: Int,
    val pages: Int,
    val next: String? = null,
    val prev: String? = null,
)
