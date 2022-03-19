package com.jydev.imagesearchapp.ui.imagefeed

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.jydev.imagesearchapp.databinding.FragmentImageFeedBinding
import com.jydev.imagesearchapp.ui.ImageFeedLibraryViewModel
import com.jydev.imagesearchapp.ui.ImageFeedViewModel
import com.jydev.imagesearchapp.ui.imagefeed.adapter.ImageFeedPagingAdapter
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ImageFeedFragment : Fragment() {
    private val imageFeedViewModel : ImageFeedViewModel by viewModels()
    private val imageFeedLibraryViewModel : ImageFeedLibraryViewModel by viewModels({requireActivity()})
    private lateinit var imageFeedAdapter : ImageFeedPagingAdapter
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
        imageFeedAdapter = ImageFeedPagingAdapter({ imageFeed, libraryStatus ->
            when(libraryStatus){
                true -> imageFeedLibraryViewModel.insertImageFeedLibrary(imageFeed)
                false -> imageFeedLibraryViewModel.deleteImageFeedLibrary(imageFeed.url)
            }
        },{
            imageFeedLibraryViewModel.isImageFeedInLibrary(it)
        })
    }

    private fun FragmentImageFeedBinding.setView(){
        feedRecyclerView.adapter = imageFeedAdapter
    }

    private fun FragmentImageFeedBinding.setListener(){
        searchEditText.setOnEditorActionListener { textView, i, keyEvent ->
            imageFeedViewModel.getImageFeedPagingData(textView.text.toString())
            true
        }
    }

    private fun observeData() {
        imageFeedViewModel.imageFeedPagingData.observe(viewLifecycleOwner){
            CoroutineScope(Dispatchers.Main).launch {
                imageFeedAdapter.submitData(it)
            }
        }
    }
}