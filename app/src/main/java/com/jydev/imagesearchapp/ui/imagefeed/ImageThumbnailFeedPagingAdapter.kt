package com.jydev.imagesearchapp.ui.imagefeed

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.util.timeMilToDateString
import com.jydev.domain.model.ImageThumbnail
import com.jydev.imagesearchapp.databinding.ItemImageThumbnailFeedBinding
import java.text.SimpleDateFormat
import java.util.*

class ImageThumbnailFeedPagingAdapter :
    PagingDataAdapter<ImageThumbnail, ImageThumbnailFeedViewHolder>(diffUtil) {
    override fun onBindViewHolder(holder: ImageThumbnailFeedViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ImageThumbnailFeedViewHolder =
        ImageThumbnailFeedViewHolder(ItemImageThumbnailFeedBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    companion object{
        val diffUtil = object : DiffUtil.ItemCallback<ImageThumbnail>() {
            override fun areItemsTheSame(
                oldItem: ImageThumbnail,
                newItem: ImageThumbnail
            ): Boolean =
                oldItem.url == newItem.url


            override fun areContentsTheSame(
                oldItem: ImageThumbnail,
                newItem: ImageThumbnail
            ): Boolean = oldItem == newItem

        }
    }
}

class ImageThumbnailFeedViewHolder(private val binding: ItemImageThumbnailFeedBinding) : RecyclerView.ViewHolder(binding.root){
    private val dateTimeFormat = SimpleDateFormat("yyyy년 MM월 dd일 hh:mm:ss", Locale.KOREA)
    fun bind(item : ImageThumbnail?){
        item?.let {
            Glide.with(binding.root)
                .load(item.url)
                .into(binding.imageThumbnailView)
            binding.dateTimeText.text = timeMilToDateString(dateTimeFormat,item.dateTime)
        }
    }

}