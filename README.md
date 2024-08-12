# Mini Logbook - Blood Glucose Tracker

## Overview
Mini Logbook is a simple application designed to help users track and monitor their blood glucose levels. This app focuses on providing an intuitive and user-friendly interface, along with efficient and reliable data storage. It showcases the use of modern Android development tools and best practices like Clean Architecture, Kotlin, Room, Flow, and Hilt.

## Features

- **Blood Glucose Entry:** Users can enter and save their blood glucose values in either mmol/L or mg/dL units.
- **Data Visualization:** The app allows users to view all previously entered values and calculates the average blood glucose level.
- **Unit Conversion:** Values entered in one unit can be automatically converted to the other (1 mmol/L = 18.0182 mg/dL).
- **Data Persistence:** Records are stored locally using Room for offline access.
- **Clean Architecture:** The project is structured to follow the Clean Architecture principles, ensuring separation of concerns and easy maintainability.
- **Dependency Injection:** Hilt is used for managing dependencies across the app.
- **Dark Mode Support:** The app supports both light and dark themes.

## Architecture
This project follows Clean Architecture principles, dividing the code into three main layers:

1. **Domain Layer:** Contains business logic and use cases. It is completely independent of any frameworks.
2. **Data Layer: **Handles data management, including Room database interactions. The `BloodGlucoseRepositoryImpl` acts as the bridge between the domain and data layers.
3. **Presentation Layer:** Manages UI and user interaction, using ViewModels to interact with the domain layer.

## Technologies Used
1. **Kotlin:** The programming language used throughout the project.
2. **Room:** For local database management.
3. **Flow:** For handling asynchronous data streams and reacting to data changes.
4. **Hilt:** For dependency injection, ensuring a clean and manageable codebase.
5. **RecyclerView:** For displaying lists of data in the UI.
6. **Coroutines:** For handling asynchronous operations.

## Setup Instructions

1. Clone the repository:

```
git clone https://github.com/moizest89/minilogbook.git
cd minilogbook
```

2. Open the project in Android Studio.

3. Build and Run the app on an Android device or emulator.

## How to Use
1. **Select Unit:** Choose the desired unit (mmol/L or mg/dL) for entering your blood glucose values.
2. **Enter Value**: Use the numeric keyboard to input your blood glucose value.
3. **Save:** Press "Save" to store the value and update the average displayed.
4. **View Entries:** Scroll through your past entries displayed in a list.
5. **Switch Units:** Change the unit and watch all values and the average automatically convert.

## Upcoming Features

There are some improvements and additional features planned for the future:

- **Migrating UI to Jetpack Compose**: Transitioning the UI to Jetpack Compose for a more modern and declarative approach to building UIs.
- **Edit/Delete Functionality**: Adding the ability to edit or delete existing entries in the blood glucose log.
- **Additional Testing**: Expanding the test coverage to include UI tests and more comprehensive unit tests.

## About Me
### Hello!
I'm a passionate Android developer with a focus on creating user-friendly and efficient mobile applications. This project, which revolves around a simple blood glucose logbook, showcases my skills in building responsive and intuitive UIs, implementing Clean Architecture, and leveraging modern tools like Kotlin, Room, Flow, and Hilt for dependency injection.

With this project, I've aimed to create a seamless experience for users who need to track and monitor their blood glucose levels, all while ensuring the app remains stable, responsive, and easy to maintain. I'm always excited to explore new technologies and continuously improve my development practices.

Connect with me on [LinkedIn](https://www.linkedin.com/in/moizest89/ '@moizest89').




## License
This project is licensed under the MIT License - see the LICENSE file for details.