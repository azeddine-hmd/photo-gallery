package com.innocent.learn.photogallery.api

import com.google.gson.annotations.SerializedName
import com.innocent.learn.photogallery.GalleryItem

class PhotoResponse{
    @SerializedName("photo")
    lateinit var galleryItem: List<GalleryItem>
}
