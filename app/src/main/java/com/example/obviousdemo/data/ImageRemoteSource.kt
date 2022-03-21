package com.example.obviousdemo.data

import android.content.Context
import android.content.res.AssetManager
import com.example.obviousdemo.model.ResponseImages
import com.example.obviousdemo.model.ResponseItem
import com.google.gson.Gson
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext


class ImageRemoteSource @Inject constructor(@ApplicationContext val context: Context) {

    suspend fun getImages(): ResponseImages = withContext(Dispatchers.IO){
        val gson = Gson()
        val responseImages = gson.fromJson<ArrayList<ResponseItem>>(
            context.assets.readAssetsFile("data.json"),
            object : TypeToken<ArrayList<ResponseItem>>() {}.type
        )
        return@withContext ResponseImages(responseImages.sortedByDescending { it.date })
    }

    private fun AssetManager.readAssetsFile(fileName: String): String =
        open(fileName).bufferedReader().use { it.readText() }
}