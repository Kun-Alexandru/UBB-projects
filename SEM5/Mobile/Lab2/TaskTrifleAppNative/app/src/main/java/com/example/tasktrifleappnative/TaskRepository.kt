package com.example.tasktrifleappnative

import android.content.Context
import android.util.Log

class TaskRepository(context: Context) {
    private val db: TasksDatabseHelper = TasksDatabseHelper(context)
    private var tasksList = mutableListOf<Task>()

    init {
        tasksList = db.getAllTasks().toMutableList()
        printTasks()
    }

    fun getAllTasks(): List<Task> {
        printTasks()
        return tasksList
    }

    fun addTask(task: Task) {
        var t = db.insertTask(task)
        tasksList.add(t)
        printTasks()
    }

    fun updateTask(task: Task): Int {
        db.updateTask(task)
        val index = tasksList.indexOfFirst { it.id == task.id }
        if (index != -1) {
            tasksList[index] = task
        }
        printTasks()
        return index
    }

    fun deleteTask(taskId: Int): Int {
        db.deleteTask(taskId)
        val index = tasksList.indexOfFirst { it.id == taskId }
        tasksList.removeIf { it.id == taskId }
        printTasks()
        return index
    }

    fun getTaskById(taskId: Int): Task {
        return tasksList.first { it.id == taskId }
    }

    private fun printTasks() {
        tasksList.forEach {
            Log.d("TaskRepository", "Task ID: ${it.id}, Title: ${it.title}, Due Date: ${it.dueDate}, Priority: ${it.priority}, Status: ${it.status}")
        }
    }
}
