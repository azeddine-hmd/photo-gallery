package com.innocent.learn.photogallery.api

import com.google.gson.Gson
import retrofit2.Call
import retrofit2.http.GET

interface FlickrApi {

    @GET("services/rest/?" +
            "method=flickr.interestingness.getList" +
            "&api_key=48a4511e64564c9083458a188d938ecc" +
            "&format=json" +
            "&nojsoncallback=1" +
            "&extras=url_s")
    fun fetchPhotos(): Call<FlickrResponse>
}