package com.example.elevatorcontrolapp

import android.util.Log
import app.cash.turbine.test
import com.example.elevatorcontrolapp.domain.model.Direction
import com.example.elevatorcontrolapp.domain.model.DoorState
import com.example.elevatorcontrolapp.uis.viewmodel.ElevatorViewModel
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertSame
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestCoroutineScheduler
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class ElevatorViewModelTest {

    private lateinit var viewModel: ElevatorViewModel
    private val testDispatcher = StandardTestDispatcher(TestCoroutineScheduler())

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        viewModel = ElevatorViewModel()
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun elevator_move_to_floor_2_then_open_door_close_door() = runTest {
        viewModel.requestElevator(2, Direction.UP)

        viewModel.elevatorState.test {
            val initialState = awaitItem()
            println("Initial State: $initialState")
            assertEquals(1, initialState.currentFloor)
            assertEquals(Direction.IDLE, initialState.direction)
            assertEquals(DoorState.CLOSE, initialState.doorState)

            val directionUpdate = awaitItem()
            assertEquals(1, directionUpdate.currentFloor)
            assertEquals(Direction.UP, directionUpdate.direction)
            assertEquals(DoorState.CLOSE, directionUpdate.doorState)

            testDispatcher.scheduler.advanceTimeBy(1000)

            val floorUpdate = awaitItem()
            assertEquals(2, floorUpdate.currentFloor)

            val doorState = awaitItem()
            assertEquals(DoorState.OPEN, doorState.doorState)

            testDispatcher.scheduler.advanceTimeBy(2000)

            val reachedSecondFloor = awaitItem()
            assertEquals(2,reachedSecondFloor.currentFloor)
            assertEquals(Direction.IDLE, reachedSecondFloor.direction)
            assertSame(DoorState.CLOSE,reachedSecondFloor.doorState)

            cancelAndConsumeRemainingEvents()
        }
    }
}