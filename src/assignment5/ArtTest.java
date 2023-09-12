package assignment5;

import org.junit.Test;
import static org.junit.Assert.*;


/**
 * testing class for assignment5.Art
 * @author rtp32
 */
public class ArtTest {

    /**
     * tests all methods & constructor of assignment5.Art class
     */
    @Test
    public void testGettersAndSetters() {

        Art a1 = new Art(250, 1000, 300, "Mona Lisa", "Leonardo da Vinci");

        assertEquals(250, a1.getHeight());  // test getHeight() from constructor

        a1.setPrice(2);  // test setPrice()
        assertNotEquals(250, a1.getPrice());  // test getPrice() from setter
        assertEquals(2, a1.getPrice());

        assertEquals(300, a1.getWidth());  // test getWidth() from constructor

        a1.setName("A Bag of Sand");  // test setName()
        assertNotEquals("Mona Lisa", a1.getName());  // test getName() from setter
        assertEquals("A Bag of Sand", a1.getName());

        assertEquals("Leonardo da Vinci", a1.getArtistName());  // test getArtistName() from constructor


        Art a2 = new Art(5000, 26, 123, "Cleveland in the Rain", "Harold C.");

        a2.setHeight(500);  // test setHeight()
        assertNotEquals(5000, a2.getHeight());
        assertEquals(500, a2.getHeight());  // test getHeight() from setter

        assertEquals(26, a2.getPrice());  // test getPrice() from constructor

        a2.setWidth(400);  // test setWidth()
        assertNotEquals(123, a2.getWidth());  // test getWidth() from setter
        assertEquals(400, a2.getWidth());

        assertEquals("Cleveland in the Rain", a2.getName());  // test getName() from constructor

        a2.setArtistName("Chris B.");  // test setArtistName()
        assertNotEquals("Harold C.", a2.getArtistName());  // test setArtistName() from setter
        assertEquals("Chris B.", a2.getArtistName());

    }

}
