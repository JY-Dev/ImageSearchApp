package com.jydev.domain.mapper

import com.jydev.domain.model.ImageFeed

fun ImageFeed.toImageFeedLibrary() : ImageFeed =
    ImageFeed(url, dateTime)