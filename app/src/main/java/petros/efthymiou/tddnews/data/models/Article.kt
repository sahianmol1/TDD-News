package petros.efthymiou.tddnews.data.models

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Article(
    @SerializedName("author")
    val author: String,
    val content: String,
    val description: String,
    @SerializedName("publishedAt")
    val publishedAt: String,
    @SerializedName("source")
    val source: Source?,
    val title: String,
    val url: String,
    val urlToImage: String
) : Serializable