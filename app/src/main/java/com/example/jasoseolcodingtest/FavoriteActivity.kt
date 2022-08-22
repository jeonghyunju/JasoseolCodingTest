package com.example.jasoseolcodingtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.jasoseolcodingtest.databinding.ActivityFavoriteBinding
import dagger.hilt.android.AndroidEntryPoint
import androidx.databinding.DataBindingUtil.setContentView

@AndroidEntryPoint
class FavoriteActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFavoriteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = setContentView(this, R.layout.activity_favorite)
        binding.lifecycleOwner = this
    }
}