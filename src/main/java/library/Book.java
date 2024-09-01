package library;

import java.time.LocalDate;
import java.util.Objects;

import library.exception.InvalidIsbnException;


public class Book {

    private String title;
    private int publishYear;
    private String author;
    private String isbn;

    public Book(String isbn, String title, String author, int publishYear) throws InvalidIsbnException {

        // Validate ISBN (must be 13 characters long)
        if (isbn.length() != 13) {
            throw new InvalidIsbnException("Please provide a valid ISBN number (must be 13 characters long)");
        }

        // Validate ISBN checksum
        int checksum = calculateIsbn13Checksum(isbn);
        if (checksum != Character.getNumericValue(isbn.charAt(12))) {
            throw new InvalidIsbnException("Invalid ISBN checksum");
        }

        // Validate Publish Year (must be 4 digits and not in the future)
        if (String.valueOf(publishYear).length() != 4) {
            throw new IllegalArgumentException("Publish year should be 4 digits long");
        } else if (publishYear > LocalDate.now().getYear()) {
            throw new IllegalArgumentException("Publish year should not be in the future");
        }

        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.publishYear = publishYear;
    }

    /** 
     * This checksum is used to verify the validity of the ISBN.
     *
     * @param isbn The 13-digit ISBN number as a String.
     * @return The calculated checksum digit.
     * 
     * Refer this to see ISBN Format : https://isbn-information.com/check-digit-for-the-13-digit-isbn.html
     */
    private static int calculateIsbn13Checksum(String isbn) throws InvalidIsbnException {
        try {
            int sum = 0;
            for (int i = 0; i < isbn.length() - 1; i++) {
                int digit = Character.getNumericValue(isbn.charAt(i));
                sum += (i % 2 == 0) ? digit : digit * 3;
            }
            int remainder = sum % 10;
            return remainder == 0 ? 0 : (10 - remainder);
        } catch (Exception e) {
            throw new InvalidIsbnException("Invalid ISBN checksum");
        }
    }

    // Getter methods for book attributes
    public String getAuthor() {
        return author;
    }

    public String getIsbn() {
        return isbn;
    }

    public int getPublishYear() {
        return publishYear;
    }

    public String getTitle() {
        return title;
    }

    /**
     * Override equals method to compare books based on their attributes
     * 
     * @param obj The object to compare with this Book.
     * @return true if the given object is equal to this Book, false otherwise.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true; // Check if the same reference
        if (obj == null || getClass() != obj.getClass()) return false; // Check for null and class type
        Book book = (Book) obj;
        return isbn.equals(book.isbn) &&
                title.equals(book.title) &&
                author.equals(book.author) &&
                publishYear == book.publishYear;
    }

    /** 
     * Override hashCode method to generate a hash based on book attributes
     * 
     * @return The hash code value for this Book.
     */ 
    @Override
    public int hashCode() {
        return Objects.hash(isbn, title, author, publishYear);
    }

    /**
     * Override toString method for a formatted string representation of the book
     * 
     * @return A formatted string representation of this Book.
     */
    @Override
    public String toString() {
        return String.format("Book[ ISBN = %s, Title = %s, Author = %s, Publish Year = %s]", isbn, title, author, publishYear);
    }
}
