package petros.efthymiou.tddnews.utils

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Rule
import petros.efthymiou.tddnews.utils.MainCoroutineScopeRule

@ExperimentalCoroutinesApi
open class BaseUnitTest {

    @get:Rule
    val mainCoroutineScope = MainCoroutineScopeRule()

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()
}