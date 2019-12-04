package com.reynaldiwijaya.bravo

import android.widget.AutoCompleteTextView
import androidx.recyclerview.widget.RecyclerView
import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.*
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.rule.ActivityTestRule
import com.reynaldiwijaya.bravo.R.id.*
import com.reynaldiwijaya.bravo.view.search.SearchActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class SearchMatchActivityTest {
    @Rule
    @JvmField var activityRule = ActivityTestRule(SearchActivity::class.java)

    @Test
    fun testAppBehaviour() {
        // Search Match Chelsea
        onView(withId(searchMatch)).check(matches(isDisplayed()))
        onView(isAssignableFrom(AutoCompleteTextView::class.java)).perform(typeText("Chelsea"))
        onView(isAssignableFrom(AutoCompleteTextView::class.java)).perform(pressImeActionButton())
        onView(withId(layout_progress)).check(matches(isDisplayed()))
        Thread.sleep(3000)

        // Show the data to recyclerview based on what user input
        onView(withId(rvSearch)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(7))
        // Move to Detail Activity
        onView(withId(rvSearch)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(5, click()))

        Thread.sleep(2000)
        onView(withId(fabFavorite)).check(matches(isDisplayed()))
        // Click add Favorite
        onView(withId(fabFavorite)).perform(click())
        Thread.sleep(1500)
//        onView(withText("Success to add this match to favorite")).check(matches(isDisplayed()))
//        onView(withText("Success to remove data from favorite")).check(matches(isDisplayed()))

        // Back to Previous Activity
        Espresso.pressBack()
        Thread.sleep(1000)
        onView(withId(searchContainer)).check(matches(isDisplayed()))
        onView(withId(rvSearch)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(0))


        // Type doesnt exist match
        onView(isAssignableFrom(AutoCompleteTextView::class.java)).perform(typeText(" Menang terus"))
        onView(isAssignableFrom(AutoCompleteTextView::class.java)).perform(pressImeActionButton())
        onView(withId(layout_progress)).check(matches(isDisplayed()))
        Thread.sleep(3000)

        onView(withId(layout_empty_data)).check(matches(isDisplayed()))

    }

}