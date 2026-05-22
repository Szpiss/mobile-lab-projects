package com.example.headlineapp

data class NewsBean(
    val id: Int,
    val title: String,
    val source: String,
    val comment: String,
    val time: String,
    val type: Int,
    val images: List<Int>,
    val isTop: Boolean = false
)
