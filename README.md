# Cat Gallery App

**Cat Gallery App** is a Kotlin application that displays a list of cats along with their photos and tags. Users can click
on a cat to view its details in a separate fragment. The app follows Clean Architecture principles, MVVM design pattern,
and uses Hilt for dependency injection. It also includes unit tests with JUnit4 and UI tests with Espresso.

## Features

- Display a list of cats using a RecyclerView.
- Show cat details (owner’s name, tags, cat ID, image type, creation date, and update date) in a separate detail fragment.
- Dark mode support.
- Single activity architecture for reusability.
- Retrofit for API calls (utilizing the Cataas API).
- Picasso for loading cat images.
- Room for local data storage (initially provided in JSON format).

## Screenshots
![Portrait Cat List](https://github.com/andresguerra93/CatsHomeAssigment/assets/23634271/8e0b493c-ef2f-40b3-9e20-53bc74ba7c06)

![LansCape Cat List](https://github.com/andresguerra93/CatsHomeAssigment/assets/23634271/dbf24bc3-4f4a-4907-80d3-ae7ecfa8ad52)

![Portrait Cat Detail](https://github.com/andresguerra93/CatsHomeAssigment/assets/23634271/60dc2b7f-880b-426b-bef1-1e0f705121e1)
![Cat Detail LansScape](https://github.com/andresguerra93/CatsHomeAssigment/assets/23634271/5431699b-f0f7-4c5b-8afb-cc3e1648089a)

## Installation


1. Clone the repository:

  ```bash
   git clone https://github.com/andresguerra93/CatsHomeAssigment.git 
   ```


2. Open the project in Android Studio.
3. Build and run the app on an emulator or physical device with a minimum Android API level of 25.

## Usage
- Launch the app to see a list of adorable cats.
- Tap on a cat to view its detailed information.
- Use the menu in the top-right corner to switch between light and dark mode.
Feel free to explore the codebase and contribute to this purr-fectly delightful app! 🐾
