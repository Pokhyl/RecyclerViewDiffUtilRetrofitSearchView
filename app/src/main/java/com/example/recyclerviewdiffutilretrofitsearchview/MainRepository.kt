package com.example.recyclerviewdiffutilretrofitsearchview

class MainRepository(var retrofitService: RetrofitService) {
    suspend fun getAllMovie() = retrofitService.getAllMovie()
}