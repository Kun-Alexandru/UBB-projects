package com.example.tasktrifleappnative

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.tasktrifleappnative.databinding.ActivityAddTaskBinding

class AddTaskActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddTaskBinding
    private lateinit var db: TasksDatabseHelper

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAddTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = TasksDatabseHelper(this)
        binding.saveButton.setOnClickListener{
            val title = binding.editTextTitle.text.toString()
            val description = binding.editTextDescription.text.toString()
            val category = binding.editTextCategory.text.toString()
            val dueDate = binding.editTextDueDate.text.toString()
            val priority = binding.editTextPriority.text.toString()
            val status = binding.editTextStatus.text.toString()
            val Task = Task(0,title,description,category,dueDate,priority,status)
            db.insertTask(Task)
            finish()
            Toast.makeText(this,"Task saved", Toast.LENGTH_SHORT).show()
        }
    }
}