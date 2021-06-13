package com.unifa.pexelsdemo.ui.main

import androidx.test.core.app.ActivityScenario.launch
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.runner.AndroidJUnit4
import com.unifa.pexelsdemo.R
import org.junit.Assert
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {
    @Test
    fun testKeywordEmpty() {
        val scenario = launch(MainActivity::class.java)
        Assert.assertNotNull(scenario)
        onView(withId(R.id.etKeyWord)).perform(replaceText(""), closeSoftKeyboard())
        onView(withId(R.id.button)).perform(click())
    }

    @Test
    fun testKeywordNotEmpty() {
        val scenario = launch(MainActivity::class.java)
        Assert.assertNotNull(scenario)
        onView(withId(R.id.etKeyWord)).perform(replaceText("nature"), closeSoftKeyboard())
        onView(withId(R.id.button)).perform(click())
    }
}