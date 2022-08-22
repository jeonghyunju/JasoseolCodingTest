package com.example.jasoseolcodingtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.jasoseolcodingtest.databinding.ActivityMovieDetailBinding
import androidx.databinding.DataBindingUtil.setContentView

class MovieDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMovieDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = setContentView(this, R.layout.activity_movie_detail)
        binding.lifecycleOwner = this
    }
}