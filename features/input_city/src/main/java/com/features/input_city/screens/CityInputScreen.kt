package com.features.input_city.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.Dimension
import androidx.navigation.NavHostController
import com.core.navigation.CityInputRoute
import com.core.navigation.CurrentWeatherRoute
import com.features.input_city.R


@Composable
fun CityInputScreen(navHostController: NavHostController, viewModel: CityInputViewModel) {
        val viewState = viewModel.state.value
        val query = viewModel.query.collectAsState()

        Surface(color = MaterialTheme.colorScheme.background) {
            if (viewState is CityInputViewState.LoadingCity) {
                Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
                    CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
                }
            } else {
                if (viewState is CityInputViewState.CitySaved) {
                    navHostController.navigate(CurrentWeatherRoute.CURRENT_WEATHER.route + "/${viewState.cityId}")
                } else {
                    ConstraintLayout(
                        modifier = Modifier
                            .fillMaxSize()
                            .statusBarsPadding()
                    ) {
                        val (searchCons, listCons) = createRefs()
                        OutlinedTextField(
                            value = query.value,
                            onValueChange = {
                                viewModel.updateQuery(it)
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 16.dp, end = 16.dp, top = 6.dp)
                                .constrainAs(searchCons) {
                                    start.linkTo(parent.start)
                                    end.linkTo(parent.end)
                                    top.linkTo(parent.top)
                                },
                            colors = TextFieldDefaults.colors(
                                focusedIndicatorColor = MaterialTheme.colorScheme.tertiary,
                                unfocusedIndicatorColor = Color.LightGray,
                                focusedContainerColor = MaterialTheme.colorScheme.surface,
                                unfocusedContainerColor = MaterialTheme.colorScheme.surface,
                                focusedLabelColor = MaterialTheme.colorScheme.onSurface
                            ),
                            label = { Text(text = "City Name", color = Color.Gray, textAlign = TextAlign.Center)},
                            shape = RoundedCornerShape(16.dp)
                        )

                        when (viewState) {
                            is CityInputViewState.Idle -> {
                                Box(
                                    modifier = Modifier.fillMaxSize(),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = stringResource(R.string.please_input_city_name),
                                        color = MaterialTheme.colorScheme.onSurface
                                    )
                                }
                            }

                            is CityInputViewState.Loading -> {}

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
                                            color = MaterialTheme.colorScheme.onSurface,
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(10.dp)
                                                .clickable {
                                                    navHostController.navigate(CurrentWeatherRoute.CURRENT_WEATHER.route + "/${it.placeId}") {
                                                        popUpTo(CityInputRoute.CITY_INPUT.route) {
                                                            inclusive = true
                                                        }
                                                    }
                                                }
                                        )
                                    }
                                }
                            }

                            is CityInputViewState.Error -> {
                                Box(
                                    modifier = Modifier.fillMaxSize(),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = viewState.errorMessage,
                                        color = MaterialTheme.colorScheme.error
                                    )
                                }
                            }

                            is CityInputViewState.CitySaved -> navHostController.navigate(
                                CurrentWeatherRoute.CURRENT_WEATHER.route + "/${viewState.cityId}"
                            )

                            CityInputViewState.LoadingCity -> {}
                        }
                    }
                }
            }
        }

}