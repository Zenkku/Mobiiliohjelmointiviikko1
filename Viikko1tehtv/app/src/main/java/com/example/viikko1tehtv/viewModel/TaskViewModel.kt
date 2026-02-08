package com.example.viikko1tehtv.viewModel

import androidx.lifecycle.ViewModel
import com.example.viikko1tehtv.domain.Task
import com.example.viikko1tehtv.domain.mockTasks
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class TaskViewModel : ViewModel() {

    private val allTasks = MutableStateFlow<List<Task>>(emptyList())

    private val _task = MutableStateFlow<List<Task>>(emptyList())
    val task: StateFlow<List<Task>> = _task.asStateFlow()

    private val _selectedTask = MutableStateFlow<Task?>(null)
    val selectedTask: StateFlow<Task?> = _selectedTask.asStateFlow()

    val addTaskDialogVisible = MutableStateFlow<Boolean>(false)


    init {
        allTasks.value = mockTasks
        _task.value = mockTasks
    }

    fun addTask(title: String, description: String, dueDate: String) {
        val newId = (allTasks.value.maxOfOrNull { it.id } ?: 0) + 1
        val newTask = Task(
            id = newId,
            title = title,
            description = description,
            priority = 1,
            dueDate = dueDate,
            done = false
        )
        allTasks.value = allTasks.value + newTask
        _task.value = allTasks.value
    }

    fun removeTask(id: Int) {
        allTasks.value = allTasks.value.filter { it.id != id }
        _task.value = allTasks.value
    }

    fun toggleDone(id: Int) {
        allTasks.value = allTasks.value.map { task ->
            if (task.id == id) task.copy(done = !task.done) else task
        }
        _task.value = allTasks.value
    }

    fun sortbyDueDate() {
        _task.value = _task.value.sortedBy { it.dueDate }
    }

    fun filterByDone(done: Boolean) {
        _task.value = allTasks.value.filter { it.done == done }
    }

    fun showAll() {
        _task.value = allTasks.value
    }

    fun selectTask(task: Task) {
        _selectedTask.value = task
    }

    fun updateTask(updatedTask: Task) {
        allTasks.value = allTasks.value.map {
            if (it.id == updatedTask.id) updatedTask else it
        }
        _task.value = allTasks.value
        _selectedTask.value = null
    }

    fun closeDialog() {
        _selectedTask.value = null
    }
}