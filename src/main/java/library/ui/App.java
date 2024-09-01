package library.ui;

import java.util.List;
import java.util.Scanner;

import library.Book;
import library.Library;

public class App {
   static Library library = new Library();
   static Scanner scanner = new Scanner(System.in);

   public static void main(String[] args) {

       System.out.println("Welcome to the Library Management System!");

       while (true) {
           printMenu();
           int choice = scanner.nextInt();
           scanner.nextLine();
           switch (choice) {
               case 1:
                   addBook();
                   break;
               case 2:
                   borrowBook();
                   break;
               case 3:
                   returnBook();
                   break;
               case 4:
                   viewAvailableBooks();
                   break;
               case 5:
                   System.out.println("Exiting the system. Goodbye!");
                   System.exit(0);
                   break;
               default:
                   System.out.println("Invalid choice. Please select a valid option.");
           }
       }
   }

   private static void printMenu() {
       System.out.println("\nPlease choose an option:");
       System.out.println("1. Add Book");
       System.out.println("2. Borrow Book");
       System.out.println("3. Return Book");
       System.out.println("4. View Available Books");
       System.out.println("5. Exit");
       System.out.print("Your choice: ");
   }

   // Add a new book
   private static void addBook() {
      try {
         System.out.print("Enter ISBN: ");
         String isbn = scanner.nextLine();
         System.out.print("Enter Title: ");
         String title = scanner.nextLine();
         System.out.print("Enter Author: ");
         String author = scanner.nextLine();
         System.out.print("Enter Publish Year: ");
         int publishYear = scanner.nextInt();
  
         Book newBook = new Book(isbn, title, author, publishYear);
  
         library.addBook(newBook);
         System.out.println("Book added successfully");
      } catch (Exception e) {
           System.out.println("Error: " + e.getMessage());
       }
   }

   // Borrow a book
   private static void borrowBook() {
       try {
           System.out.print("Enter ISBN of the book to borrow: ");
           String isbn = scanner.nextLine();
           library.borrowBook(isbn);
           System.out.println("Book borrowed successfully!");
       } catch (Exception e) {
           System.out.println("Error: " + e.getMessage());
       }
   }

   // Return a borrowed book
   private static void returnBook() {
       try {
           System.out.print("Enter ISBN of the book to return: ");
           String isbn = scanner.nextLine();
           library.returnBook(isbn);
           System.out.println("Book returned successfully!");
       } catch (Exception e) {
           System.out.println("Error: " + e.getMessage());
       }
   }

   // View available books
   private static void viewAvailableBooks() {
       List<Book> availableBooks = library.getAvailableBooks();
       if (availableBooks.isEmpty()) {
           System.out.println("No books in the library.");
       } else {
           System.out.println("\nAvailable Books:");
           availableBooks.forEach(book -> {
               System.out.println(book);
           });
       }
   }
}
