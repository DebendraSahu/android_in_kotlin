package com.debend.composelayouts

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.debend.composelayouts.ui.theme.ComposeLayoutsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ComposeLayoutsTheme {
                LazyColumnExample()
//                BoxExample()
//                RowAndColumnExample(
//                    modifier = Modifier.height(200.dp)
//                )
            }
        }
    }
}


@Composable
fun BoxExample() {
    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Box(
            modifier = Modifier
                .height(100.dp)
                .width(100.dp)
                .verticalScroll(rememberScrollState())
                .background(color = Color.Cyan)
        ) {
            Text(
                text = "Hello World this is box inside another box.", fontSize = 40.sp
            )
        }
    }
}

@Composable
fun RowAndColumnExample(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.padding(vertical = 5.dp), verticalArrangement = Arrangement.SpaceEvenly
    ) {
        RowExample()
        RowExample()
        RowExample()
    }
}


@Composable
fun ColumnExample(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.padding(20.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(
            onClick = {}, modifier = Modifier
                .weight(1F)
                .padding(5.dp)
        ) {
            Text("First item", textAlign = TextAlign.Center)
        }
        Button(
            onClick = {}, modifier = Modifier
                .weight(1F)
                .padding(5.dp)
        ) {
            Text("Second item", textAlign = TextAlign.Center)
        }
        Button(
            onClick = {}, modifier = Modifier
                .weight(1F)
                .padding(5.dp)
        ) {
            Text("Third item", textAlign = TextAlign.Center)
        }
    }

}

@Composable
fun RowExample(modifier: Modifier = Modifier) {
    Row(
        modifier = modifier.padding(20.dp),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Button(
            onClick = {}, modifier = Modifier
                .weight(1F)
                .padding(5.dp)
        ) {
            Text("First item", textAlign = TextAlign.Center)
        }
        Button(
            onClick = {}, modifier = Modifier
                .weight(1F)
                .padding(5.dp)
        ) {
            Text("Second item", textAlign = TextAlign.Center)
        }
        Button(
            onClick = {}, modifier = Modifier
                .weight(1F)
                .padding(5.dp)
        ) {
            Text("Third item", textAlign = TextAlign.Center)
        }
    }

}

@Preview
@Composable
fun LazyColumnExample() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black)
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxHeight()
        ) {
            items(100) { item ->
                var color = Color.Gray
                if (item % 2 == 0) color = Color.DarkGray
                Row(horizontalArrangement = Arrangement.SpaceEvenly) {
                    Text(
                        text = "Sl no.",
                        color = Color.White,
                        modifier = Modifier
                            .background(color)
                            .padding(10.dp)
                    )
                    Box(
                        modifier = Modifier
                            .padding(10.dp)
                            .weight(1F)
                            .background(color = Color.Yellow), contentAlignment = Alignment.Center
                    ) {
                        Text(text = "$item")
                    }
                }
            }
        }
    }
}

