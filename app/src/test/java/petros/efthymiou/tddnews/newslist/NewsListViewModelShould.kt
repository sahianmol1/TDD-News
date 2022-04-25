package petros.efthymiou.tddnews.newslist

import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito.*
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import petros.efthymiou.tddnews.data.models.Article
import petros.efthymiou.tddnews.repository.NewsRepository
import petros.efthymiou.tddnews.ui.newslist.NewsListViewModel
import petros.efthymiou.tddnews.utils.BaseUnitTest
import petros.efthymiou.tddnews.utils.captureValues
import petros.efthymiou.tddnews.utils.getValueForTest

@ExperimentalCoroutinesApi
@RunWith(MockitoJUnitRunner::class)
class NewsListViewModelShould : BaseUnitTest() {

    private val repository: NewsRepository = mock(NewsRepository::class.java)

    @Mock
    private val newsList: List<Article> = mutableListOf()
    private val expected = Result.success(newsList)
    private val expectedError =
        Result.failure<List<Article>>(RuntimeException("Something went wrong"))

    @Before
    fun setUp() {
        MockitoAnnotations.openMocks(newsList)
    }

    @Test
    fun getNewsListFromRepository() = runTest {
        val viewModel = NewsListViewModel(repository)
        viewModel.getNewsList()

        verify(repository, times(1)).fetchNewsList()
    }

    @Test
    fun emitNewsListFromTheRepository() = runTest {
        val viewModel = mockSuccessfulCase()
        viewModel.getNewsList()

        assertEquals(expected, viewModel.newsList.getValueForTest())
    }

    @Test
    fun emitErrorWhenReceivesError() = runTest {
        val viewModel = mockErrorCase()

        viewModel.getNewsList()
        assertEquals(expectedError, viewModel.newsList.getValueForTest())
    }

    @Test
    fun showLoaderWhileTheListIsStillFEtching() = runTest{
        val viewModel = mockSuccessfulCase()

        viewModel.loader.captureValues {
            viewModel.getNewsList()
            assertEquals(true, values[0])
        }
    }

    @Test
    fun hideLoaderWhenTheListIsCompletelyFetched() = runTest{
        val viewModel = mockErrorCase()

        viewModel.loader.captureValues {
            viewModel.getNewsList()
            assertEquals(false, values.last())
        }
    }

    private suspend fun mockErrorCase(): NewsListViewModel {
        `when`(repository.fetchNewsList()).thenReturn(
            flow {
                emit(expectedError)
            }
        )

        return NewsListViewModel(repository)
    }

    private suspend fun mockSuccessfulCase(): NewsListViewModel {
        `when`(repository.fetchNewsList()).thenReturn(
            flow {
                emit(expected)
            }
        )

        return NewsListViewModel(repository)
    }
}