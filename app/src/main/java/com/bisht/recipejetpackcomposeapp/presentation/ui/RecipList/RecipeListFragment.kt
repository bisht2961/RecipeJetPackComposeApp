package com.bisht.recipejetpackcomposeapp.presentation.ui.RecipList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.bisht.recipejetpackcomposeapp.presentation.BaseApplication
import com.bisht.recipejetpackcomposeapp.presentation.Components.*
import com.bisht.recipejetpackcomposeapp.presentation.theme.AppTheme
import com.bisht.recipejetpackcomposeapp.utils.RECIPE_IMAGE_HEIGHT
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class RecipeListFragment: Fragment() {

    @Inject
    lateinit var application: BaseApplication

    private val viewModel: RecipeListViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                AppTheme(application.dark.value) {
                    val recipes = viewModel.recipes.value
                    val query = viewModel.query.value
                    val focusManager = LocalFocusManager.current
                    val selectedCategory = viewModel.selectedCategory.value
                    val loading = viewModel.loading

                    Column {
                        SearchAppBar(
                            query = query,
                            onQueryChanged = viewModel::onQueryChanged,
                            onExecuteSearch = viewModel::newSearch,
                            scrollPosition = viewModel.categoryScrollPosition.toFloat(),
                            selectedCategory = selectedCategory,
                            onSelectedCategoryChanged = viewModel::onSelectedCategoryChanged,
                            onChangeCategoryScrollPosition = viewModel::onChangeCategoryScrollPosition,
                            focusManager = focusManager,
                            onToggleTheme = {
                                application.toggleLightTheme()
                            }
                        )
                        Box(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(color = MaterialTheme.colors.background)
                        ) {
                            if (loading.value) {
                                LoadingRecipeListShimmer(imageHeight = RECIPE_IMAGE_HEIGHT.dp)
                            } else {
                                LazyColumn {
                                    itemsIndexed(
                                        items = recipes
                                    ) { index, recipe ->
                                        RecipeCard(recipe = recipe, onClick = {})
                                    }
                                }
                            }
                            CircularIndeterminateProgressBar(isDisplayed = loading.value)
                        }
                    }
                }
            }
        }
    }
}
