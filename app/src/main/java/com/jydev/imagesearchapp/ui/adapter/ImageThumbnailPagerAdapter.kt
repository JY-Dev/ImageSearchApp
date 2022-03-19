package com.jydev.imagesearchapp.ui.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.jydev.imagesearchapp.ui.imagefeed.ImageFeedFragment
import com.jydev.imagesearchapp.ui.imagefeedlibrary.ImageFeedLibraryFragment

class ImageThumbnailPagerAdapter(fragmentActivity: FragmentActivity) :FragmentStateAdapter(fragmentActivity){
    private val fragmentList = listOf(ImageFeedFragment(), ImageFeedLibraryFragment())
    override fun getItemCount(): Int =
        fragmentList.size

    override fun createFragment(position: Int): Fragment =
        fragmentList[position]
}