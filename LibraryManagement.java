import java.sql.*;
import java.util.Scanner;

public class LibraryManagement {

    private static final String JDBC_URL = "jdbc:h2:./librarydb"; // persistent file-based db
    private static final String JDBC_USERNAME = "sa";
    private static final String JDBC_PASSWORD = "";

    private Connection connection;

    public LibraryManagement() {
        try {
            connection = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);
            createTableIfNotExists();
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    private void createTableIfNotExists() throws SQLException {
        String sql = "CREATE TABLE IF NOT EXISTS books (" +
                "id INT AUTO_INCREMENT PRIMARY KEY," +
                "title VARCHAR(255) NOT NULL," +
                "author VARCHAR(255) NOT NULL," +
                "year INT NOT NULL" +
                ")";
        try (Statement stmt = connection.createStatement()) {
            stmt.execute(sql);
        }
    }

    public void addBook(String title, String author, int year) throws SQLException {
        String sql = "INSERT INTO books (title, author, year) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, title);
            pstmt.setString(2, author);
            pstmt.setInt(3, year);
            pstmt.executeUpdate();
            System.out.println("Book added successfully.");
        }
    }

  
         
 public void listBooks() throws SQLException {
      String sql = "SELECT * FROM books";
     try (Statement stmt = connection.createStatement() ;
          ResultSet rs = stmt.executeQuery(sql)) {
             System.out.println("\tLibrary Books:");
             System.out.printf("%-5s %-30s %-30s %-5s%n", "ID", "Title", "Author", "Year");
             System.out.println("\t---------------------------------------------------------------");
             boolean hasBooks = false;
             while (rs.next()) {
                 hasBooks = true;
                 int id = rs.getInt("id");
                 String title = rs.getString("title");
                 String author = rs.getString("author");
                  int year = rs.getInt("year");
                  System.out.printf("%-5d %-30s %-30s %-5d%n", id, title, author, year);
              }
              if (!hasBooks) {
                  System.out.println("\tNo books available.");
              }
          }
      }
  
  public void updateBook(int id, String newTitle, String newAuthor, int newYear) throws SQLException {
      String sql = "UPDATE books SET title=?, author=?, year=? WHERE id=?";
      try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
          pstmt.setString(1, newTitle);
          pstmt.setString(2, newAuthor);
          pstmt.setInt(3, newYear);
          pstmt.setInt(4, id);
          int rows = pstmt.executeUpdate();
          if (rows > 0) {
              System.out.println("\tBook updated successfully.");
          } else {
              System.out.println("\tBook with ID " + id + " not found.");
          }
      }
  }
  
  public void deleteBook(int id) throws SQLException {
      String sql = "DELETE FROM books WHERE id=?";
      try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
          pstmt.setInt(1, id);
          int rows = pstmt.executeUpdate();
        // Method implementation likely to beint rows = pstmt.executeUpdate();
if (rows > 0) {
    System.out.println("Book deleted successfully.");
} else {
    System.out.println("Book with ID " + id + " not found.");}}
}

public void close() {
    try {
        if (connection != null && !connection.isClosed()) {
            connection.close();
        }
    } catch (SQLException ignored) {
    }
}
public static void main(String[] args) {
    LibraryManagement app = new LibraryManagement();
    Scanner scanner = new Scanner(System.in);

    System.out.println("===== Welcome to the Library Management System =====");
    while (true) {
        System.out.println("\nMenu:");
        System.out.println("1. List books");
        System.out.println("2. Add a book");
        System.out.println("3. Update a book");
        System.out.println("4. Delete a book");
        System.out.println("5. Exit");
        System.out.print("Enter your choice (1-5): ");
        String choice = scanner.nextLine();
        try {
            switch (choice) {
                case "1":
                    app.listBooks();
                    break;
                case "2":
                    System.out.print("Enter title: ");
                    String title = scanner.nextLine(); 
   System.out.print("Enter author: ");
String author = scanner.nextLine();
System.out.print("Enter year: ");
int year = Integer.parseInt(scanner.nextLine());
app.addBook(title, author, year);
break;

case "3":
System.out.print("Enter book ID to update: ");
int updateId = Integer.parseInt(scanner.nextLine());
System.out.print("Enter new title: ");
String newTitle = scanner.nextLine();
System.out.print("Enter new author: ");
String newAuthor = scanner.nextLine();
System.out.print("Enter new year: ");
int newYear = Integer.parseInt(scanner.nextLine());
app.updateBook(updateId, newTitle, newAuthor, newYear);
break;

case "4":
System.out.print("Enter book ID to delete: ");
int deleteId = Integer.parseInt(scanner.nextLine());
app.deleteBook(deleteId);
break;

case "5":
System.out.println("Exiting the system. Goodbye!");
app.close();
scanner.close();
System.exit(0);

default:
System.out.println("Invalid choice, please try again.");}}

catch (SQLException e) {
System.out.println("Database error: " + e.getMessage());
} catch (NumberFormatException nfe) {
System.out.println("Invalid input, please enter numbers where required.");
}}}}