package com.example.jasoseolcodingtest.app

import android.os.Build
import android.text.Html
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
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

@RequiresApi(Build.VERSION_CODES.N)
@BindingAdapter("setTitle")
fun setTitle(textView: TextView, title: String) {
    textView.text = Html.fromHtml(title, 0).toString()
}

@BindingAdapter("replaceText")
fun replaceText(textView: TextView, text: String) {
    if(text.split("|").size == 2) {
        textView.text = text.replace("|", "")
    }else {
        textView.text = text.replace("|", ", ")
    }
}