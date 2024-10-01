package com.turing.alan.cpifp.ui

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.turing.alan.cpifp.R
import com.turing.alan.cpifp.data.InMemoryTaskRepository
import com.turing.alan.cpifp.data.Task
import com.turing.alan.cpifp.data.TaskRepository
import com.turing.alan.cpifp.databinding.ActivityCreateTaskBinding
import java.time.Instant

class CreateTaskActivity : AppCompatActivity() {
    lateinit var binding: ActivityCreateTaskBinding
    private val taskRepository: TaskRepository = InMemoryTaskRepository.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityCreateTaskBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        binding.saveTaskButton.setOnClickListener {
            val title = binding.titleInput.text.toString()
            val body = binding.bodyInput.text.toString()
            val newTask = Task(
                id = 0,
                title = title,
                body = body,
                completed = false,
                createdAt = Instant.now()
            )
            taskRepository.create(newTask)
            finish()
        }
    }
}