package com.jydev.imagesearchapp.ui.imagefeedlibrary

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.jydev.imagesearchapp.databinding.FragmentImageFeedLibrarayBinding
import com.jydev.imagesearchapp.ui.ImageFeedLibraryViewModel
import com.jydev.imagesearchapp.ui.imagefeedlibrary.adapter.ImageFeedLibraryAdapter

class ImageFeedLibraryFragment : Fragment() {
    private lateinit var binding : FragmentImageFeedLibrarayBinding
    private lateinit var imageFeedLibraryAdapter : ImageFeedLibraryAdapter
    private val imageFeedLibraryViewModel : ImageFeedLibraryViewModel by viewModels({requireActivity()})
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
            setAdapter()
            setView()
            observeData()
        }
    }

    private fun setAdapter(){
        imageFeedLibraryAdapter =  ImageFeedLibraryAdapter()
    }

    private fun FragmentImageFeedLibrarayBinding.setView(){
        imageFeedLibraryRecyclerView.adapter = imageFeedLibraryAdapter
    }

    private fun observeData(){
        imageFeedLibraryViewModel.imageFeed.observe(viewLifecycleOwner){
            imageFeedLibraryAdapter.submitList(it)
        }
    }


}