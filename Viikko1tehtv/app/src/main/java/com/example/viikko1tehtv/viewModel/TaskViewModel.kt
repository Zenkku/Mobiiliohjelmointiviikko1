package com.example.viikko1tehtv.viewModel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.viikko1tehtv.domain.Task
import com.example.viikko1tehtv.domain.mockTasks

class TaskViewModel : ViewModel() {

    // Tämä on "päälista", jossa on aina kaikki tehtävät.
    private var allTasks = listOf<Task>()

    // Tämä on lista, jota UI näyttää. Sitä muokataan suodatuksilla ja lajittelulla.
    var tasks by mutableStateOf(listOf<Task>())
        private set

    init {
        // Alussa molemmat listat sisältävät kaiken datan.
        allTasks = mockTasks
        tasks = allTasks
    }

    fun addTask(newTask: Task) {
        allTasks = allTasks + newTask
        tasks = allTasks // Päivitä näytettävä lista
    }

    fun removeTask(id: Int) {
        allTasks = allTasks.filter { it.id != id }
        tasks = allTasks // Päivitä näytettävä lista
    }

    fun toggleDone(id: Int) {
        allTasks = allTasks.map { task ->
            if (task.id == id) {
                task.copy(done = !task.done)
            } else {
                task
            }
        }
        tasks = allTasks // Päivitä näytettävä lista
    }

    fun sortbyDueDate() {
        // Lajitellaan vain näytettävää listaa
        tasks = tasks.sortedBy { it.dueDate }
    }

    fun filterByDone(isDone: Boolean) {
        // Suodatetaan päälistasta ja päivitetään näytettävä lista
        tasks = allTasks.filter { it.done == isDone }
    }

    fun showAll() {
        // Palautetaan kaikki tehtävät näkyviin
        tasks = allTasks
    }
}