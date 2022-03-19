package com.jydev.imagesearchapp.ui.imagefeedlibrary

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.jydev.imagesearchapp.databinding.FragmentImageFeedLibrarayBinding

class ImageFeedLibraryFragment : Fragment() {
    lateinit var binding : FragmentImageFeedLibrarayBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentImageFeedLibrarayBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding){
            setView()
        }
    }

    private fun FragmentImageFeedLibrarayBinding.setView(){

    }


}