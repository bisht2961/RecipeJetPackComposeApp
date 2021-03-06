package com.bisht.recipejetpackcomposeapp.presentation.ui.Recipe

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.fragment.app.Fragment
import com.bisht.recipejetpackcomposeapp.presentation.theme.AppTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class RecipeFragment:Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        val view = ComposeView(requireContext()).apply{
            setContent {
                AppTheme(darkTheme = false) {
                    Column( modifier = Modifier.padding(16.dp)) {
                        Text(
                            text = "RECIPE FRAGMENT",
                            fontSize = 21.sp
                        )
                    }
                }
            }
        }
        return view
    }
}