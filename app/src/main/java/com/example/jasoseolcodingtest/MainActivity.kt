package com.example.jasoseolcodingtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.jasoseolcodingtest.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import androidx.databinding.DataBindingUtil.setContentView

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = setContentView(this, R.layout.activity_main)
        binding.lifecycleOwner = this
    }
}