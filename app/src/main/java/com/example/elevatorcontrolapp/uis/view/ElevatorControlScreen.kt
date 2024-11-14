package com.example.elevatorcontrolapp.uis.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicText
import androidx.compose.material.Button
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.elevatorcontrolapp.domain.model.Direction
import com.example.elevatorcontrolapp.uis.viewmodel.ElevatorViewModel

@Composable
fun ElevatorControlScreen() {
    val viewModel: ElevatorViewModel = viewModel()
    val elevatorState by viewModel.elevatorState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        BasicText("Current Floor: ${elevatorState.currentFloor}")
        BasicText("Direction: ${elevatorState.direction}")
        BasicText("Door: ${elevatorState.doorState}")

        Spacer(modifier = Modifier.height(24.dp))

        Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
            Button(onClick = { viewModel.requestElevator(1, Direction.UP) }) {
                BasicText("Floor 1")
            }
            Button(onClick = { viewModel.requestElevator(2, Direction.UP) }) {
                BasicText("Floor 2")
            }
            Button(onClick = { viewModel.requestElevator(3, Direction.UP) }) {
                BasicText("Floor 3")
            }
            Button(onClick = { viewModel.requestElevator(4, Direction.UP) }) {
                BasicText("Floor 4")
            }
            Button(onClick = { viewModel.requestElevator(5, Direction.UP) }) {
                BasicText("Floor 5")
            }
        }
    }
}