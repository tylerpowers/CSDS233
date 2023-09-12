package assignment4;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * testing class for Tokenizer.java, assignment 4
 * @author rtp32
 */
public class TokenizerTest {

    @Test
    public void testTokenizers() {
        Tokenizer t1 = new Tokenizer("src\\tokenizer_test.txt");
        Tokenizer t2 = new Tokenizer(new String[] {"This?? is a te**st,", "of the emergency broadcast system a1234"});

        // both should be: {"this", "is", "a", "test", "of", "the", "c34", "emergency", "broadcast", "system"}

        assertEquals(t1.wordList(), t2.wordList());

    }

}
