package com.example.obviousdemo.data

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.runBlocking
import org.junit.Assert.*

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ImageRemoteSourceTest {
    lateinit var imageRemoteSource: ImageRemoteSource

    @Before
    fun setUp() {
        imageRemoteSource = ImageRemoteSource(ApplicationProvider.getApplicationContext())
    }

    @Test
    fun testDataFromFile() = runBlocking {
        val responseImages = imageRemoteSource.getImages()
        assertNotNull(responseImages)
        assertFalse(responseImages?.response?.isEmpty() ?: true)
        assertEquals(responseImages?.response?.size ?: 0, 26)
    }
}