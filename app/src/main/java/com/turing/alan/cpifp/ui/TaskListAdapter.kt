package com.turing.alan.cpifp.ui

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.turing.alan.cpifp.data.Task
import com.turing.alan.cpifp.databinding.TaskListItemBinding

class TaskListAdapter(private val toItemDetail:((Task)->Unit),
                      private val shareTask:((Task)->Unit)): ListAdapter<Task, TaskListAdapter.TaskViewHolder>(TaskDiffCallback) {

    inner class TaskViewHolder(private val binding: TaskListItemBinding) :RecyclerView.ViewHolder(binding.root) {

        fun bind(task:Task) {
            binding.taskTitle.text = task.title
            binding.taskBody.text = task.body
            binding.taskCompleted.isChecked = task.completed
            binding.root.setOnClickListener  {
                toItemDetail(task)
            }
            binding.shareTask.setOnClickListener { shareTask(task) }
        }

    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val binding:TaskListItemBinding = TaskListItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return TaskViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    object TaskDiffCallback: DiffUtil.ItemCallback<Task>() {
        override fun areItemsTheSame(oldItem: Task, newItem: Task) = oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: Task, newItem: Task) = oldItem.body == newItem.body &&
                oldItem.title == newItem.title &&
                oldItem.completed == newItem.completed

    }

}