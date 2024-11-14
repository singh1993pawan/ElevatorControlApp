package com.example.elevatorcontrolapp.uis.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.elevatorcontrolapp.domain.model.Direction
import com.example.elevatorcontrolapp.domain.model.DoorState
import com.example.elevatorcontrolapp.domain.model.ElevatorState
import com.example.elevatorcontrolapp.domain.model.FloorRequest
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ElevatorViewModel : ViewModel() {
    private val _elevatorState = MutableStateFlow(ElevatorState())
    val elevatorState:StateFlow<ElevatorState> =_elevatorState

    val requestQueue = mutableListOf<FloorRequest>()


    fun requestElevator(floor:Int, direction: Direction){
        requestQueue.add(FloorRequest(floor,direction))
        if (_elevatorState.value.direction == Direction.IDLE) {
            processNextRequest()
        }
    }

    private fun processNextRequest(){
        if (requestQueue.isNotEmpty() && _elevatorState.value.direction == Direction.IDLE) {
            val nextRequest = requestQueue.removeAt(0)
            viewModelScope.launch {
                moveToFloor(nextRequest.floor)
            }
        }
    }

    private suspend fun moveToFloor(targetFloor: Int) {
        val currentFloor = _elevatorState.value.currentFloor
        val direction = if (targetFloor > currentFloor) Direction.UP else Direction.DOWN

        _elevatorState.value = _elevatorState.value.copy(direction = direction)

        var floor = currentFloor
        while (floor != targetFloor) {
            delay(1000)
            floor += if (direction == Direction.UP) 1 else -1
            _elevatorState.value = _elevatorState.value.copy(currentFloor = floor)
        }

        _elevatorState.value = _elevatorState.value.copy(doorState = DoorState.OPEN)
        delay(2000)

        _elevatorState.value = _elevatorState.value.copy(doorState = DoorState.CLOSE, direction = Direction.IDLE)
        processNextRequest()
    }

}