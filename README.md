# WalmartApp

## Description

This Android application fetches and displays a list of countries from a remote API. The app uses `RecyclerView` to show the following details for each country:

- **Name**
- **Region**
- **Code**
- **Capital**

The list is displayed in the same order as in the provided JSON file. The application follows the MVVM architecture and adheres to modern Android development best practices.

---

## Features

- Fetches country data from the [remote API](https://gist.githubusercontent.com/peymano-wmt/32dcb892b06648910ddd40406e37fdab/raw/db25946fd77c5873b0303b858e861ce724e0dcd0/countries.json).
- Displays countries in a scrollable `RecyclerView`.
- Implements robust error handling for network and data issues.
- Maintains UI state across device rotations.
- Manual Dependency Injection for better control over dependencies.
- Clean architecture for maintainable and testable code.

---

## Tech Stack and Libraries

The app uses the following stack and libraries:

- **Language**: Kotlin
- **Architecture**: MVVM (Model-View-ViewModel)
- **UI**:
    - AndroidX `RecyclerView`
    - Material Design Components
- **Networking**:
    - Retrofit for HTTP requests
    - Gson for JSON parsing
- **Concurrency**:
    - Kotlin Coroutines for asynchronous programming
- **Testing**:
    - Mockk for mocking dependencies
    - JUnit for unit tests
    - AndroidX Testing Library for instrumentation tests
- **Utilities**:
    - DiffUtil for optimized list updates
    - View Binding for easy UI manipulation

---
