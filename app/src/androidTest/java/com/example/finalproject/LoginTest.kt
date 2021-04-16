package com.example.finalproject

import androidx.test.espresso.Espresso.closeSoftKeyboard
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.filters.LargeTest
import com.example.finalproject.activity.LoginActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4



    class LoginTest {
        @get:Rule
        val testRule = ActivityScenarioRule(LoginActivity::class.java)

        @Test

        fun checkUser() {
            onView(withId(R.id.Phone)).perform(ViewActions.closeSoftKeyboard())
                .perform(typeText("123"))

            onView(withId(R.id.Password)).perform(ViewActions.closeSoftKeyboard())
                .perform(typeText("123"))

            closeSoftKeyboard()

            onView(withId(R.id.btnLogin)).perform(click())

            Thread.sleep(3000)

            onView(withId(R.id.BtnDashboard))
                .check(matches(isDisplayed()))

        }

    }
