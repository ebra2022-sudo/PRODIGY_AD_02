package com.example.todolist

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.ui.graphics.Brush
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.materialIcon
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.TopAppBarScrollBehavior
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TodoScreen(
    modifier: Modifier = Modifier
) {
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(false) }


    val scrollBehavior = TopAppBarDefaults.exitUntilCollapsedScrollBehavior()
    Scaffold(
        modifier = Modifier
            .fillMaxSize()
            .nestedScroll(scrollBehavior.nestedScrollConnection),
        topBar = {
                 TopAppBar(
                     title = {Text("Todos", style = MaterialTheme.typography.displayMedium)},
                     colors = TopAppBarDefaults.topAppBarColors(
                         containerColor = Color(0xFF03A9F4)),
                     scrollBehavior = scrollBehavior)

        },
        floatingActionButton = {
            ExtendedFloatingActionButton(
                text = { Text("Show bottom sheet") },
                icon = { Icon(Icons.Filled.Add, contentDescription = "") },
                onClick = {
                    showBottomSheet = true
                }
            )
        }
    ){
        LazyColumn(modifier = Modifier
            .padding(horizontal = 10.dp)
            .fillMaxSize()
            .padding(it)
            .then(modifier), verticalArrangement = Arrangement.spacedBy(10.dp)) {
            items (15) {
                TodoItem(title = "Task 1", description = "Mobile applications that do not need to store at least some amount of persistent data are few and far between. The use of databases is an essential aspect of most applications, ranging from applications that are almost entirely data-driven, to those that simply need to store small amounts of data such as the prevailing score of a game.")
            }
        }
        if (showBottomSheet) {
            ModalBottomSheet(
                onDismissRequest = {
                    showBottomSheet = false
                },
                modifier = Modifier.fillMaxHeight(0.7f),
                sheetState = sheetState
            ) {
                // Sheet content

            }
        }
    }
}

@Composable
fun CustomTextField(modifier: Modifier = Modifier) {
    //OutlinedTextField(value = , onValueChange = )

}

@Composable
fun TodoItem(
    title: String,
    description: String,
    //toDo: ToDoEntry, // this parameter requires the object of dog class so that we can access its image,
    // name,age, hobby for later implementation
    modifier: Modifier = Modifier // this modifier parameter is used to modify the card composable
    // of the dog item.
) {
    var expanded by rememberSaveable {mutableStateOf(false) }

    //this state variable is to remember
    // the state change and recompose all composable that they affected by the state variable.
    val color by animateColorAsState( // the animateColorAsState() function that access different
        //color based on another state variable (expanded)
        targetValue = if (expanded) MaterialTheme.colorScheme.tertiaryContainer
        else MaterialTheme.colorScheme.primaryContainer,
        label = "a"
    )
    Card(modifier = modifier
        .clickable { expanded = !expanded },
        shape = MaterialTheme.shapes.medium

    ) {
        Column(modifier = Modifier
            .fillMaxSize()
            .background(color = color)
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessVeryLow
                )
            )) {
            Row(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Text(
                    text = title,
                    style = MaterialTheme.typography.labelSmall
                )
            }
            if (expanded) {
                Column(
                    modifier = modifier.padding(start = 15.dp, bottom = 5.dp)
                ) {
                    Text(
                        text = description,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 5.dp),
                        style = MaterialTheme.typography.bodyLarge,
                        textAlign = TextAlign.Justify
                    )
                }
            }
        }
    }
}



fun List<Color>.toBrush(): Brush {
    return when {
        isEmpty() -> throw IllegalArgumentException("Color list cannot be empty")
        else -> {
            val colorStops = mapIndexed { index, color ->
                (index.toFloat() / (size - 1)) to color
            }
            Brush.linearGradient(
                colorStops = colorStops.toTypedArray(),
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
private fun Preview() {
    TodoScreen()
}





