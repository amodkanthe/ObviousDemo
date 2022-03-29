package com.example.obviousdemo.ui


import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import org.junit.Test
import org.junit.runner.RunWith

import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.obviousdemo.R
import com.example.obviousdemo.RecyclerViewItemCountAssertion
import com.example.obviousdemo.launchFragmentInHiltContainer
import dagger.hilt.android.testing.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Rule

@HiltAndroidTest
@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class ImageListFragmentTest {

    @get:Rule
    val hiltAndroidRule = HiltAndroidRule(this)

    @Before
    fun init() {
        hiltAndroidRule.inject()
    }

    @Test
    fun testImageListFregment() {
        launchFragmentInHiltContainer<ImageListFragment>()
        onView(withId(R.id.rvImages))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Thread.sleep(3000);
        onView(withId(R.id.rvImages)).check((RecyclerViewItemCountAssertion(26)));
    }
}