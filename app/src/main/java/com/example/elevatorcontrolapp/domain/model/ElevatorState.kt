package com.example.elevatorcontrolapp.domain.model

enum class DoorState{
    OPEN,CLOSE
}

enum class Direction{
    UP,DOWN,IDLE
}

data class ElevatorState(
    val currentFloor:Int=1,
    val doorState: DoorState=DoorState.CLOSE,
    val direction: Direction=Direction.IDLE
)