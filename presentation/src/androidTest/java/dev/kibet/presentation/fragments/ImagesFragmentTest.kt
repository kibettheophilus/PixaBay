package dev.kibet.presentation.fragments

import android.content.Context
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import dev.kibet.presentation.R
import dev.kibet.presentation.activities.MainActivity
import dev.kibet.presentation.di.presentationModule
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.GlobalContext
import org.koin.core.context.GlobalContext.startKoin
import org.koin.core.context.GlobalContext.stopKoin

class ImagesFragmentTest {

    @Rule
    @JvmField
    var getActivityScenarioRule = ActivityScenarioRule(MainActivity::class.java)

    @Before
    fun startKoinForTest() {
        if (GlobalContext.getOrNull() == null) {
            startKoin {
                androidLogger()
                androidContext(androidContext = ApplicationProvider.getApplicationContext())
                modules(presentationModule)
            }
        }
    }

    @After
    fun stopKoinAfterTest() = stopKoin()

    @Test
    fun testSearchbarIsShowing() {
        onView(withId(R.id.search_edit_text)).check(matches(ViewMatchers.isDisplayed()))
    }

    @Test
    fun testRecyclerviewIsShowing() {
        onView(withId(R.id.images_recyclerview)).check(matches(ViewMatchers.isDisplayed()))
    }
}
