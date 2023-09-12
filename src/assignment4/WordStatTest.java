package assignment4;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * testing class for WordStat
 * @author rtp32
 */
public class WordStatTest {

    /**
     * testing method for wordStat methods
     */
    @Test
    public void testWordStat() {
        WordStat ws = new WordStat("src\\wordstat_test.txt");

        // wordCount()
        assertEquals(2, ws.wordCount("banana"));
        assertEquals(3, ws.wordCount("wallace"));
        assertNotEquals(2, ws.wordCount("orange"));

        // wordRank()
        assertEquals(1, ws.wordRank("23"));
        assertEquals(4, ws.wordRank("banana"));
        assertEquals(-1, ws.wordRank("wordNotInFile"));
        assertNotEquals(1, ws.wordRank("twenty-three"));

        // most/leastCommonWords()
        String[] expected = new String[]{"23", "orange", "wallace", "banana"};
        assertEquals(expected, ws.mostCommonWords(4));
        assertEquals(expected, ws.leastCommonWords(4));

        //mostCommonCollocations()
        String[] arr1 = new String[]{"orange", "banana"};
        String[] arr2 = new String[]{"23", "wallace"};
        assertEquals(arr1, ws.mostCommonCollocations(2, "23", true));
        assertEquals(arr2, ws.mostCommonCollocations(2, "23", false));

    }

}
