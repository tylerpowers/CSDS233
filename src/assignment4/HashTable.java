package assignment4;

import java.util.ArrayList;
import java.util.NoSuchElementException;

/**
 * Implementation of a hash table using an ArrayList and separate chaining.
 * @param <T> the type of the elements in the hash table
 * @author rtp32
 */
public class HashTable<T> {

    /**
     * Node class for the hash table
     */
    private class Node {

        // instance variables
        private final String key;
        private T element;
        private Node next;

        /**
         * private constructor for Node class
         * @param key the key for the node
         * @param element the element for the node
         */
        private Node(String key, T element) {
            this.key = key;
            this.element = element;
        }
    }


    // the list that works as the hash table
    private ArrayList<Node> nodeList;

    // the hash factor, number of spots in the list
    private int hashFactor;

    // amount of items in the hash table
    private int size;


    /**
     * default constructor for HashTable, creates a hash table with 25 spots
     */
    public HashTable() {
        nodeList = new ArrayList<>(25);
        hashFactor = 25;
    }


    /**
     * constructor for hash table, creates a hash table with the given capacity
     * @param capacity the capacity of the hash table
     */
    public HashTable(int capacity) {
        if (capacity < 1)  // capacity should not be 0 or negative
            throw new IllegalArgumentException();

        nodeList = new ArrayList<>(capacity);
        hashFactor = capacity;
    }


    /**
     * finds a key in the hash table and returns its element
     * @param key the key to be found
     * @return the element belonging to that key
     */
    public T get(String key) {

        if (nodeList.size() == 0)  // size should be greater than 0
            throw new NoSuchElementException();

        int hash = Math.abs(key.hashCode()) % hashFactor;
        if (nodeList.get(hash) == null)  // if there is no element
            throw new NoSuchElementException();

        Node ptr = nodeList.get(hash);

        do {  // finds element in chain
            if (ptr.key.equals(key))
                return ptr.element;
            ptr = ptr.next;
        } while (ptr != null);

        throw new NoSuchElementException();  // default case
    }


    /**
     * puts an element in the hashtable
     * @param key the key to be stored
     * @param value the value corresponding with the key
     */
    public void put(String key, T value) {

        if (nodeList.size() == 0) {  // initializes the ArrayList, filling it with null values
            for (int i = 0; i < hashFactor; i++)
                nodeList.add(i, null);
        }

        Node node = new Node(key, value);
        int hash = Math.abs(key.hashCode()) % hashFactor;

        if (nodeList.get(hash) == null)  // if there is an open space
            nodeList.set(hash, node);
        else {
            Node ptr = nodeList.get(hash);
            Node prev = null;
            do {

                if (ptr.key.equals(key)) {  // override/addition thing
                    if (ptr.element instanceof Integer && value instanceof Integer)
                        ptr.element = (T) new Integer((Integer) ptr.element + (Integer) value);
                    else
                        ptr.element = value;

                    return; // to not increase size;
                }

                prev = ptr;
                ptr = ptr.next;

            } while (ptr != null);

            prev.next = node;
        }

        size++;
        if (size >= hashFactor)  // assures good distribution + not too much chaining
            rehash();

    }


    /**
     * removes an element from the hash table
     * @param key the key of the element to be removed
     * @return the element removed
     */
    public T remove(String key) {

        if (nodeList.size() == 0)  // can't remove if there's no list
            throw new NoSuchElementException();

        int hash = Math.abs(key.hashCode()) % hashFactor;
        if (nodeList.get(hash) == null)  // there's nothing there
            throw new NoSuchElementException();

        Node ptr = nodeList.get(hash);
        Node prev = null;
        while (ptr != null) {  // locates & deletes in chain
            if (ptr.key.equals(key)) {
                if (prev == null) {
                    nodeList.set(hash, ptr.next);
                } else {
                    prev.next = ptr.next;
                }
                size--;
                return ptr.element;
            }
            prev = ptr;
            ptr = ptr.next;
        }

        throw new NoSuchElementException();

    }


    /**
     * the size of the hash table
     * @return the size of the hash table
     */
    public int size() {
        return size;
    }


    /**
     * protected method for creating a priorityQueue out of the hashtable
     * @return an array of all strings in the table
     */
    protected String[] getStringList() {
        String[] raw = new String[size];
        int i = 0;
        for (Node node : nodeList) {
            if (node != null) {
                raw[i] = node.key;
                i++;
            }
        }
        return raw;
    }


    /**
     * protected method for creating a priorityQueue out of the hashtable
     * @return an array of all values in table
     */
    protected T[] getValueList() {
        Integer[] raw = new Integer[size];
        int i = 0;
        for (Node node : nodeList) {
            if (node != null && node.element instanceof Integer) {
                raw[i] = (Integer) node.element;
                i++;
            }
        }
        return (T[])raw;
    }


    /**
     * private rehash function that is invoked when load factor >= 1
     */
    private void rehash() {
        hashFactor *= 2;
        ArrayList<Node> oldList = nodeList;
        nodeList = new ArrayList<>(hashFactor);
        Node ptr;
        for (Node node : oldList) {
            ptr = node;
            while (ptr != null) {
                put(ptr.key, ptr.element);
                ptr = ptr.next;
            }
        }
    }

}
