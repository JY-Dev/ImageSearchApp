package com.jydev.imagesearchapp.ui.imagefeedlibrary.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.util.timeMilToDateString
import com.jydev.domain.model.ImageFeed
import com.jydev.imagesearchapp.databinding.ItemImageFeedLibraryBinding
import java.text.SimpleDateFormat
import java.util.*

class ImageFeedLibraryAdapter() :
    ListAdapter<ImageFeed, ImageFeedLibraryViewHolder>(diffUtil) {
    override fun onBindViewHolder(holder: ImageFeedLibraryViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ImageFeedLibraryViewHolder =
        ImageFeedLibraryViewHolder(
            ItemImageFeedLibraryBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    companion object {
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

class ImageFeedLibraryViewHolder(
    private val binding: ItemImageFeedLibraryBinding,
) : RecyclerView.ViewHolder(binding.root) {
    private val dateTimeFormat = SimpleDateFormat("yyyy년 MM월 dd일 hh:mm:ss", Locale.KOREA)
    fun bind(item: ImageFeed?) {
        item?.let {
            Glide.with(binding.root)
                .load(item.url)
                .into(binding.imageThumbnailView)
            binding.dateTimeText.text = timeMilToDateString(dateTimeFormat, item.dateTime)
        }
    }

}