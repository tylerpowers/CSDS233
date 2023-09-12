package assignment4;

import java.lang.reflect.Array;
import java.util.*;

/**
 * priorityQueue implemented using a maxHeap stored in an array list
 * @param <K> a comparable type acting as the key
 * @param <V> the values stored
 * @author rtp32
 */
public class PriorityQueue<K extends Comparable<? super K>, V> {

    // the queue itself
    private final ArrayList<Entry<K, V>> heap;

    // the size of the queue
    private int size;


    /**
     * private class containing entries into the queue
     * @param <K> outer generic K
     * @param <V> outer generic V
     */
    private static class Entry<K, V> {
        private K key;
        private V value;
        private Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }


    /**
     * class constructor for an empty queue
     */
    public PriorityQueue() {
        heap = new ArrayList<>();
        size = 0;
    }


    /**
     * class constructor for predetermined values
     * @param keys array of keys
     * @param values array of values
     */
    public PriorityQueue(K[] keys, V[] values) {
        if (keys.length != values.length)
            throw new IllegalArgumentException();

        heap = new ArrayList<>(keys.length);
        size = 0;

        for (int i = 0; i < keys.length; i++) {
            add(keys[i], values[i]);
        }
    }


    /**
     * adds a key/value pair to the queue
     * @param key comparable key to be stored
     * @param value value corresponding with key
     */
    public void add(K key, V value) {
        Entry<K, V> entry = new Entry<>(key, value);
        heap.add(entry);
        size++;
        upHeap(size - 1);
    }


    /**
     * updates a key/value pair and re-sorts queue
     * @param key comparable key to find
     * @param value value to replace old value
     */
    public void update(K key, V value) {
        int i = 0;
        while (!heap.get(i).key.equals(key))
            i++;
        heap.remove(i);
        heap.add(i, new Entry<>(key, value));

        if (key.compareTo(heap.get(parent(i)).key) >= 0)
            upHeap(i);
        else
            downHeap(i);
    }


    /**
     * "peeks" at the top/greatest node
     * @return the value corresponding to the greatest key
     */
    public V peek() {
        if (size == 0)
            throw new NoSuchElementException();
        return heap.get(0).value;
    }


    /**
     * "peeks" at the top k elements of the queue
     * @param k number of elements to be peeked at
     * @return an array of length k with the greatest elements in the queue
     */
    public V[] peek(int k) {
        if (k < 0 || k > size)
            throw new IllegalArgumentException();

        Comparable[] copyKeys = new Comparable[size];
        V[] copyValues = (V[]) Array.newInstance(heap.get(0).value.getClass(), size);

        for (int i = 0; i < size; i++) {
            copyKeys[i] = heap.get(i).key;
            copyValues[i] = heap.get(i).value;
        }

        PriorityQueue<K, V> copy = new PriorityQueue<>((K[])copyKeys, copyValues);

        V[] firstKValues = (V[]) Array.newInstance(heap.get(0).value.getClass(), size);

        for (int i = 0; i < k + 1; i++) {
            firstKValues[i] = copy.poll();
        }

        return firstKValues;

    }


    /**
     * removes the top/maximum element of the queue
     * @return the element removed
     */
    public V poll() {
        if (size == 0)
            throw new NoSuchElementException("Priority queue is empty");

        Entry<K, V> save = heap.get(0);

        heap.set(0, heap.get(size - 1));
        heap.remove(size - 1);
        size--;
        downHeap(0);
        return save.value;

    }


    /**
     * removes a given element
     * @param value the value whose element will be removed
     * @return the key of the element removed
     */
    public K poll(V value) {
        if (size == 0)
            throw new NoSuchElementException("Priority queue is empty");

        int i = 0;
        while (!heap.get(i).value.equals(value)) {
            i++;
            if (i > size)
                return null;
        }

        Entry<K, V> save = heap.get(i);
        heap.set(i, heap.get(size - 1));
        heap.remove(size - 1);
        size--;
        downHeap(i);
        return save.key;
    }


    /**
     * returns the size of the heap
     * @return size of the heap
     */
    public int size() {
        return size;
    }


    /**
     * protected method which returns all the nodes with the top key
     * @return a V[]
     */
    protected V[] returnAllOfTopKey() {
        if (size <= 0)
            return null;

        K topKey = heap.get(0).key;
        int x = 0;
        ArrayList<V> valuesRaw = new ArrayList<>();
        for (Entry<K, V> kvEntry : heap) {
            if (kvEntry.key.equals(topKey)) {
                valuesRaw.add(kvEntry.value);
                x++;
            }
        }
        V[] values = (V[]) Array.newInstance(heap.get(0).value.getClass(), x);
        for (int i = 0; i < valuesRaw.size(); i++) {
            if (valuesRaw.get(i) != null)
                values[i] = valuesRaw.get(i);
        }
        return values;
    }


    /**
     * private method which performs an "up-heap" or "percolate-up" operation
     * @param j index
     */
    private void upHeap(int j) {
        if (j < 1)
            return;
        int p = parent(j);
        if (heap.get(j).key.compareTo(heap.get(p).key) <= 0)
            return; // this handles the break from the while loop
        swap(j, p);
        upHeap(p); // the recursive method call, replacing j with p
    }


    /**
     * private method which performs a "down-heap" or "percolate-down" operation
     * @param j index
     */
    private void downHeap(int j) {
        if (j < size) {
            int left = 2 * (j + 1) - 1;
            int right = 2 * (j + 1);

            if (right < size && heap.get(right).key.compareTo(heap.get(j).key) >= 0 &&
                    heap.get(right).key.compareTo(heap.get(left).key) >= 0) {
                swap(right, j);
                downHeap(right);
            }
            else if (left < size && heap.get(left).key.compareTo(heap.get(j).key) >= 0) {
                swap(left, j);
                downHeap(left);
            }


        }
    }


    /**
     * private method that returns the parent of an index
     * @param i index
     * @return parent index
     */
    private int parent(int i) {
        return (i - 1)/2;
    }


    /**
     * private method which swaps two nodes
     * @param j index node 1
     * @param p index node 2
     */
    private void swap(int j, int p) {
        Entry<K, V> save = heap.get(j);
        heap.remove(j);
        heap.add(j, heap.get(p));
        heap.remove(p);
        heap.add(p, save);
    }

}
