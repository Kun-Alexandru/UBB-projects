package com.example.tasktrifleappnative

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tasktrifleappnative.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var taskRepository: TaskRepository
    private lateinit var tasksAdapter: TasksAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        taskRepository = TaskRepositorySingleton.getInstance(this)

        tasksAdapter = TasksAdapter(taskRepository.getAllTasks(), this)

        binding.tasksRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.tasksRecyclerView.adapter = tasksAdapter

        binding.addButton.setOnClickListener {
            val intent = Intent(this, AddTaskActivity::class.java)
            startActivityForResult(intent, ADD_TASK_REQUEST_CODE)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == ADD_TASK_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            if (data != null) {
                val bundle = data.getBundleExtra("taskBundle")
                val task = bundle?.getParcelable<Task>("task")
                if (task != null) {
                    taskRepository.addTask(task)
                    val position = tasksAdapter.itemCount - 1
                    tasksAdapter.notifyItemInserted(position)
                    Toast.makeText(this, "Task added!", Toast.LENGTH_SHORT).show()
                }
            }
        }
        else if (requestCode == UPDATEDELETE_TASK_REQUEST_CODE && resultCode == Activity.RESULT_OK) {
            if (data != null) {

                if (data.getStringExtra("operation") == "Update") {
                    val bundle = data.getBundleExtra("updatedTaskBundle")
                    val task = bundle?.getParcelable<Task>("task")
                    val id = data.getIntExtra("id", -1)
                    if (task != null && id != -1) {

                        val index = taskRepository.updateTask(task)
                        tasksAdapter.notifyItemChanged(index)
                        Toast.makeText(this, "Task updated!", Toast.LENGTH_SHORT).show()

                    }
                }
                else if (data.getStringExtra("operation") == "Delete") {
                    val id = data.getIntExtra("id", -1)
                    if (id != -1) {

                        val index = taskRepository.deleteTask(id)
                        tasksAdapter.notifyItemRemoved(index)
                        Toast.makeText(this, "Task deleted!", Toast.LENGTH_SHORT).show()

                    }
                }
            }
        }

    }


    companion object {
        private const val ADD_TASK_REQUEST_CODE = 1
        private const val UPDATEDELETE_TASK_REQUEST_CODE = 2
    }
}