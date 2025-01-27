package com.debend.retrofit_example

data class Comment(val id: Int, val comment: String)
data class PostDetails(
    val userId: Int, val id: Int, val title: String, val body: String, val comments: List<Comment>
)
