package petros.efthymiou.tddnews.ui.newslist.placeholder

import java.io.Serializable
import java.util.ArrayList
import java.util.HashMap

data class MyArticle(
    val imageUrl : String,
    val title: String,
    val description: String
): Serializable