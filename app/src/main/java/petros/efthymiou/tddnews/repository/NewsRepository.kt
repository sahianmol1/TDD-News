package petros.efthymiou.tddnews.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import petros.efthymiou.tddnews.data.NewsAPI
import petros.efthymiou.tddnews.data.models.Article
import java.lang.Exception
import java.lang.RuntimeException
import javax.inject.Inject

class NewsRepository @Inject constructor(private val api: NewsAPI) {
    suspend fun fetchNewsList(): Flow<Result<List<Article>>> {
        return flow {
            val response = api.getAllLatestNews()
            if (response.isSuccessful && response.body() != null) {
                emit(Result.success(response.body()!!.articles))
            } else {
                throw Exception("${response.code()}: ${response.errorBody()}")
            }
        }.catch { e ->
            emit(Result.failure(RuntimeException(e.printStackTrace().toString())))
        }
    }
}