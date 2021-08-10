package com.example.marketalerts

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.marketalerts.ui.theme.MarketAlertsTheme

class AlertFormActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MarketAlertsTheme {
                // A surface container using the 'background' color from the theme

                   AlertFormContent()

            }
        }
    }
}


@Composable
fun AlertFormContent() {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Title") })
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = { /*TODO*/ },
                backgroundColor = Color.Red,
                content = {
                    Icon(
                        painter = painterResource(id = R.drawable.ic_launcher_background),
                        contentDescription = null,
                        tint = Color.White
                    )
                }
            )
        },
        content = {

            form("test")

        }
    )
}

@Composable
fun form(name: String) {
    //Text(text = "Hello $name!",     color = MaterialTheme.colors.secondaryVariant, )

       Column {
            Text(text = "alert.author",
                color = MaterialTheme.colors.secondaryVariant,
                style = MaterialTheme.typography.h5
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text =" alert.body",
                style = MaterialTheme.typography.subtitle2,
                modifier = Modifier
                    .padding(all = 4.dp)
                    .fillMaxWidth())
            Spacer(modifier = Modifier.width(8.dp))

           Text(text = "Price")
           Spacer(modifier = Modifier.width(8.dp))
           val textState = remember { mutableStateOf(TextFieldValue()) }
           TextField(
               value = textState.value,
               onValueChange = { textState.value = it },
               modifier = Modifier
                   .fillMaxWidth()
           )

           DropdownDemo()
           Spacer(modifier = Modifier.width(8.dp))
           dropdown()
        }


}


@Composable
fun DropdownDemo() {
    var expanded by remember { mutableStateOf(false) }
    val items = listOf("A", "B", "C", "D", "E", "F")
    val disabledValue = "B"
    var selectedIndex by remember { mutableStateOf(0) }
    Box(modifier = Modifier
        .fillMaxSize()
        .wrapContentSize(Alignment.TopStart)) {
        Text(items[selectedIndex],modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = { expanded = true })
            .background(
                Color.Gray
            ))
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .fillMaxWidth()
                .background(
                    Color.Red
                )
        ) {
            items.forEachIndexed { index, s ->
                DropdownMenuItem(onClick = {
                    selectedIndex = index
                    expanded = false
                }) {
                    val disabledText = if (s == disabledValue) {
                        " (Disabled)"
                    } else {
                        ""
                    }
                    Text(text = s + disabledText)
                }
            }
        }
    }
}


@Composable
fun dropdown() {
    var expanded by remember { mutableStateOf(false) }
    val suggestions = listOf("Item1","Item2","Item3")
    var selectedText by remember { mutableStateOf("") }



    Column() {
        OutlinedTextField(
            value = selectedText,
            onValueChange = { selectedText = it },
            modifier = Modifier.fillMaxWidth(),
            label = {Text("Label")},

        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier.fillMaxWidth()
        ) {
            suggestions.forEach { label ->
                DropdownMenuItem(onClick = {
                    selectedText = label
                }) {
                    Text(text = label)
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!",     color = MaterialTheme.colors.secondaryVariant, )
}

@Preview
@Composable
fun DefaultPreview() {

    AlertFormContent()

}