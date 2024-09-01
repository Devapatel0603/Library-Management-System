package library;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;

import library.exception.BookAlreadyExistException;
import library.exception.BookNotAvailableException;
import library.exception.BookNotFoundException;
import library.exception.InvalidIsbnException;


public class LibraryTest {

    /**
     * Tests that a book can be successfully added to the library.
     */
    @Test
    public void shouldAddBookSuccessfully() throws InvalidIsbnException, BookAlreadyExistException {

        Library library = new Library();

        Book book1 = new Book("9789295055025", "Book Title", "Author Name", 2022);

        library.addBook(book1);

        // Verify that the book was added successfully
        assertEquals(book1, library.getBookByIsbn("9789295055025"));
    }

    /**
     * Tests that attempting to add a book with same ISBN number throws an exception.
     */
    @Test
    public void shouldNotAddBookWithDuplicateIsbn() throws InvalidIsbnException, BookAlreadyExistException {

        Library library = new Library();

        Book book1 = new Book("9789295055025", "Book Title 1", "Author Name 1", 2022);
        Book book2 = new Book("9789295055025", "Book Title 2", "Author Name 2", 2023);

        library.addBook(book1);

        // Verify that adding a duplicate book throws an exception
        Throwable exception = assertThrows(BookAlreadyExistException.class, () -> library.addBook(book2));
        assertEquals("Book with the given ISBN already exists in the library", exception.getMessage());
    }

    /**
     * Tests that attempting to add a book with invalid ISBN number throws an exception.
     */
    @Test
    public void shouldThrowExceptionWhenAddingBookWithInvalidIsbn() {
        // Test with invalid ISBN lengths (Length should be 13 characters long)
        Throwable exception1 = assertThrows(InvalidIsbnException.class, () -> new Book("1234567", "Book Title", "Author Name", 2022));
        assertEquals("Please provide a valid ISBN number (must be 13 characters long)", exception1.getMessage());

        // Test with invalid ISBN characters
        Throwable exception2 = assertThrows(InvalidIsbnException.class, () -> new Book("123456789012A", "Book Title", "Author Name", 2022));
        assertEquals("Invalid ISBN checksum", exception2.getMessage());

        // Test with invalid ISBN format
        // Refer this to see ISBN Format : https://isbn-information.com/check-digit-for-the-13-digit-isbn.html
        Throwable exception3 = assertThrows(InvalidIsbnException.class, () -> new Book("1234567890123", "Book Title", "Author Name", 2022));
        assertEquals("Invalid ISBN checksum", exception3.getMessage());
    }

    /**
     * Tests that a book can be borrowed successfully.
     */
    @Test
    public void shouldBorrowBook() throws InvalidIsbnException, BookAlreadyExistException {

        Library library = new Library();

        Book book1 = new Book("9780596520687", "Book Title 1", "Author Name 1", 2021);
        Book book2 = new Book("9789295055025", "Book Title 2", "Author Name 2", 2022);
        Book book3 = new Book("9780306406157", "Book Title 3", "Author Name 3", 2023);

        library.addBook(book1);
        library.addBook(book2);
        library.addBook(book3);

        library.borrowBook("9780596520687");

        // Create expected list of borrowed books
        List<Book> expectedBorrowedBooks = List.of(
            new Book("9780596520687", "Book Title 1", "Author Name 1", 2021)
        );

         // Verify that the borrowed book list matches the expected result
        assertEquals(expectedBorrowedBooks, library.getBorrowedBooks());
    }

    /**
     * Tests that attempting to borrow an already borrowed book throws an exception.
     */
    @Test
    public void shouldNotBorrowAlreadyBorrowedBook() throws InvalidIsbnException, BookAlreadyExistException {

        Library library = new Library();

        Book book1 = new Book("9780596520687", "Book Title 1", "Author Name 1", 2021);
        Book book2 = new Book("9789295055025", "Book Title 2", "Author Name 2", 2022);
        Book book3 = new Book("9780306406157", "Book Title 3", "Author Name 3", 2023);

        library.addBook(book1);
        library.addBook(book2);
        library.addBook(book3);

        library.borrowBook("9780596520687");

        // Verify that attempting to borrow an already borrowed book throws an exception
        Throwable exception = assertThrows(BookNotAvailableException.class, () -> library.borrowBook("9780596520687"));
        assertEquals("Book is already borrowed", exception.getMessage());
    }

    /**
     * Tests that attempting to borrow a book that doesn't exist in the library throws an exception.
     */
    @Test
    public void shouldThrowExceptionWhenBorrowingNonExistentBook() {
        Library library = new Library();

        // Try borrowing a book with a non-existent ISBN
        Throwable exception = assertThrows(BookNotFoundException.class, () -> library.borrowBook("1234567890123"));
        assertEquals("Book with the given ISBN does not exist", exception.getMessage());
    }
}