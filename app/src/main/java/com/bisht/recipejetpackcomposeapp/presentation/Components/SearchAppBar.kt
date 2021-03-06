package com.bisht.recipejetpackcomposeapp.presentation.Components

import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusManager
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import com.bisht.recipejetpackcomposeapp.presentation.ui.RecipList.FoodCategory
import com.bisht.recipejetpackcomposeapp.presentation.ui.RecipList.getAllFoodCategories
import kotlinx.coroutines.launch

@Composable
fun SearchAppBar(
    query: String,
    onQueryChanged: (String) -> Unit,
    onExecuteSearch: () -> Unit,
    scrollPosition: Float,
    selectedCategory: FoodCategory?,
    onSelectedCategoryChanged: (String) -> Unit,
    onChangeCategoryScrollPosition: (Float) -> Unit,
    focusManager: FocusManager,
    onToggleTheme: () -> Unit
){
    Surface(
        elevation = 8.dp,
        modifier = Modifier.fillMaxWidth(),
        color = MaterialTheme.colors.surface,
    ) {
        Column {
            Row(modifier = Modifier.fillMaxWidth()){
                ConstraintLayout {
                    val text = createRef()
                    TextField(
                        value = query,
                        onValueChange = {
                            onQueryChanged(it)
                        },
                        label = {
                            Text( text = "Search")
                        },
                        keyboardOptions = KeyboardOptions(
                            keyboardType = KeyboardType.Text,
                            imeAction = ImeAction.Search,
                        ),
                        leadingIcon = {
                            Icon(Icons.Filled.Search,"Icon")
                        },
                        keyboardActions = KeyboardActions( onSearch = {
                            onExecuteSearch()
                            focusManager.clearFocus()
                        }),
                        colors = TextFieldDefaults.textFieldColors(
                            backgroundColor = MaterialTheme.colors.surface,
                            textColor = MaterialTheme.colors.primary
                        ),
                        modifier = Modifier
                            .fillMaxWidth(.9f)
                            .padding(start = 5.dp)
                    )
                }
                ConstraintLayout(
                    modifier = Modifier.align(Alignment.CenterVertically)
                ) {
                    val menu = createRef()
                    IconButton(
                        onClick = onToggleTheme,
                        modifier = Modifier
                            .constrainAs(menu){
                                end.linkTo(parent.end)
                                top.linkTo(parent.top)
                                bottom.linkTo(parent.bottom)
                            }
                    ){
                        Icon(Icons.Filled.MoreVert,"Icon")
                    }
                }
            }
            val scrollState = rememberScrollState()
            val scope = rememberCoroutineScope()
            Row(
                modifier = Modifier
                    .horizontalScroll(scrollState)
                    .padding(top = 8.dp, start = 8.dp, bottom = 8.dp)
            ){
                scope.launch { scrollState.scrollTo(scrollPosition.toInt()) }
                for ( foodCategory in getAllFoodCategories()){
                    FoodCategoryChip(
                        category = foodCategory.value,
                        isSelected = selectedCategory == foodCategory,
                        onExecuteSearch = {
                            onExecuteSearch()
                        },
                        onSelectedCategoryChanged = {
                            onSelectedCategoryChanged(it)
                            onChangeCategoryScrollPosition(scrollState.value.toFloat())
                        }
                    )
                }
            }
        }

    }
}