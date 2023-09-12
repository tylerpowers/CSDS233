package assignment4;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Testing class for HashTable.java, assignment 4
 * @author rtp32
 */
public class HashTableTest {

    /**
     * testing method for put() and get()
     */
    @Test
    public void testPutAndGet() {

        HashTable<Integer> table = new HashTable<>();

        table.put("apple", 1);
        assertEquals(Integer.valueOf(1), table.get("apple"));

        table.put("banana", 2);
        table.put("orange", 3);  // orange has same hash % 25 as apple
        table.put("pear", 4);


        assertEquals(Integer.valueOf(1), table.get("apple"));
        assertEquals(Integer.valueOf(2), table.get("banana"));
        assertEquals(Integer.valueOf(3), table.get("orange"));
        assertEquals(Integer.valueOf(4), table.get("pear"));

        // test other constructor

        HashTable<Integer> table2 = new HashTable<>(10);

        table2.put("gorilla", 8);
        table2.put("elephant", 9);

        assertEquals(Integer.valueOf(9), table2.get("elephant"));

        table2.put("zebra", 10);
        table2.put("lion", 11);

        assertEquals(Integer.valueOf(8), table2.get("gorilla"));
        assertEquals(Integer.valueOf(9), table2.get("elephant"));
        assertEquals(Integer.valueOf(10), table2.get("zebra"));

    }

    /**
     * testing method for remove() and size()
     */
    @Test
    public void testRemoveAndSize() {

        HashTable<Integer> table = new HashTable<>();
        table.put("apple", 1);
        table.put("banana", 2);
        table.put("orange", 3);
        table.put("pear", 4);

        assertEquals(Integer.valueOf(1), table.remove("apple"));
        assertEquals(Integer.valueOf(2), table.remove("banana"));
        assertEquals(2, table.size());

        table.put("blackberry", 16);
        table.put("blackberry", 20);
        assertEquals(3, table.size());

        assertEquals(Integer.valueOf(3), table.remove("orange"));
        assertEquals(Integer.valueOf(4), table.remove("pear"));
        assertEquals(Integer.valueOf(36), table.remove("blackberry"));

        assertEquals(0, table.size());
    }



}
