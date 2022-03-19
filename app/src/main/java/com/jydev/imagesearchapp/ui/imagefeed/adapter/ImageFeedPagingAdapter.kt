package com.jydev.imagesearchapp.ui.imagefeed.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.util.timeMilToDateString
import com.jydev.domain.model.ImageFeed
import com.jydev.imagesearchapp.R
import com.jydev.imagesearchapp.databinding.ItemImageFeedBinding
import java.text.SimpleDateFormat
import java.util.*

class ImageFeedPagingAdapter(private val feedLibraryStateChange : (ImageFeed,Boolean) -> Unit, private val feedLibraryState : (url : String) -> Boolean) :
    PagingDataAdapter<ImageFeed, ImageFeedViewHolder>(diffUtil) {
    override fun onBindViewHolder(holder: ImageFeedViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ImageFeedViewHolder =
        ImageFeedViewHolder(ItemImageFeedBinding.inflate(LayoutInflater.from(parent.context),parent,false),feedLibraryStateChange, feedLibraryState)

    companion object{
        val diffUtil = object : DiffUtil.ItemCallback<ImageFeed>() {
            override fun areItemsTheSame(
                oldItem: ImageFeed,
                newItem: ImageFeed
            ): Boolean =
                oldItem.url == newItem.url


            override fun areContentsTheSame(
                oldItem: ImageFeed,
                newItem: ImageFeed
            ): Boolean = oldItem == newItem

        }
    }
}

class ImageFeedViewHolder(private val binding: ItemImageFeedBinding,private val feedLibraryStateChange : (ImageFeed,Boolean) -> Unit, private val feedLibraryState : (url : String) -> Boolean) : RecyclerView.ViewHolder(binding.root){
    private val dateTimeFormat = SimpleDateFormat("yyyy년 MM월 dd일 hh:mm:ss", Locale.KOREA)

    fun bind(item : ImageFeed?){
        item?.let {
            Glide.with(binding.root)
                .load(item.url)
                .into(binding.imageThumbnailView)
            binding.dateTimeText.text = timeMilToDateString(dateTimeFormat,item.dateTime)
            setButtonDrawable(item)
            binding.libraryCheckButton.setOnClickListener {
                feedLibraryStateChange(item,feedLibraryState(item.url).not())
                setButtonDrawable(item)
            }
        }
    }

    private fun setButtonDrawable(item : ImageFeed){
        val buttonDrawable = if(feedLibraryState(item.url)) R.drawable.selected_heart else R.drawable.unselected_heart
        binding.libraryCheckButton.background = ContextCompat.getDrawable(binding.root.context,buttonDrawable)
    }

}