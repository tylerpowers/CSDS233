package assignment5;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Art a2 = new Art(1, 1, 1, "a", "a");
        Art a1 = new Art(2, 2, 2, "b", "b");
        Art a5 = new Art(3, 3, 3, "c", "c");
        Art a4 = new Art(4, 4, 4, "d", "d");
        Art a3 = new Art(5, 5, 5, "e", "e");
        Art a6 = new Art(6, 6, 6, "f", "f");
        Art a7 = new Art(7, 7, 7, "g", "g");
        Art a11 = new Art(8, 8, 8, "h", "h");
        Art a9 = new Art(9, 9, 9, "i", "i");
        Art a13 = new Art(10, 10, 10, "j", "j");
        Art a8 = new Art(11, 11, 11, "k", "k");
        Art a12 = new Art(12, 12, 12, "l", "l");
        Art a10 = new Art(13, 13, 13, "m", "m");
        Art a14 = new Art(14, 14, 14, "n", "n");


        ArtMuseum m1 = new ArtMuseum("MoCA");
        m1.add(a1);
        m1.add(a2);
        m1.add(a3);
        m1.add(a4);
        m1.add(a5);
        m1.add(a6);
        m1.add(a7);
        m1.add(a8);
        m1.add(a9);
        m1.add(a10);
        m1.add(a11);
        m1.add(a12);
        m1.add(a13);
        m1.add(a14);

        ArrayList<Art> lst = new ArrayList<>();
        lst.add(a1);
        lst.add(a2);
        lst.add(a3);
        lst.add(a4);
        lst.add(a5);
        lst.add(a6);
        lst.add(a7);
        lst.add(a8);
        lst.add(a9);
        lst.add(a10);
        lst.add(a11);
        lst.add(a12);
        lst.add(a13);
        lst.add(a14);

        for (Art a : m1.randomizeArts(5))
            System.out.println(a.getName());

        System.out.println();

        for (Art a : m1.randomSort(lst))
            System.out.println(a.getName());;


    }
}
