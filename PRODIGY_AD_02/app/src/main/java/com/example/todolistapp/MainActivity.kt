package com.example.todolistapp
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.todolistapp.databinding.ActivityMainBinding // Replace with your package name

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val taskList = mutableListOf<String>()
    private lateinit var taskAdapter: TaskAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        taskAdapter = TaskAdapter(taskList) { position ->
            deleteTask(position)
        }

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter = taskAdapter

        binding.addButton.setOnClickListener {
            val task = binding.taskInput.text.toString()
            if (task.isNotEmpty()) {
                addTask(task)
            }
        }
    }

    private fun addTask(task: String) {
        taskList.add(task)
        taskAdapter.notifyItemInserted(taskList.size - 1)
        binding.taskInput.text.clear()
    }

    private fun deleteTask(position: Int) {
        taskList.removeAt(position)
        taskAdapter.notifyItemRemoved(position)
    }
}
