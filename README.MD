# Library Management System

This project implements a simple Library Management System written in Java, focusing on clean code and following Test-Driven Development (TDD) principles.

## Features:

-   **Add Books with Validation:** Users can add new books to the library. Each book is validated before being added:
    -   ISBN Format: The system checks that the ISBN is in a valid format.
    -   Publication Year: The system ensures that the publication year is a valid past year.
-   **Borrow Books:** Users can borrow books if they are available in the library.
-   **Return Books:** Users can return borrowed books, updating the library’s availability.
-   **View Available Books:** Users can view a list of all available (not borrowed) books in the library.

## Project Structure:

```java
Library Management System
│
└─── src
    ├── main
    │   └─── java
    │       └─── library
    │           ├── UI // Optional future UI implementation
    │           │   └─── App.java
    │           ├── exception  // Custom exceptions
    │           │   ├── BookAlreadyExistException.java
    │           │   ├── BookNotAvailableException.java
    │           │   ├── BookNotBorrowedException.java
    │           │   ├── BookNotFoundException.java
    │           │   └── InvalidIsbnException.java
    │           ├── Book.java
    │           └── Library.java
    │
    └─── test
        └─── java
            └─── library
                └─── LibraryTest.java
```

</br>

-   `src/main/java/library` : Contains the main application code - `Book.java` and `Library.java`.
    -   `ui` : Optional folder for future implementation of a user interface.
        -   `App.java` : This file serves as the entry point for the library system, providing a command-line interface (CLI) for users to interact with the functionalities.
    -   `exception` : It contains custom exceptions.
-   `src/test/java/library` : Contains JUnit tests for the library functionality - `LibraryTest.java`.

## Getting Started

### Prerequisites

-   Java Development Kit (JDK) 8 or later
-   Git for version control
-   Maven

### Steps to Setup

1. **Clone the repository**:

    ```shell
    git clone https://github.com/Devapatel0603/react-dynamic-hooks.git
    cd Library-Management-System
    ```

2. **To run tests**:

    ```shell
    mvn test
    ```

3. **To run Application**:

-   Build the project using Maven:

    ```shell
    mvn clean install
    ```

-   Now, run the application:
    ```shell
    java -cp target/classes library.ui.App
    ```

## Authors

[Dev Patel](https://dev-patel-0611.vercel.app/)
