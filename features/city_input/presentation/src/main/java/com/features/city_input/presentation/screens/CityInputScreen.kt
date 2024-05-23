package com.features.city_input.presentation.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavHostController
import com.core.navigation.CurrentWeatherRoute

@Composable
fun CityInputScreen(navHostController: NavHostController, viewModel: CityInputViewModel) {

    val viewState = viewModel.state.value
    val query = viewModel.query.collectAsState()

    ConstraintLayout(modifier = Modifier.fillMaxSize().statusBarsPadding()) {
        val (searchCons, listCons) = createRefs()
        TextField(value = query.value, onValueChange = {
            viewModel.updateQuery(it)
        }, modifier = Modifier
            .fillMaxWidth()
            .constrainAs(searchCons) {
                start.linkTo(parent.start)
                end.linkTo(parent.end)
                top.linkTo(parent.top)
            })

        when (viewState) {
            is CityInputViewState.Idle -> {

            }

            is CityInputViewState.Loading -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator()
                }
            }

            is CityInputViewState.Success -> {
                LazyColumn(modifier = Modifier.constrainAs(listCons) {
                    start.linkTo(parent.start)
                    end.linkTo(parent.end)
                    top.linkTo(searchCons.bottom)
                    bottom.linkTo(parent.bottom)
                    height = Dimension.fillToConstraints
                }) {
                    items(viewState.cities) {
                        Text(
                            text = it.getFullText(null).toString(),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp)
                                .clickable {
                                    navHostController.navigate(CurrentWeatherRoute.CURRENT_WEATHER.route + "/${it.placeId}")
                                }
                        )
                    }
                }
            }

            is CityInputViewState.Error -> {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    Text(text = viewState.errorMessage)
                }
            }
        }
    }
}
