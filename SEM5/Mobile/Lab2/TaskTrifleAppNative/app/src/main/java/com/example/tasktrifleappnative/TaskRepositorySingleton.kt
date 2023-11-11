package com.example.tasktrifleappnative

import android.content.Context

object TaskRepositorySingleton {
    private var instance: TaskRepository? = null

    fun getInstance(context: Context): TaskRepository {
        return instance ?: synchronized(this) {
            instance ?: TaskRepository(context).also { instance = it }
        }
    }
}