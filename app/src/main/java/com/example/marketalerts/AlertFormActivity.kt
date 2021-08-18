package com.example.marketalerts

import android.Manifest
import android.app.Activity
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import com.example.marketalerts.api.retrofitservices.ICaristockServiceApi
import com.example.marketalerts.ui.theme.MarketAlertsTheme
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class AlertFormActivity : ComponentActivity() {

    private val cristocksService by lazy {
        ICaristockServiceApi.create()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var x = cristocksService.getSymbol()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({ users ->

                users?.symbol {

                    entity.activities = entity.activities + it.name
                }

                //users

            },
                { error ->
                    val params = Bundle()
                    params.putString("image_name", "GooglePlaceHelper")
                    params.putString("full_text", error.toString())
                    Log.d("retroffit error", error.message.toString())

                },
                {
                    ActivityCompat.requestPermissions(
                        (this as Activity),
                        arrayOf(
                            Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.INTERNET,
                            Manifest.permission.ACCESS_COARSE_LOCATION
                        ),
                        REQUEST_LOCATION
                    )

                    Log.d("retroffit complete", "complete")

                },
                {

                    Log.d("retroffit subscribe", it.toString())
                }
            )




        setContent {
            MarketAlertsTheme {
                   AlertFormContent()
            }
        }
    }
}


@Composable
fun AlertFormContent() {
    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Create a New Alert") })
        },
        content = {

            form()

        }
    )
}

@Composable
fun form() {
    //Text(text = "Hello $name!",     color = MaterialTheme.colors.secondaryVariant, )
        Spacer(modifier = Modifier.width(8.dp))
       Column(modifier = Modifier.fillMaxSize()) {
            Text(text = "Select An Instrument",
                color = MaterialTheme.colors.secondaryVariant,
                style = MaterialTheme.typography.h5
            )

           Spacer(modifier = Modifier.width(8.dp))
           InstrumentList()
           Spacer(modifier = Modifier.width(8.dp))

           Text(text = "Enter Price",
               color = MaterialTheme.colors.secondaryVariant,
               style = MaterialTheme.typography.h5
           )

           Spacer(modifier = Modifier.width(8.dp))
           val textState = remember { mutableStateOf(TextFieldValue()) }
           OutlinedTextField(
               value = textState.value,
               onValueChange = { textState.value = it },
               modifier = Modifier
                   .fillMaxWidth(),
               keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)

           )

           Spacer(modifier = Modifier.width(8.dp))


           SubmitBtn()

        }


}

@Composable
fun SubmitBtn() {
    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {

        Button(
            modifier = Modifier.padding(12.dp),
            onClick = { /* Do something! */ }
        ) {
            Text("Button")
        }
    }
}


@Composable
fun DropDownList(
    requestToOpen: Boolean = false,
    list: List<String>,
    request: (Boolean) -> Unit,
    selectedString: (String) -> Unit
) {
    DropdownMenu(
        modifier = Modifier.fillMaxWidth(),

        expanded = requestToOpen,
        onDismissRequest = { request(false) },
    ) {
        list.forEach {
            DropdownMenuItem(
                modifier = Modifier.fillMaxWidth(),
                onClick = {
                    request(false)
                    selectedString(it)
                }
            ) {
                Text(
                    it,
                    modifier = Modifier
                        .wrapContentWidth()
                      
                )
            }
        }
    }
}

@Composable
fun InstrumentList() {



    val countryList = listOf(
        "United state",
        "Australia",
        "Japan",
        "India",
    )
    val text = remember { mutableStateOf("") } // initial value
    val isOpen = remember { mutableStateOf(false) } // initial value
    val openCloseOfDropDownList: (Boolean) -> Unit = {
        isOpen.value = it
    }
    val userSelectedString: (String) -> Unit = {
        text.value = it
    }
    Box {
        Column {
            OutlinedTextField(
                value = text.value,
                onValueChange = { text.value = it },
                label = { Text(text = "Select Instrument") },
                modifier = Modifier.fillMaxWidth()
            )
            DropDownList(
                requestToOpen = isOpen.value,
                list = countryList,
                openCloseOfDropDownList,
                userSelectedString
            )
        }
        Spacer(
            modifier = Modifier
                .matchParentSize()
                .background(Color.Transparent)
                .padding(10.dp)
                .clickable(
                    onClick = { isOpen.value = true }
                )
        )
    }
}


@Preview
@Composable
fun DefaultPreview() {

   form()
}