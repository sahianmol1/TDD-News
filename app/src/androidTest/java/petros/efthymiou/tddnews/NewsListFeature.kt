package petros.efthymiou.tddnews

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import petros.efthymiou.tddnews.BaseUITest.RecyclerViewMatchers.hasItemCount
import petros.efthymiou.tddnews.ui.MainActivity

@RunWith(AndroidJUnit4::class)
class NewsListFeature: BaseUITest() {

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun displayNewsText() {
        onView(withText("News")).check(matches(isDisplayed()))
    }

    @Test
    fun displayListOfNews() {
        Thread.sleep(4000)
        onView(withId(R.id.rv_news_list)).check(matches(hasItemCount(20)))
    }

    @Test
    fun showsALoaderWhenTheListIsLoading() {
        onView(withId(R.id.loader)).check(matches(isDisplayed()))
    }

    @Test
    fun hidesALoaderWhenTheListIsCompletelyFetched() {
        Thread.sleep(4000)
        onView(withId(R.id.loader)).check(matches(withEffectiveVisibility(Visibility.GONE)))
    }
}