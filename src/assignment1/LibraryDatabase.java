package assignment1;

import java.util.Random;

/**
 * a database which stores Assignment1.Book objects in order of their ISBN.
 * implemented using an array.
 * @author rtp32
 */
public class LibraryDatabase {

    // private array of all books stored (null if no book there)
    private Book[] arr;

    // current number of books in the database
    private int size;


    /**
     * public constructor for class
     * @param capacity maximum number of books stored in the database
     */
    public LibraryDatabase(int capacity) {
        if (capacity < 0)
            throw new IllegalArgumentException();
        arr = new Book[capacity];
    }


    /**
     * adds a book to the list, sorted by ISBN
     * @param book Assignment1.Book object to be added
     */
    public void add(Book book) {
        int i = 0;
        Book save1;
        Book save2;

        if (size < arr.length) {  // if there is space in the database...
            while (arr[i] != null && Integer.parseInt(arr[i].getISBN()) <= Integer.parseInt(book.getISBN()))
                i++;  // iterates until there is an open space or the location of the book is found

            if (i != arr.length - 1) {  // edge cases do not work in this algorithm
                save1 = arr[i];
                arr[i++] = book;
                save2 = arr[i];
                while (arr[i] != null && i < size() + 1) {  // shifts list over
                    arr[i++] = save1;
                    save1 = save2;
                    save2 = arr[i];
                }
                arr[i] = save1;
            }

            else
                arr[i] = book; // edge case - book is new last element

            size++;  // increments size field
        }
    }


    /**
     * removes a book from the list, searches by ISBN
     * @param book Assignment1.Book object to be removed
     * @return the object removed, null if not found
     */
    public Book remove(Book book) {
        int i = 0;
        Book save;

        while (i < size() && arr[i] != null && !arr[i].getISBN().equals(book.getISBN()))
            i++;  // increments until value is found / verified not found

        save = arr[i];

        if (i == size() || arr[i] == null)
            return null;  // if not found, returns null

        while (i < arr.length - 1) {  // shifts list over
            arr[i] = arr[i + 1];
            i++;
        }

        arr[i] = null;

        size--;
        return save;
    }


    /**
     * returns number of books currently stored in the database
     * @return an int representing the current size of the database
     */
    public int size() {
        return size;
    }


    /**
     * returns a random element of the database
     * @return a random book in the database, null if empty
     */
    public Book randomSelection() {
        if (size() == 0)  // empty list means no books to choose from
            return null;
        return arr[new Random().nextInt(size())];  // picks a random occupied index
    }


    /**
     * returns a book with the specified title
     * @param title a String containing the desired title
     * @return the book with given title, null if not found
     */
    public Book findByTitle(String title) {
        for (int i = 0; i < size(); i++) { // iterates through array until title is found
            if (arr[i].getTitle().equals(title))
                return arr[i];
        }
        return null;
    }


    /**
     * returns a book with the specified ISBN
     * @param isbn a String containing the desired ISBN
     * @return the book with given ISBN, null if not found
     */
    public Book findByISBN(String isbn) {
        for (int i = 0; i < size(); i++) {  // iterates through array until ISBN is found
            if (arr[i].getISBN().equals(isbn))
                return arr[i];
        }
        return null;
    }


    /**
     * returns an array with all books written by the specified author
     * @param author a String containing the desired author
     * @return an array of all books written by author, empty array if no books found
     */
    public Book[] getAllByAuthor(String author) {
        int count = 0;
        Book[] bigArray = new Book[size()];

        for (int i = 0; i < size(); i++) {  // iterates through array finding all Books with this author
            if (arr[i].getAuthor().equals(author)) {
                bigArray[count++] = arr[i];
            }
        }

        if (count < bigArray.length) {  // makes a new array with no null values
            Book[] littleArray = new Book[count];
            for (int i = 0; i < count; i++)
                littleArray[i] = bigArray[i];
            return littleArray;
        }
        else
            return bigArray;
    }


    /**
     * removes all books with duplicate ISBN from database
     */
    public void removeDuplicates() {
        Book[] removed = new Book[arr.length];

        int i = size() - 1;
        while (i > 0 && arr[i] != null) {
            if (arr[i].getISBN().equals(arr[i - 1].getISBN())) {
                arr[i] = null;
                size--;
            }
            i--;
        }

        int pos = 0;
        for (int j = 0; j < arr.length; j++) {
            if (arr[j] != null)
                removed[pos++] = arr[j];
        }

        arr = removed;
    }


    /**
     * returns an array of all books in the database
     * @return Assignment1.Book array of all books in database
     */
    public Book[] toArray() {
        return arr;
    }
}
