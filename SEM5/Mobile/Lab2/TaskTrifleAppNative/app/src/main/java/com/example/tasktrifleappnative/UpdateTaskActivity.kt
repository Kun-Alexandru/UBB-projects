package com.example.tasktrifleappnative

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.tasktrifleappnative.databinding.ActivityUpdateBinding

class UpdateTaskActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateBinding
    private lateinit var db: TasksDatabseHelper
    private var taskId: Int = -1



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = TasksDatabseHelper(this)
        taskId = intent.getIntExtra("task_id", -1)
        if(taskId == -1){
            finish()
            return
        }

        val task = db.getTaskById(taskId)
        binding.editTextTitleUpdate.setText(task.title)
        binding.editTextDescriptionUpdate.setText(task.description)
        binding.editTextDueDateUpdate.setText(task.dueDate)
        binding.editTextCategoryUpdate.setText(task.category)
        binding.editTextPriorityUpdate.setText(task.priority)
        binding.editTextStatusUpdate.setText(task.status)

        binding.updateSaveButton.setOnClickListener{
            val title = binding.editTextTitleUpdate.text.toString()
            val description = binding.editTextDescriptionUpdate.text.toString()
            val category = binding.editTextCategoryUpdate.text.toString()
            val dueDate = binding.editTextDueDateUpdate.text.toString()
            val priority = binding.editTextPriorityUpdate.text.toString()
            val status = binding.editTextStatusUpdate.text.toString()
            val UpdatedTask = Task(taskId,title,description,category,dueDate,priority,status)
            db.updateTask(UpdatedTask)
            finish()
            Toast.makeText(this,"Changes saved",Toast.LENGTH_SHORT).show()
        }

        binding.deleteButton.setOnClickListener{
            showDeleteConfirmationDialog()
        }

    }

    private fun showDeleteConfirmationDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Confirm Deletion")
        builder.setMessage("Are you sure you want to delete this task?")

        builder.setPositiveButton("Yes") { _, _ ->
            deleteTask()
        }

        builder.setNegativeButton("No") { dialog, _ ->
            dialog.dismiss()
        }

        val dialog = builder.create()
        dialog.show()
    }

    private fun deleteTask() {
        db.deleteTask(taskId)
        finish()
        Toast.makeText(this, "Task deleted", Toast.LENGTH_SHORT).show()
    }


}