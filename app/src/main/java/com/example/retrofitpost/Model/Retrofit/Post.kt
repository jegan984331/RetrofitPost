package com.example.retrofitpost.Model.Retrofit

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Post(

    @Expose
    //  @SerializedName("albumId")
    @SerializedName("postId")
    val albumid: Int,
    @Expose
    //   @SerializedName("id")
    @SerializedName("id")
    val id: Int,
    @Expose
    //  @SerializedName("title")
    @SerializedName("name")
    val title: String,
    @Expose
    //  @SerializedName("url")
    @SerializedName("email")
    val url: String,
    @Expose
    //  @SerializedName("thumbnailUrl")
    @SerializedName("body")
    val thumbnailurl: String
)
