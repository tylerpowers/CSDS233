package assignment2;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * testing class for assignment2.StringManipulation class
 */

public class StringManipulationTest {

    /**
     * test metod for toPostfix
     */
    @Test
    public void testToPostfix() {
        String infix1 = "3 + 4 * 2 - 9";
        String expectedPostfix1 = "3 4 2 * + 9 -";
        String actualPostfix1 = StringManipulation.toPostfix(infix1);
        assertEquals(expectedPostfix1, actualPostfix1);

        String infix2 = "5 * ( 12 + 3 ) - 5 / 8";
        String expectedPostfix2 = "5 12 3 + * 5 8 / -";
        String actualPostfix2 = StringManipulation.toPostfix(infix2);
        assertEquals(expectedPostfix2, actualPostfix2);

        String infix3 = "( 19 + 2 ) * 3 - 2";
        String expectedPostfix3 = "19 2 + 3 * 2 -";
        String actualPostfix3 = StringManipulation.toPostfix(infix3);
        assertEquals(expectedPostfix3, actualPostfix3);
    }


    /**
     * testing method for result()
     */
    @Test
    public void testResult() {
        String postfix1 = "2 3 + 4 *";
        int expected1 = 20;
        int actual1 = StringManipulation.result(postfix1);
        assertEquals(expected1, actual1);

        String postfix2 = "15 2 - 3 *";
        int expected2 = 39;
        int actual2 = StringManipulation.result(postfix2);
        assertEquals(expected2, actual2);

        String postfix3 = "1 2 + 3 4 - *";
        int expected3 = -3;
        int actual3 = StringManipulation.result(postfix3);
        assertEquals(expected3, actual3);

        String postfix4 = "3 5 + 24 2 - *";
        int expected4 = 176;
        int actual4 = StringManipulation.result(postfix4);
        assertEquals(expected4, actual4);
    }


    /**
     * testing method for smallestNumber()
     */
    @Test
    public void testSmallestNumber() {
        // Test cases with valid input
        assertEquals("4", StringManipulation.smallestNumber("70004", 1));
        assertEquals("23", StringManipulation.smallestNumber("102345", 3));
        assertNotEquals("9", StringManipulation.smallestNumber("9012", 1));
        assertEquals("0", StringManipulation.smallestNumber("000", 2));
        assertEquals("123456789", StringManipulation.smallestNumber("123456789", 0));

        // Test case with n = length of number
        assertEquals("0", StringManipulation.smallestNumber("12345", 5));

    }


    /**
     * testing method for unbelievableString()
     */
    @Test
    public void testUnbelievableString() {
        assertEquals("abdE", StringManipulation.unbelievableString("abDDdddE"));
        assertEquals("abcdefg", StringManipulation.unbelievableString("abcdefg"));
        assertEquals("", StringManipulation.unbelievableString("AAaaBBbbCCcc"));
        assertEquals("A", StringManipulation.unbelievableString("A"));
    }


}
