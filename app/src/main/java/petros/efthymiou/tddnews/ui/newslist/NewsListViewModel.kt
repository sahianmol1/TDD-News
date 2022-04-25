package petros.efthymiou.tddnews.ui.newslist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import petros.efthymiou.tddnews.data.models.Article
import petros.efthymiou.tddnews.repository.NewsRepository
import javax.inject.Inject

@HiltViewModel
class NewsListViewModel @Inject constructor(private val repository: NewsRepository): ViewModel() {
    val loader = MutableLiveData<Boolean>()
    val newsList = MutableLiveData<Result<List<Article>>>()

    fun getNewsList() {
        loader.value = true
        viewModelScope.launch {
            repository.fetchNewsList()
                .onEach { loader.value = false }
                .collect{
                newsList.value = it
            }
        }
    }
}