package com.example.viikko1tehtv.view

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.LightMode
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.example.viikko1tehtv.domain.Task
import com.example.viikko1tehtv.viewModel.TaskViewModel
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue


@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun CalendarScreen(
    viewModel: TaskViewModel,
    onTaskClick: (Int) -> Unit = {},
    onNavigationToHome: () -> Unit

){
    val tasks by viewModel.task.collectAsState()
    val selectedTask by viewModel.selectedTask.collectAsState()
     //val Icons.Filled.list: ImageVector

    val grouped = tasks.groupBy {it.dueDate ?: "No date"}

    Column(modifier = Modifier.padding(all = 16.dp)) {

        TopAppBar(
            title = { Text(text = "Kalenteri") },
            navigationIcon = {
                IconButton(onClick = onNavigationToHome) {
                    Icon(imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Home")
                }
            }
        )

        Spacer(modifier = Modifier.height(height = 16.dp))

        LazyColumn {
            grouped.toSortedMap().forEach { (date, tasksOfDay) ->
                item {
                    Text(
                        text = date,
                        style = MaterialTheme.typography.titleMedium,
                        modifier = Modifier.padding(top = 16.dp, bottom = 8.dp)
                    )
                }

                items(items = tasksOfDay) { task ->
                    CalendarTaskCard(
                        tasks = task,
                        onTaskClick = { viewModel.selectTask(task) }
                    )
                }
            }
        }
        if (selectedTask != null) {
            DetailDialog(
                task = selectedTask!!,
                onClose = { viewModel.closeDialog() },
                onUpdate = { viewModel.updateTask(it) })
        }
    }
}

@Composable
    fun CalendarTaskCard(
        tasks: Task,
    onTaskClick: (Int) -> Unit
    ){
        Card(
            modifier = Modifier
                .padding(vertical = 8.dp)
                .fillMaxWidth()
                .clickable { onTaskClick(tasks.id) },
        ){
            Column(modifier = Modifier.padding(all = 12.dp)) {
                Text(text = tasks.title, style = MaterialTheme.typography.titleMedium)
                    if (tasks.description.isNotBlank()) {
                        Text(text = tasks.description, style = MaterialTheme.typography.bodyMedium)
            }
        }

        }}
