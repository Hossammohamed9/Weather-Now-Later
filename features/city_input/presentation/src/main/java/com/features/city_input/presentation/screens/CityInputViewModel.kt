package com.features.city_input.presentation.screens

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.core.result.Resource
import com.features.city_input.domain.use_cases.SearchCitiesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.FlowPreview
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.drop
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@OptIn(FlowPreview::class)
@HiltViewModel
class CityInputViewModel @Inject constructor(
    private val searchCitiesUseCase: SearchCitiesUseCase
): ViewModel(){

    private val _state : MutableState<CityInputViewState> = mutableStateOf(CityInputViewState.Idle)
    val state : State<CityInputViewState> get() = _state

    private val _query : MutableStateFlow<String> = MutableStateFlow("")
    val query : StateFlow<String> get() = _query

    init {
        viewModelScope.launch {
            _query.debounce(500).drop(1).collectLatest {
                searchCities(it)
            }
        }
    }

    fun updateQuery(query: String){
        _query.value = query
    }

    private fun searchCities(query: String) {
        viewModelScope.launch(Dispatchers.IO) {
            searchCitiesUseCase(query = query).collectLatest { resource ->
                withContext(Dispatchers.Main) {
                    when (resource) {
                        is Resource.Loading -> {
                            _state.value = CityInputViewState.Loading
                        }

                        is Resource.Success -> {
                            _state.value = CityInputViewState.Success(resource.data)
                        }

                        is Resource.Failure -> {
                            _state.value = CityInputViewState.Error(resource.exception.message ?: "Unknown Error")
                        }
                    }
                }
            }
        }
    }

}