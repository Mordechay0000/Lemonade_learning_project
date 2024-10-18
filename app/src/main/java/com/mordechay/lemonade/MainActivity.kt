package com.mordechay.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.mordechay.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LemonadeTheme {
                AppScreen()
            }
        }
    }

@Composable
fun AppScreen(modifier: Modifier = Modifier) {
    var screenNumber by remember { mutableIntStateOf(1) }
    var severalClicksToSqueeze by remember { mutableIntStateOf(0) }

    Column (
        modifier = modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        val (imageResource, imageDescription, textResource) = when (screenNumber) {
            1 -> {
                Triple(R.drawable.lemon_tree, R.string.image_description_one, R.string.one)
            }
            2 -> {
                Triple(R.drawable.lemon_squeeze, R.string.image_description_two, R.string.two)
            }
            3 -> {
                Triple(R.drawable.lemon_drink, R.string.image_description_three, R.string.three)
            }
            else -> {
                Triple(R.drawable.lemon_restart, R.string.image_description_four, R.string.four)
            }
        }
        Image(
            painter = painterResource(imageResource),
            contentDescription = stringResource(imageDescription),
            modifier = Modifier
                .clip(RoundedCornerShape(16.dp))
                .background(colorResource(R.color.teal_200))
                .clickable {
                if(severalClicksToSqueeze !=0) {
                    if (severalClicksToSqueeze == 1){
                        screenNumber = 3
                    }
                    severalClicksToSqueeze--
                } else if (screenNumber == 4) {
                    screenNumber = 1
                }else{
                    screenNumber++
                    if (screenNumber == 2) {
                        severalClicksToSqueeze = (2..4).random()
                    }
                }
            }
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = stringResource(textResource), fontSize = 18.sp)
        Text(text=if (severalClicksToSqueeze != 0) "Clicks to squeeze: $severalClicksToSqueeze" else "", fontSize = 12.sp)
    }
}
}