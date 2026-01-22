package com.example.viikko1tehtv.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.viikko1tehtv.domain.Task
import com.example.viikko1tehtv.viewModel.TaskViewModel


@Composable
fun HomeScreen(viewModel: TaskViewModel = viewModel()) {
    var newTaskTitle by remember { mutableStateOf(value = "") }

    Column(modifier = Modifier.padding(all = 16.dp)) {
        Text(text = "Tehtavalista", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(height = 16.dp))


        Row {
            TextField(
                value = newTaskTitle,
                onValueChange = { newTaskTitle = it },
                label = { Text(text = "Uusi Tehtava") },
                modifier = Modifier.weight(weight = 1f)
            )

            Spacer(modifier = Modifier.width(width = 8.dp))
            Button(onClick = {
                if (newTaskTitle.isNotBlank()) {
                    viewModel.addTask(
                        Task(
                            id = viewModel.tasks.size + 1,
                            title = newTaskTitle,
                            description = "Added from button",
                            priority = 1,
                            dueDate = "2026-01-10",
                            done = false
                        )
                    )
                    newTaskTitle = ""
                }
            }) { Text(text = "Lisää uusi") }


        }

        Spacer(modifier = Modifier.height(height = 8.dp))

        Button(onClick = { viewModel.sortbyDueDate() }) {
            Text(text = "Järjestä päivämäärän mukaan")
        }

        Spacer(modifier = Modifier.height(height = 8.dp))

        // Suodatusnapit
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            Button(onClick = { viewModel.filterByDone(true) }) {
                Text(text = "Valmiit")
            }
            Button(onClick = { viewModel.filterByDone(false) }) {
                Text(text = "Kesken")
            }
            Button(onClick = { viewModel.showAll() }) {
                Text(text = "Kaikki")
            }
        }

        Spacer(modifier = Modifier.height(height = 16.dp))

        LazyColumn() {
            items(items = viewModel.tasks) { task ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(all = 8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Checkbox(
                        checked = task.done,
                        onCheckedChange = {
                            viewModel.toggleDone(id = task.id)
                        }
                    )
                    Text(text = task.title)
                    Button(onClick = { viewModel.removeTask(id = task.id) }) {
                        Text(text = "Poista")
                    }
                }
            }
        }
    }
}
