package com.example.finalproject

import androidx.test.espresso.Espresso.closeSoftKeyboard
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import kotlinx.android.synthetic.main.activity_dashboard.*
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@LargeTest
@RunWith(JUnit4::class)
class LoginTesting {
    @get:Rule
    val testRule =ActivityScenarioRule(LoginActivity::class.java)

    @Test

    fun checkArithmetic() {
        onView(withId(R.id.Phone))
            .perform(typeText("486"))

        onView(withId(R.id.Password))
            .perform(typeText("123"))

        closeSoftKeyboard()

        onView(withId(R.id.btnLogin))
            .perform(click())

        Thread.sleep(5000)

        onView(withId(R.id.Home))
            .check(matches(withText("Home Fragment")))

    }

}