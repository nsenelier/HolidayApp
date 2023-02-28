package com.example.holidayapp.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.holidayapp.data.remote.dto.HolidayDetailDto
import com.example.holidayapp.domain.model.HolidayDetail
import com.example.holidayapp.domain.use_case.AddHolidayUseCase
import com.example.holidayapp.domain.use_case.DeleteHolidayUseCase
import com.example.holidayapp.domain.use_case.GetHolidayUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class USHolidayViewModel @Inject constructor(
    private val dispatcher: CoroutineDispatcher,
    private val deleteHolidayUseCase: DeleteHolidayUseCase,
    private val getHolidayUseCase: GetHolidayUseCase,
    private val addHolidayUseCase: AddHolidayUseCase
): ViewModel(){
    private val _uiState = MutableStateFlow(USHolidayUiState())

    val uiState: StateFlow<USHolidayUiState> = _uiState.asStateFlow()

     var data: List<HolidayDetailDto> = emptyList<HolidayDetailDto>()

    fun onEvent(event: USHolidayEvent){
        when(event){
            is USHolidayEvent.LoadHoliday ->{
                getUSHolidayList()
            }
            is USHolidayEvent.OnFavoriteSelected ->{
                _uiState.value = USHolidayUiState(holiday = event.holiday)
                addFavorite()
            }
            is USHolidayEvent.DeleteFavorite ->{
                _uiState.update { it.copy(id= event.id) }
                deleteFavorite()
            }
        }
    }

    private fun deleteFavorite(
        id: Int = _uiState.value.id
    ) {
        viewModelScope.launch(dispatcher) {
            deleteHolidayUseCase.deleteFavorite(id)
        }
    }
    private fun addFavorite(
        holiday: HolidayDetail = _uiState.value.holiday
    ){
        viewModelScope.launch(dispatcher) {
            addHolidayUseCase.addFavorite(holiday)
            getUSHolidayList()
        }
    }
    private fun getUSHolidayList(
        query: String = "US",
    ){
        viewModelScope.launch(dispatcher) {
            data = getHolidayUseCase.getHoliday(query)
        }
    }
}