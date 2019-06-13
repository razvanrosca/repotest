package com.example.gitapp.util

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide

object ImageUtils {

    fun displayImage(context: Context, imageView: ImageView, avatarUrl: String) {

        Glide.with(context)
            .load(avatarUrl)
            .asBitmap()
            .into(imageView)

    }

}