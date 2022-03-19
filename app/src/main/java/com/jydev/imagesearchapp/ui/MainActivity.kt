package com.jydev.imagesearchapp.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.android.material.tabs.TabLayoutMediator
import com.jydev.imagesearchapp.R
import com.jydev.imagesearchapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    private val tabTextResList = listOf(R.string.image_feed, R.string.image_feed_library)
    private val pagerAdapter by lazy {
        ImageThumbnailPagerAdapter(this)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        with(binding){
            setView()
        }
    }

    private fun ActivityMainBinding.setView(){
        viewpager.adapter = pagerAdapter
        TabLayoutMediator(tabLayout,viewpager){ tab, position ->
            tab.text = getString(tabTextResList[position])
        }.attach()
    }
}