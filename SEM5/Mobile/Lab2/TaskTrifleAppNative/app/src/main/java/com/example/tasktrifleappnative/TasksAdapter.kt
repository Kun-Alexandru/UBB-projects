package com.example.tasktrifleappnative

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

class TasksAdapter(private var tasks: List<Task>, private val context: Context) : RecyclerView.Adapter<TasksAdapter.TaskViewHolder>() {
    class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val titleTextView: TextView = itemView.findViewById(R.id.titleTextView)
        val duedateTextView: TextView = itemView.findViewById(R.id.dateTextView)
        val priorityTextView: TextView = itemView.findViewById(R.id.priorityTextView)
        val statusTextView: TextView = itemView.findViewById(R.id.statusTextView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.task_item, parent, false)
        return TaskViewHolder(view)
    }

    override fun getItemCount(): Int = tasks.size

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val task = tasks[position]
        holder.titleTextView.text = task.title
        holder.duedateTextView.text = task.dueDate
        holder.statusTextView.text = task.status

        // Set priority text and color based on priority value
        holder.priorityTextView.text = task.priority
        when (task.priority.toLowerCase()) {
            "high" -> holder.priorityTextView.setTextColor(ContextCompat.getColor(context, R.color.red))
            "medium" -> holder.priorityTextView.setTextColor(ContextCompat.getColor(context, R.color.yellow))
            "low" -> holder.priorityTextView.setTextColor(ContextCompat.getColor(context, R.color.goodgreen))
            else -> holder.priorityTextView.setTextColor(ContextCompat.getColor(context, R.color.green))
        }


        // Add a click listener to open EditTaskActivity
        holder.itemView.setOnClickListener {
            val intent = Intent(context, UpdateTaskActivity::class.java)
            intent.putExtra("task_id", task.id) // Pass any necessary data
            context.startActivity(intent)
        }
    }


    fun refreshData(newTasks: List<Task>){
        tasks = newTasks
        notifyDataSetChanged()
    }
}