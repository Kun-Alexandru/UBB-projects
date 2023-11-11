package com.example.tasktrifleappnative

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.tasktrifleappnative.databinding.ActivityAddTaskBinding
import java.text.SimpleDateFormat
import java.util.Date

class AddTaskActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddTaskBinding
    private lateinit var db: TasksDatabseHelper
    private lateinit var taskRepository: TaskRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = TasksDatabseHelper(this)
        binding.saveButton.setOnClickListener {
            val title = binding.editTextTitle.text.toString()
            val description = binding.editTextDescription.text.toString()
            val category = binding.editTextCategory.text.toString()
            val dueDateStr = binding.editTextDueDate.text.toString()
            val priority = binding.editTextPriority.text.toString()
            val status = binding.editTextStatus.text.toString()

            if (title.isNotBlank() && description.isNotBlank() && category.isNotBlank() &&
                dueDateStr.isNotBlank() && priority.isNotBlank() && status.isNotBlank()) {
                if (isValidDate(dueDateStr)) {
                    if (isValidPriority(priority)) {
                        if (isValidCategory(category)) {
                            if (isFutureDate(dueDateStr)) {
                                val dueDate = dueDateStr
                                val Task = Task(0, title, description, category, dueDate, priority, status)
                                //taskRepository = TaskRepositorySingleton.getInstance(this)
                                //taskRepository.addTask(Task)

                                val bundle = Bundle()
                                bundle.putParcelable("task", Task)
                                intent.putExtra("taskBundle", bundle)
                                setResult(Activity.RESULT_OK, intent)

                                finish()
                            } else {
                                Toast.makeText(this, "Due date must be in the future.", Toast.LENGTH_SHORT).show()
                            }
                        } else {
                            Toast.makeText(this, "Invalid category. Use 'personal' or 'work'.", Toast.LENGTH_SHORT).show()
                        }
                    } else {
                        Toast.makeText(this, "Invalid priority. Use 'high', 'medium', or 'low'.", Toast.LENGTH_SHORT).show()
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

    private fun isValidPriority(priority: String): Boolean {
        return priority.lowercase() in setOf("high", "medium", "low")
    }

    private fun isValidCategory(category: String): Boolean {
        return category.lowercase() in setOf("personal", "work")
    }

    private fun isFutureDate(date: String): Boolean {
        val dateFormat = SimpleDateFormat("dd.MM.yyyy")
        val currentDate = Date()
        val dueDate = dateFormat.parse(date)
        return dueDate != null && dueDate.after(currentDate)
    }
}
