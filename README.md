Case Study: Elevator Control App
Background:
Imagine managing a smart building where users can summon elevators and select
destination floors via a mobile app. As an Android developer, your task is to design a
mobile interface that controls an elevator system, allowing users to request the elevator
from any floor and select their desired destination once inside the elevator.
The app will interact with the elevator system, reflecting real-time statuses such as floor
number, movement direction, and door state.
Objective:
Build a simple Elevator Control App that allows users to:
• Request an elevator from their current floor.
• Select a destination floor once inside the elevator.
• View the elevator’s current state (e.g., floor number, direction).
Requirements:
Architecture & Frameworks:
• Clean Architecture: Focus on separating UI, business logic, and data layers.
• MVVM: Use the MVVM pattern to manage state and UI updates.
• Kotlin: Code must be written in Kotlin.
• Jetpack Compose: Use Jetpack Compose for building a simple, responsive UI.
Components:
• User Interaction:
o Two buttons on each floor (Up/Down) for requesting the elevator.
o Once inside the elevator, a set of buttons for selecting the destination
floor.
• Basic Elevator States:
o Display the current floor of the elevator.
o Display whether the elevator is moving up or down.
o Doors open when arriving at a floor, and close after a brief delay.
• Loading Spinner:
o Display a spinner when fetching or updating elevator state data (e.g., when
calling the elevator or selecting a floor).
• Request Queue:
o When a button is pressed, it simulates a request being added to the
queue, and the elevator moves to service it
Unit Testing Requirements:
You are expected to write unit tests alongside your implementation. Focus on the
following components for testing:
1. Elevator state management
2. Request queue handling
_____________________________________________________________________________________________
Notes for the Developer:
1. Spinner Implementation: Use Jetpack Compose to implement a spinner that
appears while elevator information is being fetched from the backend system
(such as current floor status).
2. Clean Architecture: Ensure the application is modular and follows clean
architecture principles, separating UI, domain, and data layers.
3. User Experience: Pay attention to user interactions with the buttons. Ensure the
button illuminations and elevator movements are smooth and intuitive.
4. Make sure you have followed all the standard coding and formatting guidelines.
5. Share the code with us in a Zip file or GitHub repository once you have
completed the assignment.
6. Kindly submit the video or screenshot of output (you got from your side) along
with the assessment submission
