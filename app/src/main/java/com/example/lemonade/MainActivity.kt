package com.example.lemonade

import android.icu.text.CaseMap.Title
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.material3.rememberTopAppBarState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.currentRecomposeScope
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.input.nestedscroll.nestedScroll
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonade.ui.theme.LemonadeTheme
import androidx.compose.material3.Button as Button1

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                LemonadeApp()

            }
        }
    }
}

@Preview(
    showBackground = true
)
@Composable
fun LemonadeApp() {
    LemonadeWithTextAndImage(modifier = Modifier
        .fillMaxSize()
        .wrapContentSize(Alignment.Center)
    )
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LemonadeWithTextAndImage(modifier: Modifier = Modifier) {
    var result by remember { mutableStateOf(1) }
    var currentClick by remember { mutableStateOf(1) }
    var randomClicks by remember { mutableStateOf((2..5).random())}

    val imageResource = when(result){
        1 -> R.drawable.lemon_tree
        2 -> R.drawable.lemon_squeeze
        3 -> R.drawable.lemon_drink
        else -> R.drawable.lemon_restart
    }

    val stringResource = when(result){
        1 -> R.string.instruction1
        2 -> R.string.instruction2
        3 -> R.string.instruction3
        else -> R.string.instruction4
    }

    TopAppBar(
        title = {
            Text(
                text = stringResource(R.string.top_bar_title),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .width(365.dp)
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color(0xFFf5dc07)
        )
    )

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = imageResource),
            contentDescription = "1",
            contentScale = ContentScale.Inside,
            modifier = Modifier
                .size(
                    width = 250.dp,
                    height = 250.dp
                )
                .clip(RoundedCornerShape(16.dp))
                .background(
                    color = Color(0xFFcbf5cd)
                )
                .clickable(
                    onClick = {
                        if (currentClick <= randomClicks + 1) {
                            result = 2
                        } else if (currentClick <= randomClicks + 2) {
                            result = 3
                        } else if (currentClick <= randomClicks + 3) {
                            result = 4
                        } else if (currentClick <= randomClicks + 4) {
                            currentClick = 1
                            result = 1
                            randomClicks = (2..5).random()
                        }
                        currentClick++
                    }
                )
        )
        
        Spacer(modifier = Modifier.height(16.dp))
        
        Text(
            text = stringResource(id = stringResource),
            fontSize = 18.sp
        )
    }
}