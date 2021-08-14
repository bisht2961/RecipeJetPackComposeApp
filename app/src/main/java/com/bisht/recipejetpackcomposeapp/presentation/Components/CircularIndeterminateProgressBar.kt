package com.bisht.recipejetpackcomposeapp.presentation.Components

import android.content.res.Configuration
import androidx.compose.foundation.layout.*
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet
import androidx.constraintlayout.widget.ConstraintLayout

@Composable
fun CircularIndeterminateProgressBar(
    isDisplayed: Boolean
){
    if(isDisplayed) {
        val constraints = if(LocalConfiguration.current.orientation == Configuration.ORIENTATION_PORTRAIT){
            myDecoupledConstraints(0.3f)
        }else{
            myDecoupledConstraints(0.7f)
        }
        ConstraintLayout(
            modifier = Modifier.fillMaxSize(),
            constraintSet = constraints
        ) {
            CircularProgressIndicator(
                modifier = Modifier.layoutId("progressBar"),
                color = MaterialTheme.colors.primary
            )
            Text(
                text = "Loading",
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 15.sp
                ),
                modifier = Modifier.layoutId("text")
            )
        }
    }
}

private fun myDecoupledConstraints( verticalBias: Float): ConstraintSet{
    return ConstraintSet {
        val guideline = createGuidelineFromTop(verticalBias)
        val progressBar = createRefFor("progressBar")
        val text = createRefFor("text")
        constrain(progressBar){
            top.linkTo(guideline)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }
        constrain(text){
            top.linkTo(progressBar.bottom)
            start.linkTo(parent.start)
            end.linkTo(parent.end)
        }
    }

}