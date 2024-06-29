package pro.inc.todolistapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.datetime.toInstant

@Composable


fun TaskScreen(modifier: Modifier = Modifier) {
    val taskViewModel : MainViewModel = viewModel()

    val viewState by taskViewModel.listState

    Box(modifier = Modifier.fillMaxSize()) {

        when {
            viewState.isEditing -> {
                // Show a loading indicator
            }
            viewState.error != null -> {
                Text(text = "Error: ${viewState.error}")
            }
            else -> {
                // Show the list of tasks
                ListItemScreen(tasks = viewState.list)
            }

        }
    }



}


@Composable
fun ListItemScreen(tasks: List<Listitem>) {

    var lists by remember { mutableStateOf(listOf<Listitem>()) }
    var taskname by remember { mutableStateOf("") }
    var taskdiscription by remember { mutableStateOf("") }
    var taskpriority by remember { mutableStateOf("") }
    var taskduedate by remember { mutableStateOf("") }
    var taskstartdate by remember { mutableStateOf("") }

    var showDialog by remember { mutableStateOf(false) }

    Column(

        horizontalAlignment =  Alignment.CenterHorizontally
    ) {
        Spacer(modifier = Modifier.height(45.dp))

        Button(onClick = {showDialog = true},) {
            Text(text = "Add Task")

        }
        LazyColumn(
            modifier = Modifier.fillMaxSize()
        ) {
            items(tasks) { task ->
                ListitemItem(list = task)
            }

        }
    }

    if (showDialog) {
        // Show the dialog

        AlertDialog(onDismissRequest = {showDialog= false}, confirmButton = {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                Button(onClick = {
                    if (taskname.isNotBlank()){
                        val newTask = Listitem(
                            id = tasks.size+1,
                            name = taskname,
                            description = taskdiscription,
                            priority = taskpriority.toInt(),
                            dueDate = taskduedate.toInstant(),
                            creationDate = taskstartdate.toInstant()
                        )
                        lists = lists + newTask
                        showDialog = false
                        taskname = ""
                        taskdiscription = ""
                        taskpriority = ""
                        taskduedate = ""
                        taskstartdate = ""



                    }


                }) {
                    Text("Add")

                }

                Button(onClick = { showDialog = false }) {
                    Text("Cancel")

                }

            }

        },
            title = {
                Text(
                    "Add your task",
                    style = MaterialTheme.typography.headlineMedium
                )
            },
            text = {
                Column {
                    OutlinedTextField(value = taskname, 
                        onValueChange ={taskname = it},
                        singleLine = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .background(Color.Cyan),
                        label = { Text(text = "Task Title")}
                    )

                    OutlinedTextField(value = taskdiscription,
                        onValueChange ={taskdiscription = it},
                        singleLine = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .background(Color.Cyan),
                        label = { Text(text = "Task Details")}
                    )

                    OutlinedTextField(value = taskpriority,
                        onValueChange ={taskpriority = it},
                        singleLine = true,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .background(Color.Cyan),
                        label = { Text(text = "Task Priority")}
                    )



                }
            }

            )
    }


}


@Composable

fun ListitemItem( list: Listitem){

    Row (

    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(8.dp)
                .background(Color.White)
        ) {
            Text(text = list.name,
                color = Color.Black,
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold)

            Text(text = list.description,
                color = Color.Black,
                fontSize = 16.sp,
                fontWeight = FontWeight.Normal,
                modifier = Modifier.padding(top = 4.dp),
                maxLines = 2)

            Row {
                Text(text = "Due: ${list.dueDate}",
                    color = Color.Black,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier.padding(top = 4.dp))

                Text(text = "Priority: ${list.priority}",
                    color = Color.Black,
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Normal,
                    modifier = Modifier.padding(top = 4.dp))
            }
        }

        IconButton(onClick = { /*TODO*/  }) {
            Icon(imageVector = Icons.Default.Edit, contentDescription = "Edit icon")

        }

        IconButton(onClick = { /*TODO*/ }) {
            Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete icon")

        }


    }

}


//@Composable
//
//fun TaskaddScreen(){
//
//    Column(
//        modifier = Modifier
//            .fillMaxSize()
//            .padding(16.dp)
//    ) {
//        Text(text = "Add Task",
//            color = Color.Black,
//            fontSize = 24.sp,
//            fontWeight = FontWeight.Bold,
//            modifier = Modifier.padding(bottom = 16.dp))
//
//        // This is the text field for the name
//        BasicTextField(value = editedName,
//            onValueChange = { editedName = it },
//            singleLine = true,
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(8.dp)
//        )
//
//        // This is the text field for the description
//        BasicTextField(value = editedDescription,
//            onValueChange = { editedDescription = it },
//            singleLine = true,
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(8.dp)
//        )
//
//        // This is the text field for the due date
//        BasicTextField(value = editedDueDate,
//            onValueChange = { editedDueDate = it },
//            singleLine = true,
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(8.dp)
//        )
//
//        // This is the text field for the priority
//        BasicTextField(value = editedPriority,
//            onValueChange = { editedPriority = it },
//            singleLine = true,
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(8.dp)
//        )
//
//        Button(onClick = {
//            isEditMode = false
//            onEditComplete(editedName, editedDescription, editedDueDate, editedPriority)
//        }) {
//            Text(text = "Save")
//        }
//    }
//
//}


