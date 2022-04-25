package petros.efthymiou.tddnews.repository

import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.Test
import org.mockito.Mockito.*
import petros.efthymiou.tddnews.data.NewsAPI
import petros.efthymiou.tddnews.data.models.NewsResponse
import petros.efthymiou.tddnews.utils.BaseUnitTest
import retrofit2.Response
import java.lang.RuntimeException

@ExperimentalCoroutinesApi
class NewsRepositoryShould : BaseUnitTest() {

    private val api: NewsAPI = mock(NewsAPI::class.java)

    private val newsResponse: NewsResponse = mock(NewsResponse::class.java)

    private val expected = Result.success(newsResponse.articles)

    @Test
    fun fetchNewsListFromAPI() = runTest {
        val repository = mockSuccessfulCase()
        repository.fetchNewsList().first()

        verify(api, times(1)).getAllLatestNews()
    }

    @Test
    fun convertValuesToFlowAndEmitThem() = runTest {
        val repository = mockSuccessfulCase()

        assertEquals(expected, repository.fetchNewsList().first())
    }

    @Test
    fun emitsFailureOnNetworkFailure() = runTest {
        val repository = mockFailureCase()

        assertTrue(!repository.fetchNewsList().first().exceptionOrNull()!!.message.isNullOrBlank())
    }

    private suspend fun mockFailureCase(): NewsRepository {
        `when`(api.getAllLatestNews()).thenThrow(
            RuntimeException("Something Went Wrong")
        )

        return NewsRepository(api)
    }

    private fun mockSuccessfulCase(): NewsRepository = runBlocking{
        `when`(api.getAllLatestNews()).thenReturn(
            Response.success(newsResponse)
        )

        return@runBlocking NewsRepository(api)
    }
}