package assignment1;

/**
 * Assignment1.Book object to be stored in a Assignment1.LibraryDatabase
 * @author rtp32
 */
public class Book {

    // title of the book
    private final String title;

    // ISBN of the book
    private final String isbn;

    // author of the book
    private final String author;


    /**
     * public class constructor
     * @param title title of the book
     * @param isbn ISBN of the book
     * @param author author of the book
     */
    public Book(String title, String isbn, String author) {
        this.title = title;
        this.isbn = isbn;
        this.author = author;
    }


    /**
     * public getter method for title of book
     * @return title of the book
     */
    public String getTitle() {
        return title;
    }


    /**
     * public getter method for ISBN of book
     * @return ISBN of the book
     */
    public String getISBN() {
        return isbn;
    }


    /**
     * public getter method for author of book
     * @return author of book
     */
    public String getAuthor() {
        return author;
    }
}
