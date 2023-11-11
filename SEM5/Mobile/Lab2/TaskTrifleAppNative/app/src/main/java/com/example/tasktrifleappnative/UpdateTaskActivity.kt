package com.example.tasktrifleappnative

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.tasktrifleappnative.databinding.ActivityUpdateBinding
import java.text.SimpleDateFormat
import java.util.Date

class UpdateTaskActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateBinding
    private lateinit var db: TasksDatabseHelper
    private var taskId: Int = -1
    private lateinit var taskRepository: TaskRepository

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

        taskRepository = TaskRepositorySingleton.getInstance(this)
        val task = taskRepository.getTaskById(taskId)
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

            if (title.isNotBlank() && description.isNotBlank() && dueDate.isNotBlank() &&
                category.isNotBlank() && priority.isNotBlank() && status.isNotBlank()) {
                if (isValidDate(dueDate)) {
                    if (isValidDateInFuture(dueDate)) {
                        if (isValidPriority(priority)) {
                            if (isValidCategory(category)) {
                                val UpdatedTask = Task(taskId, title, description, category, dueDate, priority, status)
                                //taskRepository.updateTask(UpdatedTask)

                                val returnIntent = Intent()
                                val bundle = Bundle()
                                bundle.putParcelable("task", UpdatedTask)
                                returnIntent.putExtra("operation", "Update")
                                returnIntent.putExtra("updatedTaskBundle", bundle)
                                returnIntent.putExtra("id", taskId)
                                setResult(Activity.RESULT_OK, returnIntent)

                                finish()
                            } else {
                                Toast.makeText(this, "Invalid category. Use 'personal' or 'work'.", Toast.LENGTH_SHORT).show()
                            }
                        } else {
                            Toast.makeText(this, "Invalid priority. Use 'high', 'medium', or 'low'.", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        Toast.makeText(this, "Due date must be in the future.", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this, "Invalid due date format. Please use dd.MM.yyyy.", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Please fill in all fields.", Toast.LENGTH_SHORT).show()
            }
        }

        binding.backButton.setOnClickListener {
            finish()
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
        //taskRepository = TaskRepositorySingleton.getInstance(this)
        //taskRepository.deleteTask(taskId)

        val returnIntent = Intent()
        val bundle = Bundle()
        returnIntent.putExtra("operation", "Delete")
        returnIntent.putExtra("id", taskId)
        setResult(Activity.RESULT_OK, returnIntent)

        finish()
        Toast.makeText(this, "Task deleted", Toast.LENGTH_SHORT).show()
    }

    private fun isValidDate(date: String): Boolean {
        try {
            val dateFormat = SimpleDateFormat("dd.MM.yyyy")
            dateFormat.isLenient = false
            dateFormat.parse(date)
            return true
        } catch (e: Exception) {
            return false
        }
    }

    private fun isValidDateInFuture(date: String): Boolean {
        val dateFormat = SimpleDateFormat("dd.MM.yyyy")
        val dueDate = dateFormat.parse(date)
        val currentDate = Date()
        return dueDate?.after(currentDate) ?: false
    }

    private fun isValidPriority(priority: String): Boolean {
        return priority.lowercase() in setOf("high", "medium", "low")
    }

    private fun isValidCategory(category: String): Boolean {
        return category.lowercase() in setOf("personal", "work")
    }
}
