package com.example.gitapp.model

import com.google.gson.annotations.SerializedName

data class Readme(
    @SerializedName("content") var content: String
)