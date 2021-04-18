package com.example.finalproject

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.filters.LargeTest
import com.example.finalproject.activity.DashActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@LargeTest
@RunWith(JUnit4::class)

class ProductTest {
    @get:Rule
    val testRule=ActivityScenarioRule(DashActivity::class.java)

    @Test
    fun productviewtest(){
        onView(withId(R.id.productadd))
                .perform(click())


        onView(withId(R.id.linearcontainer))
                .check(matches(isDisplayed()))


    }
}