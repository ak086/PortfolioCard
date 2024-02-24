package com.example.portfoliocard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.portfoliocard.ui.theme.PortfolioCardTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PortfolioCardTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    CreateBizCard()
                }
            }
        }
    }
}

@Composable
fun CreateBizCard(){
    val buttonClickState = remember {
        mutableStateOf(false)
    }
    Surface(modifier = Modifier.fillMaxSize()) {
        Card(modifier = Modifier,
            elevation= CardDefaults.cardElevation(defaultElevation = 4.dp),
            shape = RoundedCornerShape(50.dp),
        ) {
            Column(verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally) {
                CreateImageProfile()
                Divider(color = Color.Black)
                CreateInfo()
                Divider(color= Color.Black)
                Button(onClick = {
                    buttonClickState.value = !buttonClickState.value
                }) {
                    Text(text = "Portfolio", style = MaterialTheme.typography.bodyMedium)
                }
            }
            if(buttonClickState.value){
                Content()
            }else{
                Box{}
            }

        }
    }
}

@Composable
private fun CreateImageProfile(modifier: Modifier = Modifier) {
    Surface(
        modifier = Modifier
            .size(150.dp)
            .padding(5.dp),
        shadowElevation = 5.dp,
        shape = CircleShape,
        border = BorderStroke(0.5.dp, Color.DarkGray)
    ) {
        Image(
            painter = painterResource(id = R.drawable.profilepic),
            contentDescription = "Profile pic",
            modifier = Modifier.size(150.dp),
            contentScale = ContentScale.Crop
        )

    }
}

@Composable
private fun CreateInfo(modifier: Modifier=Modifier){
    Column(modifier = Modifier.padding(5.dp)) {
        Text(text = "Moles.P",
            style=MaterialTheme.typography.headlineLarge,
            color = MaterialTheme.colorScheme.primary)
        Text(text = "Android Programmer",
            style=MaterialTheme.typography.headlineMedium,
            color = MaterialTheme.colorScheme.primary)
        Text(text = "@themilesCompose",
            style=MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.primary)
    }
}

@Preview
@Composable
fun Content(){
    Box(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()
        .padding(5.dp)){
        Surface(modifier = Modifier
            .padding(3.dp)
            .fillMaxHeight()
            .fillMaxWidth(),
            shape = RoundedCornerShape(corner = CornerSize(6.dp)),
            border = BorderStroke(width = 2.dp, color = Color.LightGray)
        ) {
            Portfolio(data= listOf("Project 1","Project 2","Project 3"))
        }
    }
}


@Composable
fun Portfolio(data: List<String>) {
    LazyColumn{
        items(data){
                item ->
            Row(modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth()
                .size(150.dp)) {
                CreateImageProfile(modifier = Modifier.size(50.dp))
                Column(modifier = Modifier
                    .padding(2.dp)
                    .align(alignment = Alignment.CenterVertically)) {
                    Text(text = item, fontWeight = FontWeight.Bold)
                    Spacer(modifier = Modifier.size(5.dp))
                    Text("The great Project", fontStyle = FontStyle.Italic)
                }
            }
        }
    }
}