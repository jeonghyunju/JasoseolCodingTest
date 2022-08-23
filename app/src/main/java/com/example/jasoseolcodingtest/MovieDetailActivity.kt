package com.example.jasoseolcodingtest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.activity.viewModels
import com.example.jasoseolcodingtest.databinding.ActivityMovieDetailBinding
import androidx.databinding.DataBindingUtil.setContentView
import com.example.jasoseolcodingtest.model.MovieItem
import com.example.jasoseolcodingtest.viewModel.MovieDetailViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMovieDetailBinding
    private val viewModel: MovieDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = setContentView(this, R.layout.activity_movie_detail)
        binding.lifecycleOwner = this
        binding.viewModel = viewModel

        initWebView()
        setClickListener()

        viewModel.setMovieDetail(
            intent.getSerializableExtra("movie") as MovieItem
        )
        setUpObserver()
    }
    private fun initWebView() {
        binding.webview.apply {
            webViewClient = WebViewClient()
            settings.setSupportZoom(true)
            settings.builtInZoomControls = true
            settings.javaScriptEnabled = true
            settings.javaScriptCanOpenWindowsAutomatically = true
        }
    }

    private fun setClickListener() {
        binding.setClickListener {
            when(it.id) {
                R.id.btn_back -> { finish() }
                R.id.iv_favorite -> { viewModel.favoriteClickEvent() }
            }
        }
    }

    private fun setUpObserver() {
        viewModel.movieDetail.observe(this) {
            if(it != null) {
                binding.webview.loadUrl(it.link)
            }
        }
    }
}