package com.example.jasoseolcodingtest.app

import android.widget.ImageView
import android.widget.TextView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy

@BindingAdapter("doubleToString")
fun doubleToString(textView: TextView, num: Double) {
    textView.text = num.toString()
}

@BindingAdapter("imageFromUrl")
fun imageFromUrl(imageView: ImageView, url: String?) {
    Glide.with(imageView.context)
        .load(url)
        .fitCenter()
        .diskCacheStrategy(DiskCacheStrategy.AUTOMATIC)
        .into(imageView)
}