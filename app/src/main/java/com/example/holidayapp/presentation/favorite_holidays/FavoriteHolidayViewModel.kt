package com.example.holidayapp.presentation.favorite_holidays

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.holidayapp.domain.use_case.DeleteHolidayUseCase
import com.example.holidayapp.domain.use_case.GetHolidayUseCase
import com.example.holidayapp.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoriteHolidayViewModel @Inject constructor(
    private val dispatcher: CoroutineDispatcher,
    private val deleteHolidayUseCase: DeleteHolidayUseCase,
    private val getHolidayUseCase: GetHolidayUseCase
): ViewModel() {
    private val _uiState = MutableStateFlow(FavoriteHolidayUiState())

    val uiState: StateFlow<FavoriteHolidayUiState> = _uiState.asStateFlow()

    fun onEvent(event: FavoriteHolidayEvent) {
        when (event) {

            is FavoriteHolidayEvent.OnDeleteSelected -> {
                _uiState.update { it.copy(id=event.id) }

                viewModelScope.launch {
                    deleteFavorite()
                }
            }
            is FavoriteHolidayEvent.LoadFavoriteHoliday-> getFavoriteHolidayListings()
        }
    }

    private fun deleteFavorite(
        id: Int = _uiState.value.id
    ) {
        viewModelScope.launch(dispatcher) {
            deleteHolidayUseCase.deleteFavorite(id)
            getFavoriteHolidayListings()
        }

    }

    private fun getFavoriteHolidayListings(
    ) {
        viewModelScope.launch(dispatcher) {
            getHolidayUseCase.getFavorites()
                .collect { result ->
                    when (result) {
                        is Resource.Success -> {
                            result.data?.let { listings ->
                                _uiState.value = _uiState.value.copy(favoriteHoliday = listings, isLoading = false)
                            }
                            _uiState.value = _uiState.value.copy()
                        }
                        is Resource.Error -> {
                            _uiState.value = _uiState.value.copy(error = "Error occurred")
                        }
                        is Resource.Loading -> {
                            _uiState.value = _uiState.value.copy(isLoading = true)
                        }
                    }
                }
        }
    }

}