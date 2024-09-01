package library;


public class Book {

    private String title;
    private int publishYear;
    private String author;
    private String isbn;

    public Book(String isbn, String title, String author, int publishYear) {
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.publishYear = publishYear;
    }
}
