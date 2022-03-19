package com.jydev.imagesearchapp.ui.imagefeed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.jydev.imagesearchapp.databinding.FragmentImageFeedBinding
import com.jydev.imagesearchapp.ui.ImageFeedViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ImageFeedFragment : Fragment() {
    private val imageFeedViewModel : ImageFeedViewModel by viewModels()
    private lateinit var imageThumbnailAdapter : ImageThumbnailFeedPagingAdapter
    lateinit var binding : FragmentImageFeedBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentImageFeedBinding.inflate(inflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        with(binding){
            setAdapter()
            observeData()
            setView()
            setListener()
        }
    }

    private fun setAdapter(){
        imageThumbnailAdapter = ImageThumbnailFeedPagingAdapter()
    }

    private fun FragmentImageFeedBinding.setView(){
        feedRecyclerView.adapter = imageThumbnailAdapter
    }

    private fun FragmentImageFeedBinding.setListener(){
        searchEditText.setOnEditorActionListener { textView, i, keyEvent ->
            imageFeedViewModel.getImageThumbnailPagingData(textView.text.toString())
            true
        }
    }

    private fun observeData() {
        imageFeedViewModel.imageThumbnailPagingData.observe(viewLifecycleOwner){
            CoroutineScope(Dispatchers.Main).launch {
                imageThumbnailAdapter.submitData(it)
            }
        }
    }
}