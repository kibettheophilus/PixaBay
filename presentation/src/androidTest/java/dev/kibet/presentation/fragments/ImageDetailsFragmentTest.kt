package dev.kibet.presentation.fragments

import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import dev.kibet.presentation.R
import dev.kibet.presentation.activities.MainActivity
import org.hamcrest.core.AllOf.allOf
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class ImageDetailsFragmentTest {

    @Rule
    @JvmField
    var activityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun navigateToDetailsFragment() {
        Thread.sleep(5000)

        val recyclerView = onView(
            allOf(withId(R.id.images_recyclerview))
        )

        recyclerView.perform(
            actionOnItemAtPosition<RecyclerView.ViewHolder>(
                0, click()
            )
        )
    }

    @Test
    fun testImageIsShowing() {
        onView(withId(R.id.detail_image_view)).check(ViewAssertions.matches(isDisplayed()))
    }
}
