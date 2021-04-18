package com.example.finalproject

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.filters.LargeTest
import com.example.finalproject.activity.RegistrationActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@LargeTest
@RunWith(JUnit4::class)
class RegistrationTest {

    @get:Rule
    val testRule = ActivityScenarioRule(RegistrationActivity::class.java)

    @Test
    fun TestRegisterUI(){

        onView(withId(R.id.Fname)).perform(closeSoftKeyboard())
            .perform(typeText("test"))

        onView(withId(R.id.Lname)).perform(closeSoftKeyboard())
            .perform(typeText("test"))

        onView(withId(R.id.Address)).perform(closeSoftKeyboard())
                .perform(typeText("ktm"))

        onView(withId(R.id.Phone)).perform(closeSoftKeyboard())
            .perform(typeText("123123"))



        onView(withId(R.id.Password)).perform(closeSoftKeyboard())
            .perform(typeText("abc"))

        onView(withId(R.id.ConfirmPassword)).perform(closeSoftKeyboard())
            .perform(typeText("abc"))

        onView(withId(R.id.btnAdduser)).perform(closeSoftKeyboard())
            .perform(click())
        Thread.sleep(3000)
        onView(withId(R.id.btnAdduser))
            .check(matches(isDisplayed()))
    }



}