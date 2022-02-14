package com.example.photogallery

import com.example.photogallery.api.PhotoResponse
import com.google.gson.JsonDeserializationContext
import com.google.gson.JsonDeserializer
import com.google.gson.JsonElement
import com.google.gson.JsonObject
import java.lang.reflect.Type
import java.util.Arrays

class PhotoDeserializer: JsonDeserializer<PhotoResponse> {

    override fun deserialize(json: JsonElement?, typeOfT: Type?, context: JsonDeserializationContext?): PhotoResponse {
        val jsonObject:JsonObject = json?.asJsonObject!!
        val jsonArray = jsonObject.get("photo").asJsonArray
        val photoResponse = PhotoResponse()
        val photos:MutableList<GalleryItem> = mutableListOf()
        jsonArray.forEach {photo ->
            val photoElement = photo.asJsonObject
            val galleryItem = GalleryItem(
                photoElement.get("title").asString,
                photoElement.get("id").asString,
                photoElement.get("url_s").asString
            )
            photos.add(galleryItem)
        }
        photoResponse.galleryItems = photos
        return photoResponse
    }
}