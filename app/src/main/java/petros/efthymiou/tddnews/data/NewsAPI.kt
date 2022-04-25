package petros.efthymiou.tddnews.data

import petros.efthymiou.tddnews.data.models.NewsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsAPI {

    @GET("top-headlines")
    suspend fun getAllLatestNews(
        @Query("country") country: String = "in",
        @Query("apiKey") apiKey: String = "5a574596b5434fd282e6f6c1c04d50c2",
    ): Response<NewsResponse>
}