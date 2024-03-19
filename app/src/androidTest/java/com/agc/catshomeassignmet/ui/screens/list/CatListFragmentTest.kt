package com.agc.catshomeassignmet.ui.screens.list

import android.view.View
import org.junit.Assert.*
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import com.agc.catshomeassignmet.R
@RunWith(AndroidJUnit4::class)
@ExperimentalCoroutinesApi
class CatListFragmentTest {

    private lateinit var navController: NavController

    @Before
    fun setUp() {
        val context = ApplicationProvider.getApplicationContext()
        navController = Navigation.findNavController(View(context))
        val scenario = launchFragmentInContainer<CatListFragment>()
        scenario.onFragment {
            Navigation.setViewNavController(it.requireView(), navController)
        }
    }

    @Test
    fun testCatListFragmentNavigation() {
        // Simulate clicking an item in the RecyclerView
        onView(withId(R.id.rvCats))
            .perform(
                RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(
                    0,
                    click()
                )
            )

        // Verify that navigation occurred to CatDetailFragment with the correct argument
        val expectedAction =
            CatListFragmentDirections.actionCatListFragmentToCatDetailFragment("catId")
        val actualAction = navController.currentDestination?.getAction(expectedAction.actionId)
        assertEquals(expectedAction, actualAction)
    }

    @Test
    fun testRecyclerViewVisible() {
        onView(withId(R.id.rvCats)).check(matches(isDisplayed()))
    }
}