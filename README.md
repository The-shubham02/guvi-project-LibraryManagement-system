# guvi-project-LibraryManagement-system
Library Management System

A simple Java-based Library Management System that allows users to manage books, including adding, updating, deleting, and listing books.

Features

- Add new books to the library
- Update existing book details
- Delete books from the library
- List all books in the library

Technologies Used

- Java
- H2 Database
- JDBC

Setup and Installation

1. Clone the repository: git clone https://github.com/your-username/LibraryManagementSystem.git
2. Navigate to the project directory: cd LibraryManagementSystem
3. Compile the code: javac LibraryManagement.java
4. Run the application: java LibraryManagement

Usage

1. Run the application and follow the menu prompts to manage books.
2. Choose an option from the menu:
    - 1: List books
    - 2: Add a book
    - 3: Update a book
    - 4: Delete a book
    - 5: Exit

Database Configuration

- The application uses an H2 database, which is a file-based database.
- The database file is created in the same directory as the application.
- The database connection settings are defined in the LibraryManagement.java file:
    - JDBC_URL: jdbc:h2:./librarydb
    - JDBC_USERNAME: sa
    - JDBC_PASSWORD: (empty string)

Contributing

Contributions are welcome! If you'd like to contribute to this project, please fork the repository and submit a pull request.

License

This project is licensed under the MIT License. See the LICENSE file for details.

Author

shubham pandey
