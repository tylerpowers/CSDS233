package assignment2;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * testing class for assignment2.Stack<T> class
 */

public class StackTest {

    /**
     * testing method for push() and pop()
     */
    @Test
    public void testPushAndPop() {
        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.push(2);
        stack.push(3);

        assertEquals(Integer.valueOf(3), stack.pop());
        assertNotEquals(Integer.valueOf(3), stack.pop());
        assertEquals(Integer.valueOf(1), stack.pop());
    }


    /**
     * testing method for peek()
     */
    @Test
    public void testPeek() {
        Stack<String> stack = new Stack<>();
        stack.push("apple");
        stack.push("banana");

        assertNotEquals("apple", stack.peek());
        assertEquals("banana", stack.peek());
    }

}
