package library;

import java.util.HashMap;
import java.util.Map;

import library.exception.BookAlreadyExistException;

public class Library {

    // Map to store all books with ISBN as the key
    private Map<String, Book> books = new HashMap<>();
    private Map<String, Book> borrowedBooks = new HashMap<>();

    /**
     * Adds a new book to the library.
     * 
     * @param newBook The book to be added.
     * @throws IllegalArgumentException if a book with the given ISBN already exists.
     */
    public void addBook(Book newBook) throws BookAlreadyExistException {

        // Check if book already exists based on ISBN
        if (books.containsKey(newBook.getIsbn())) {
            throw new BookAlreadyExistException("Book with the given ISBN already exists in the library");
        }

        books.put(newBook.getIsbn(), newBook);
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
}
