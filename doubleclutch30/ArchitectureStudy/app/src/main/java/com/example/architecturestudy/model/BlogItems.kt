package com.example.architecturestudy.model

import com.google.gson.annotations.SerializedName

data class BlogData (val items : List<BlogItems>)

data class BlogItems(

    @SerializedName("title")
    val title : String,
    @SerializedName("description")
    val description : String,
    @SerializedName("bloggername")
    val bloggername : String,
    @SerializedName("link")
    val link : String ,
    @SerializedName("postdate")
    val postdate : String
)