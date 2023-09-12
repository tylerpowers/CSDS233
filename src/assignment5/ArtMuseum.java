package assignment5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class ArtMuseum {

    // name of the art museum
    private final String museumName;

    // ArrayList storing all assignment5.Art objects
    private final ArrayList<Art> arr = new ArrayList<>();


    /**
     * class constructor
     * @param museumName a String containing the name of the museum
     */
    public ArtMuseum(String museumName) {
        this.museumName = museumName;
    }


    /**
     * adds an assignment5.Art object to the database
     * @param art object to be added
     * @return true if operation succeeds
     */
    public boolean add(Art art) {
        arr.add(art);
        return true;
    }


    /**
     * sorts by a given attribute
     * @param attribute a String (must be an attribute of assignment5.Art)
     * @param direction an int, ascending order if >=0, descending order if <0.
     * @return sorted list
     */
    public List<Art> sort(String attribute, int direction) {
        quickSort(0, arr.size() - 1, getAttribute(attribute), direction, arr);
        return arr;
    }


    /**
     * returns all assignment5.Art objects created by a given artist
     * @param artistName name of desired artist
     * @return list of all works
     */
    public List<Art> findArts(String artistName) {
        LinkedList<Art> found = new LinkedList<>();

        for (Art art : arr) {
            if (art.getArtistName().equals(artistName))
                found.add(art);
        }

        return found;
    }


    /**
     * returns a list of n assignment5.Art objects in the database
     * @param n size of desired list
     * @return list of random objects
     */
    public List<Art> randomizeArts(int n) {
        if (n > arr.size())
            return null;
        LinkedList<Art> random = new LinkedList<>();
        Collections.shuffle(arr);
        for (int i = 0; i < n; i++)
            random.add(arr.get(i));
        return random;
    }


    /**
     * returns a list where the first 1/5 of elements in arts are sorted by height,
     * the next 1/5 are sorted by price, the next 1/5 are sorted by width,
     * the next 1/5 are sorted by name, and remaining sorted based on ArtistName.
     * no instruction was given on order, so all returned lists are sorted in ascending order.
     * @param arts list to RandomSort
     * @return the RandomSorted list
     */
    public List<Art> randomSort(List<Art> arts) {
        int factor = arts.size() / 5;
        quickSort(0, factor - 1, 1, 1, arts);
        quickSort(factor, factor * 2 - 1, 2, 1, arts);
        quickSort(factor * 2, factor * 3 - 1, 3, 1, arts);
        quickSort(factor * 3, factor * 4 - 1, 4, 1, arts);
        quickSort(factor * 4, arr.size() - 1, 5, 1, arts);

        return arts;
    }


    /**
     * returns an assignment5.Art object in the ArrayList, used for testing
     * @param index index in assignment5.ArtMuseum Arraylist
     * @return the assignment5.Art object
     */
    protected Art getArt(int index) {
        return arr.get(index);
    }


    /**
     * quickSort method that does the bulk of the work for sort()
     * @param first first index of arr
     * @param last last index of arr
     * @param attribute attribute to be sorted by
     * @param direction ascending or descending
     * @param arr the ArrayList
     */
    private void quickSort(int first, int last, int attribute, int direction, List<Art> arr) {
        if (first >= last)
            return;

        boolean forwards = direction >= 0;

        int split = partition(first, last, attribute, forwards, arr);
        quickSort(first, split - 1, attribute, direction, arr);
        quickSort(split + 1, last, attribute, direction, arr);

    }


    /**
     * partition helper method for quicksort
     * @param begin first from quickSort()
     * @param end last from quickSort()
     * @param attribute attribute to be sorted by
     * @param direction ascending or descending
     * @param arr the ArrayList
     * @return an int
     */
    private int partition(int begin, int end, int attribute, boolean direction,  List<Art> arr) {
        int pivot = end;
        int i = (begin - 1);

        for (int j = begin; j < end; j++) {
            if (compareArt(j, pivot, attribute, arr) != direction){
                i++;
                swap(i, j, arr);
            }
        }

        swap(i + 1, end, arr);

        return i + 1;
    }


    /**
     * compares two assignment5.Art objects based on the given attribute
     * @param i index of art 1
     * @param j index of art 2
     * @param attribute attribute to compare
     * @param arr the ArrayList
     * @return a boolean
     */
    private boolean compareArt(int i, int j, int attribute, List<Art> arr) {
        if (attribute == 1)
            return arr.get(i).getHeight() >= arr.get(j).getHeight();
        else if (attribute == 2)
            return arr.get(i).getPrice() >= arr.get(j).getPrice();
        else if (attribute == 3)
            return arr.get(i).getWidth() >= arr.get(j).getWidth();
        else if (attribute == 4)
            return arr.get(i).getName().compareTo(arr.get(j).getName()) >= 0;
        else if (attribute == 5)
            return arr.get(i).getArtistName().compareTo(arr.get(j).getArtistName()) >= 0;
        else
            return false;
    }


    /**
     * swaps two assignment5.Art objects
     * @param i index 1
     * @param j index 2
     * @param arr the ArrayList
     */
    private void swap(int i, int j, List<Art> arr) {
        Art save = arr.get(i);
        arr.set(i, arr.get(j));
        arr.set(j, save);
    }


    /**
     * assigns an int to each attribute
     * @param strAttribute the attribute
     * @return an int
     */
    private int getAttribute(String strAttribute) {
        switch (strAttribute) {
            case "height": return 1;
            case "price": return 2;
            case "width": return 3;
            case "name": return 4;
            case "artistName": return 5;
            default: return 0;
        }
    }


    /**
     * prints the whole museum
     */
    private void printArr() {
        for (Art art : arr) {
            System.out.println(art.getName());
        }
    }


}
