package com.example.tasktrifleappnative

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale
import androidx.recyclerview.widget.RecyclerView

class TasksAdapter(private var tasks: List<Task>, private val context: MainActivity) : RecyclerView.Adapter<TasksAdapter.TaskViewHolder>() {
    class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        val duedateTextView: TextView = itemView.findViewById(R.id.dateTextView)
        val priorityTextView: TextView = itemView.findViewById(R.id.priorityTextView)
        val categoryTextView: TextView = itemView.findViewById(R.id.categoryTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.task_item, parent, false)
        return TaskViewHolder(view)
    }

    override fun getItemCount(): Int = tasks.size

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = tasks[position]
        holder.titleTextView.text = task.title

        val inputDateFormat = SimpleDateFormat("dd.MM.yyyy", Locale.getDefault())
        val inputDate = inputDateFormat.parse(task.dueDate)

        val outputDateFormat = SimpleDateFormat("E, MMM dd, yyyy", Locale.getDefault())
        val formattedDate = outputDateFormat.format(inputDate)

        holder.duedateTextView.text = formattedDate
        holder.categoryTextView.text = task.category

        //Kunfu

        holder.priorityTextView.text = "${task.priority} priority"
        when (task.priority.lowercase()) {
            "high" -> holder.priorityTextView.setTextColor(ContextCompat.getColor(context, R.color.red))
            "medium" -> holder.priorityTextView.setTextColor(ContextCompat.getColor(context, R.color.yellow))
            "low" -> holder.priorityTextView.setTextColor(ContextCompat.getColor(context, R.color.goodgreen))
            else -> holder.priorityTextView.setTextColor(ContextCompat.getColor(context, R.color.white))
        }

        when (task.category.lowercase()) {
            "work" -> holder.categoryTextView.setTextColor(ContextCompat.getColor(context, R.color.blue))
            "personal" -> holder.categoryTextView.setTextColor(ContextCompat.getColor(context, R.color.lightblue))
        }

        holder.itemView.setOnClickListener {
            val intent = Intent(context, UpdateTaskActivity::class.java)
            intent.putExtra("task_id", task.id)
            context.startActivityForResult(intent,2)
        }
    }

    fun refreshData(newTasks: List<Task>){
        tasks = newTasks
        notifyDataSetChanged()
    }

    fun updateData(newTasks: List<Task>) {
        tasks = newTasks
        notifyDataSetChanged()
    }
}