package petros.efthymiou.tddnews

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.hamcrest.core.AllOf.allOf
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import petros.efthymiou.tddnews.ui.MainActivity

@RunWith(AndroidJUnit4::class)
class NewsDetailsFeature : BaseUITest() {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun showAllTheViewsIntoTheDetailsScreen() {
        Thread.sleep(4000)
        onView(
            allOf(
                withId(R.id.img_news),
                ViewMatchers.isDescendantOfA(nthChildOf(withId(R.id.rv_news_list), 0))
            )
        ).perform(ViewActions.click())
        Thread.sleep(1000)


        onView(withId(R.id.img_news)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_description)).check(matches(isDisplayed()))
        onView(withId(R.id.tv_news_title)).check(matches(isDisplayed()))

        onView(withText("News Details")).check(matches(isDisplayed()))
    }
}