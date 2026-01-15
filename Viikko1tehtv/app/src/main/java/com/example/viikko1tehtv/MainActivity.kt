package com.example.viikko1tehtv

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.viikko1tehtv.domain.Task
import com.example.viikko1tehtv.domain.addTask
import com.example.viikko1tehtv.domain.filtter
import com.example.viikko1tehtv.domain.mockTasks
import com.example.viikko1tehtv.domain.sortbyDueDate
import com.example.viikko1tehtv.domain.toggleDone
import com.example.viikko1tehtv.ui.theme.Viikko1tehtäväTheme



class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Viikko1tehtäväTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Homescreen(modifier = Modifier.padding(paddingValues= innerPadding))
                }
            }
        }
    }
}

//@Preview(showBackground = true)
@Composable
    fun Homescreen(modifier: Modifier){
      var taskList by remember  { mutableStateOf(value = mockTasks) }

    Column(modifier = Modifier.padding(all =100.dp)) {
        Text(text= "Tehtavalista", style = MaterialTheme.typography.headlineSmall)
        taskList.forEach { task ->
            Text(text = "${task.title} - Due: ${task.dueDate} - ${task.done}")
        }
    Row(modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceEvenly) {

        Button(
            onClick = {
                val newTask = Task(
                    id = taskList.size + 1,
                    title = "New Task",
                    description = "Added from button",
                    priority = 1,
                    dueDate = "2026-01-10",
                    done = false
                )
                taskList = addTask(taskList, newTask)
            },
        ) { Text(text = "Add task") }

        Button(onClick = {
            taskList = toggleDone(taskList, id = taskList.size)

        }
        ) { Text(text = "vaihto") }

    }
        Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly) {

        Button(onClick = {
            taskList = filtter(taskList, done = true)
        }
        ) { Text(text = "filtter true") }



        Button(onClick = {
            taskList = sortbyDueDate(taskList)
        }
        ) { Text(text = "Sort") }

        }
        Row(modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly) {

        Button(onClick = {
            taskList = mockTasks
        }) { Text(text = "Reset") }

    }

    }

    }


