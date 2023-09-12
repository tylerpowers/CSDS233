package assignment5;

import org.junit.Test;

import static org.junit.Assert.*;

public class ArtMuseumTest {

    @Test
    public void testSort() {

        // test ascending, name
        Art a2 = new Art(1, 1, 1, "a", "a");
        Art a1 = new Art(2, 2, 2, "b", "b");
        Art a5 = new Art(3, 3, 3, "c", "c");
        Art a4 = new Art(4, 4, 4, "d", "d");
        Art a3 = new Art(5, 5, 5, "e", "e");
        Art a6 = new Art(6, 6, 6, "f", "f");

        ArtMuseum m1 = new ArtMuseum("MoCA");
        m1.add(a1);
        m1.add(a2);
        m1.add(a3);
        m1.add(a4);
        m1.add(a5);
        m1.add(a6);

        m1.sort("name", 5);

        assertEquals("a", m1.getArt(0).getName());
        assertEquals("b", m1.getArt(1).getName());
        assertEquals("c", m1.getArt(2).getName());
        assertEquals("d", m1.getArt(3).getName());
        assertEquals("e", m1.getArt(4).getName());
        assertEquals("f", m1.getArt(5).getName());


        // test descending, artist name

        m1.sort("artistName", -5);

        assertEquals("f", m1.getArt(0).getName());
        assertEquals("e", m1.getArt(1).getName());
        assertEquals("d", m1.getArt(2).getName());
        assertEquals("c", m1.getArt(3).getName());
        assertEquals("b", m1.getArt(4).getName());
        assertEquals("a", m1.getArt(5).getName());

        // test width
        m1.sort("width", 32);
        assertEquals(1, m1.getArt(0).getWidth());
        assertEquals(2, m1.getArt(1).getWidth());
        assertEquals(3, m1.getArt(2).getWidth());
        assertEquals(4, m1.getArt(3).getWidth());
        assertEquals(5, m1.getArt(4).getWidth());
        assertEquals(6, m1.getArt(5).getWidth());

    }

    @Test
    public void testFindArts() {
        Art a2 = new Art(1, 1, 1, "a", "a");
        Art a1 = new Art(2, 2, 2, "b", "a");
        Art a5 = new Art(3, 3, 3, "c", "b");
        Art a4 = new Art(4, 4, 4, "d", "b");
        Art a3 = new Art(5, 5, 5, "e", "b");
        Art a6 = new Art(6, 6, 6, "f", "b");

        ArtMuseum m1 = new ArtMuseum("MoCA");
        m1.add(a1);
        m1.add(a2);
        m1.add(a3);
        m1.add(a4);
        m1.add(a5);
        m1.add(a6);

        assertEquals(2, m1.findArts("a").size());
        assertEquals(4, m1.findArts("b").size());

    }

    // randomSort and randomizeArts were tested manually due to their finicky nature.

}
