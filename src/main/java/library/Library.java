package library;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import library.exception.BookAlreadyExistException;
import library.exception.BookNotAvailableException;
import library.exception.BookNotBorrowedException;
import library.exception.BookNotFoundException;

public class Library {

    // Map to store all books with ISBN as the key
    private Map<String, Book> books = new HashMap<>();
    private Map<String, Book> borrowedBooks = new HashMap<>();

    /**
     * Adds a new book to the library.
     * 
     * @param newBook The book to be added.
     * @throws BookAlreadyExistException if a book with the given ISBN already exists.
     */
    public void addBook(Book newBook) throws BookAlreadyExistException {

        // Check if book already exists based on ISBN
        if (books.containsKey(newBook.getIsbn())) {
            throw new BookAlreadyExistException("Book with the given ISBN already exists in the library");
        }

        books.put(newBook.getIsbn(), newBook);
    }

    /**
     * Borrows a book from the library.
     * 
     * @param isbn The ISBN of the book to be borrowed.
     * @throws BookNotAvailableException if the book is already borrowed
     * @throws BookNotFoundException if book does not exist in the library.
     */
    public void borrowBook(String isbn) throws BookNotFoundException, BookNotAvailableException {

        // Check if the book is already borrowed
        if (borrowedBooks.containsKey(isbn)) {
            throw new BookNotAvailableException("Book is already borrowed");
        }

        // Check if the book exists in the library
        if (!books.containsKey(isbn)) {
            throw new BookNotFoundException("Book with the given ISBN does not exist");

        }

        // Move the book from available to borrowed
        Book borrowedBook = books.get(isbn);

        borrowedBooks.put(isbn, borrowedBook);
    }

    /**
     * Returns a borrowed book to the library.
     * 
     * @param isbn The ISBN of the book to be returned.
     * @throws BookNotFoundException if the book does not exist in the library.
     * @throws BookNotBorrowedException if the book is not borrowed
     */
    public void returnBook(String isbn) throws BookNotFoundException, BookNotBorrowedException {

        // Check if the book is exist in the library
        if (!books.containsKey(isbn)) {
            throw new BookNotFoundException("Book with the given ISBN does not exist");
        }

        // Check if book is not borrowed
        if (!borrowedBooks.containsKey(isbn)) {
            throw new BookNotBorrowedException("Book is not borrowed");
        }

        borrowedBooks.remove(isbn);
    }

    /**
     * Retrieves a list of all borrowed books.
     * 
     * @return A list of books that are currently borrowed.
     */
    public List<Book> getBorrowedBooks() { 
        return new ArrayList<>(borrowedBooks.values()); 
    }

    /**
     * Retrieves a book by its ISBN.
     * 
     * @param isbn The ISBN of the book to retrieve.
     * @return The book with the specified ISBN, or null if not found.
     */
    public Book getBookByIsbn(String isbn) {
        return books.get(isbn);
    }

    public List<Book> getAvailableBooks() {
        List<Book> availableBooks = new ArrayList<>();

        return availableBooks;
    }
}
