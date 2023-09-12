package assignment4;

import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        CMDbProfile c1 = new CMDbProfile("rtp32");
        CMDbProfile c2 = new CMDbProfile("powersiv");
        CMDbProfile c3 = new CMDbProfile("tylerpowers");

        c1.rate("EEAAO", 10);
        c2.rate("Top Gun", 6);
        c2.rate("The Hangover", 6);
        c2.rate("Wedding Crashers", 4);
        c3.rate("El Camino", 10);

        System.out.println(Arrays.toString(c1.favorite()));
        System.out.println(Arrays.toString(c2.favorite()));
        System.out.println(c3.profileInformation());

        c2.removeRating("The Hangover");
        c2.changeRating("Wedding Crashers", 9);

        System.out.println(Arrays.toString(c2.favorite(2)));

        c3.changeUserName("john-humphrey82");

        System.out.println(c3.profileInformation());



        CMDbGroup g = new CMDbGroup();
        g.addMember(c1);
        System.out.println(Arrays.toString(g.groupFavorites()));
        g.addMember(new CMDbProfile[]{c2, c3});
        System.out.println(Arrays.toString(g.group()));
        System.out.println(Arrays.toString(g.groupFavorites()));
        System.out.println(CMDbGroup.favorite("rtp32"));

    }






}
