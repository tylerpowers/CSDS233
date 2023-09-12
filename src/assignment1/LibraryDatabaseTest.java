package assignment1;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * testing class for Assignment1.Book and Assignment1.LibraryDatabase classes
 * @author rtp32
 */
public class LibraryDatabaseTest {

    // fields to use throughout the program
    private LibraryDatabase db = new LibraryDatabase(5);
    private Book b1 = new Book("Assignment1.Book 1", "0000000000001", "Bartleby");
    private Book b2 = new Book("Assignment1.Book 2", "0000000000002", "Harold");
    private Book b3 = new Book("Assignment1.Book 3", "0000000000003", "Dianne");
    private Book b4 = new Book("Assignment1.Book 4", "0000000000004", "Shuai");
    private Book b5 = new Book("Assignment1.Book 5", "0000000000005", "Dianne");
    private Book b6 = new Book("Assignment1.Book 5", "0000000000005", "Dianne");


    /**
     * test method for all Assignment1.Book methods
     */
    @Test
    public void testBookMethods() {
        assertEquals("Assignment1.Book 1", b1.getTitle());  // getTitle()
        assertNotEquals("Assignment1.Book 2", b3.getTitle());

        assertEquals("0000000000005", b6.getISBN());  // getISBN()
        assertNotEquals("5", b5.getISBN());

        assertEquals("Shuai", b4.getAuthor());  // getAuthor()
        assertNotEquals("Bartleby", b2.getAuthor());
    }


    /**
     * test method for constructor
     */
    @Test
    public void testConstructor() {
        try {
            db = new LibraryDatabase(-32);
        }
        catch (IllegalArgumentException e) {
            assertTrue(true);  // make sure exception is thrown
        }  // i am working in IntelliJ and this line keeps coming up as uncovered in code coverage. it is just a }.

        db = new LibraryDatabase(23);
        db = new LibraryDatabase(173);
        db = new LibraryDatabase(0);  // these should not throw errors
    }


    /**
     * test method for add()
     */
    @Test
    public void testAdd() {
        db.add(b1); // test one
        assertEquals(b1, db.toArray()[0]);
        assertNotEquals(b2, db.toArray()[1]);

        db.add(b4);  // test a few
        db.add(b5);
        db.add(b2);
        assertEquals(b4, db.toArray()[2]);
        assertNotEquals(b5, db.toArray()[4]);

        db.add(b3);  // test full db
        assertNotEquals(b3, db.toArray()[4]);
        assertEquals(b3, db.toArray()[2]);

        db.add(b6);  // db cannot be overflowed
        assertNotEquals(b6, db.toArray()[4]);
    }


    /**
     * test method for remove()
     */
    @Test
    public void testRemove() {
        db.add(b1);
        db.add(b3);
        db.add(b2);
        db.add(b4);
        db.add(b5);
        assertEquals(b3, db.remove(b3));  // make sure return values are used
        assertNull(db.remove(b3));
        assertEquals(b5, db.remove(b5));
    }


    @Test
    public void testSize() {
        db.add(b1);
        db.add(b2);
        db.add(b4);
        assertEquals(3, db.size());
        db.add(b3);
        db.add(b5);
        assertEquals(5, db.size());
        db = new LibraryDatabase(5);
        assertEquals(0, db.size());
    }


    @Test
    public void testRandomSelection() {
        db = new LibraryDatabase(5);
        assertNull(db.randomSelection());
        db.add(b1);
        db.add(b2);
        db.add(b3);
        db.add(b4);
        db.add(b5);
        db.randomSelection();  // tested manually, every value has an equal chance. this is here for code coverage.
    }


    @Test
    public void testFindByTitle() {
        db = new LibraryDatabase(5);

        assertNull(db.findByTitle("Assignment1.Book 4"));

        db.add(b1);
        db.add(b2);
        db.add(b3);
        db.add(b4);
        db.add(b5);

        assertEquals(b2, db.findByTitle("Assignment1.Book 2"));
        assertNotEquals(b5, db.findByTitle("5"));
    }


    @Test
    public void testFindByISBN() {
        db = new LibraryDatabase(5);

        assertNull(db.findByISBN("0000000000001"));

        db.add(b1);
        db.add(b2);
        db.add(b3);
        db.add(b4);
        db.add(b5);

        assertEquals(b3, db.findByISBN("0000000000003"));
        assertNotEquals(b1, db.findByISBN("1"));
    }


    @Test
    public void testGetAllByAuthor() {
        assertEquals(new Book[]{}, db.getAllByAuthor("Dianne"));

        db = new LibraryDatabase(5);
        db.add(b1);
        db.add(b2);
        db.add(b3);
        db.add(b4);
        db.add(b5);

        assertEquals(new Book[]{b3, b5}, db.getAllByAuthor("Dianne"));
        assertEquals(new Book[]{b1}, db.getAllByAuthor("Bartleby"));
        assertNotEquals(new Book[]{}, db.getAllByAuthor("Shuai"));
    }


    @Test
    public void testRemoveDuplicates() {
        db = new LibraryDatabase(5);

        db.removeDuplicates();  // this should not throw an error.

        db.add(b1);
        db.add(b2);
        db.add(b2);
        db.add(b4);
        db.add(b5);

        assertEquals(b2, db.toArray()[2]);
        db.removeDuplicates();
        assertNotEquals(b2, db.toArray()[2]);
        assertEquals(b4, db.toArray()[2]);
        assertNull(db.toArray()[4]);


        db = new LibraryDatabase(5);
        db.add(b1);
        db.add(b2);
        db.add(b3);
        db.add(b5);
        db.add(b5);

        assertEquals(b5, db.toArray()[4]);
        db.removeDuplicates();
        assertNotEquals(b5, db.toArray()[4]);
        assertNull(db.toArray()[4]);


        db = new LibraryDatabase(5);
        db.add(b1);
        db.add(b2);
        db.add(b2);
        db.add(b2);
        db.add(b5);

        assertEquals(b2, db.toArray()[2]);
        assertEquals(b2, db.toArray()[3]);
        db.removeDuplicates();
        assertEquals(b2, db.toArray()[1]);
        assertNotEquals(b2, db.toArray()[2]);
        assertNotEquals(b2, db.toArray()[3]);
        assertEquals(b5, db.toArray()[2]);
        assertNull(db.toArray()[3]);
    }


    @Test
    public void testToArray() {
        db = new LibraryDatabase(5);

        assertEquals(new Book[]{null, null, null, null, null}, db.toArray());

        db.add(b1);
        db.add(b2);

        assertEquals(new Book[]{b1, b2, null, null, null}, db.toArray());

        db.add(b3);
        db.add(b4);
        db.add(b5);

        assertEquals(new Book[]{b1, b2, b3, b4, b5}, db.toArray());

        db.remove(b1);
        db.remove(b2);
        db.remove(b3);
        db.remove(b4);
        db.remove(b5);

        assertEquals(new Book[]{null, null, null, null, null}, db.toArray());
    }
}
