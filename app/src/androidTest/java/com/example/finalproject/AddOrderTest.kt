package com.example.finalproject

import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.filters.LargeTest
import com.example.finalproject.activity.OrderActivity
import com.example.finalproject.activity.RegistrationActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4
@LargeTest
@RunWith(JUnit4::class)
class AddOrderTest {

    @get:Rule
    val testRule = ActivityScenarioRule(OrderActivity::class.java)

    @Test
    fun TestOrderUI() {

        onView(ViewMatchers.withId(R.id.name)).perform(ViewActions.closeSoftKeyboard())
                .perform(ViewActions.typeText("test"))
        onView(ViewMatchers.withId(R.id.material)).perform(ViewActions.closeSoftKeyboard())
                .perform(ViewActions.typeText("test"))
        onView(ViewMatchers.withId(R.id.cost)).perform(ViewActions.closeSoftKeyboard())
                .perform(ViewActions.typeText("test"))
        onView(ViewMatchers.withId(R.id.featutre)).perform(ViewActions.closeSoftKeyboard())
                .perform(ViewActions.typeText("test"))
        onView(ViewMatchers.withId(R.id.btnOrder)).perform(ViewActions.closeSoftKeyboard())
                .perform(ViewActions.click())
        Thread.sleep(3000)
        onView(ViewMatchers.withId(R.id.btnOrder))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))


    }
}