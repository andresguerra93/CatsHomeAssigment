package com.agc.catshomeassignmet.ui.screens.list

import android.view.View
import androidx.core.content.ContentProviderCompat.requireContext
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
import androidx.test.espresso.intent.Intents
import androidx.test.espresso.intent.Intents.intended
import androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import com.agc.catshomeassignmet.R
import com.agc.catshomeassignmet.ui.screens.MainActivity
import com.agc.catshomeassignmet.ui.screens.detail.CatDetailFragment
import dagger.hilt.android.testing.HiltAndroidRule
import org.junit.After
import org.junit.Rule

@RunWith(AndroidJUnit4::class)
@ExperimentalCoroutinesApi
class CatListFragmentTest {

    private lateinit var navController: NavController

    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    var mainActivityRule = ActivityScenarioRule(MainActivity::class.java)



    @Before
    fun setUp() {
        hiltRule.inject()
        Intents.init()
/*        val context = requireContext()
        navController = Navigation.findNavController(View(context))
        val scenario = launchFragmentInContainer<CatListFragment>()
        scenario.onFragment {
            Navigation.setViewNavController(it.requireView(), navController)
        }*/
    }

    @After
    fun tearDown(){
        Intents.release()
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
    /*    val expectedAction =
            CatListFragmentDirections
                .actionCatListFragmentToCatDetailFragment("catId")
        val actualAction = navController.currentDestination?.getAction(expectedAction.actionId)
        assertEquals(expectedAction, actualAction)*/
        intended(hasComponent(CatDetailFragment::class.java.name))
    }

    @Test
    fun testRecyclerViewVisible() {
        onView(withId(R.id.rvCats)).check(matches(isDisplayed()))
    }

    @Test
    fun when_mainactivity_is_created_then_open_cat_list_fragment() {
        onView(withId(R.id.catListFragment)).check(matches(isDisplayed()))
    }
}