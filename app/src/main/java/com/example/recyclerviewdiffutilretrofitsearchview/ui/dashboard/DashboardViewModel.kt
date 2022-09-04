package com.example.recyclerviewdiffutilretrofitsearchview.ui.dashboard


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.recyclerviewdiffutilretrofitsearchview.MainRepository
import com.example.recyclerviewdiffutilretrofitsearchview.Movie
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DashboardViewModel(private val mainRepository: MainRepository) : ViewModel() {


    var movieList = MutableLiveData<List<Movie>>()
    fun getAllMovie() {
        CoroutineScope(Dispatchers.IO).launch {
            val response = mainRepository.getAllMovie()
            withContext(Dispatchers.Main) {
                if (response.isSuccessful) {
                    movieList.postValue(response.body())
                }
            }
        }
    }
}