package com.example.marketalerts

import android.content.Intent
import android.content.res.Configuration
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.marketalerts.ui.theme.MarketAlertsTheme
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.*
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {

            //AlertCard(Alert("Android", "Jetpack Compose","3434"))

//            Column(modifier = Modifier.fillMaxSize()) {
//                // 2
//                TopAppBar(title = {
//                    Text("Market Alerts")
//                })
//                // 3
//                var alerts =   ArrayList<Alert>()
//                alerts.add(Alert("^DJI", "34935.47","Price Not Met Yet"))
//                alerts.add(Alert("^DJI", "34935.47","Price Not Met Yet"))
//                alerts.add(Alert("^DJI", "34935.47","Price Not Met Yet"))
//                alerts.add(Alert("^DJI", "34935.47","Price Not Met Yet"))
//                Alerts(alerts)
//
//            }

            HomeContent()
        }
    }
}


data class Alert(val author: String, val body: String,val price: String)

//how to create a a list similar to recylcelist
@Composable
fun Alerts(alerts: List<Alert>){
    LazyColumn  {
        items(alerts){ alert ->
            AlertCard(alert)
        }
    }
}




@Composable
fun AlertCard(alert: Alert) {

    Surface(shape = MaterialTheme.shapes.medium, elevation = 8.dp,modifier= Modifier.padding(16.dp)) {
    Row(modifier = Modifier.padding(all = 8.dp)) {
        Image(
            painter = painterResource(R.drawable.ic_launcher_background),
            contentDescription = "Contact profile picture",
            modifier = Modifier
                .size(40.dp)
                .clip(CircleShape)
                .border(1.5.dp, MaterialTheme.colors.secondary, CircleShape)
        )
        Spacer(modifier = Modifier.width(8.dp))
        Column {
            Text(text = alert.author,
                color = MaterialTheme.colors.secondaryVariant,
                style = MaterialTheme.typography.h5
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = alert.body,
                style = MaterialTheme.typography.subtitle2,
                modifier = Modifier.padding(all = 4.dp).fillMaxWidth())
            Spacer(modifier = Modifier.width(8.dp))
            Text(text = alert.price)
        }

    }
  }

}


//Scaffold generate layout based on material design
@Composable
fun HomeContent() {
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("Title") })
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    val intent = Intent(context, AlertFormActivity::class.java).apply {
                        putExtra(EXTRA_MESSAGE, "message")
                    }
                    context.startActivity(intent)
                },
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

                var alerts =   ArrayList<Alert>()
                alerts.add(Alert("^DJI", "34935.47","Price Not Met Yet"))
                alerts.add(Alert("^DJI", "34935.47","Price Not Met Yet"))
                alerts.add(Alert("^DJI", "34935.47","Price Not Met Yet"))
                alerts.add(Alert("^DJI", "34935.47","Price Not Met Yet"))
                Alerts(alerts)

        }
    )
}

@Preview
@Composable
fun PreviewHomeScreen() {
    HomeContent()
}



@Preview
@Preview(
    showBackground = true,
    name = "Dark Mode"
)
@Composable
fun PreviewMessageCard() {
    AlertCard(
        alert = Alert("^DJI", "34935.4               ","ere")
    )

}

@Preview
@Composable
fun PreviewConversation() {
    MarketAlertsTheme {
        var alerts =   ArrayList<Alert>()
        alerts.add(Alert("^DJI", "34935.47","Price Not Met Yet"))
        alerts.add(Alert("^DJI", "34935.47","Price Not Met Yet"))
        alerts.add(Alert("^DJI", "34935.47","Price Not Met Yet"))
        alerts.add(Alert("^DJI", "34935.47","Price Not Met Yet"))
        Alerts(alerts)
    }
}



