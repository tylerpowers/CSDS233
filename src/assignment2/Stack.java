package assignment2;

/**
 * Implementation of the assignment2.Stack data structure
 * @param <T> Any type which extends Comparable
 * @author rtp32
 */
public class Stack<T extends Comparable<? super T>> {

    /**
     * private class for assignment3.Node object
     * @param <T> Any type that extends Comparable
     */
    private static class Node<T> {
        private T element;
        private Node<T> next;
        private Node(T element) {
            this.element = element;
        }
    }

    // top of the stack
    private Node<T> front;


    /**
     * pushes a value on top of the stack
     * @param value value to be pushed
     * @return returns whether the operation succeeded
     */
    public boolean push(T value) {
        Node<T> node = new Node<T>(value);
        node.next = front;
        front = node;
        return true;
    }


    /**
     * pops the first node out of the stack
     * @return the first value of the stack
     */
    public T pop() {
        T save;
        if (front != null) {
            save = front.element;
            front = front.next;
            return save;
        }
        return null;
    }


    /**
     * peeks at the top node without removing it
     * @return the first value of the stack
     */
    public T peek() {
        if (front != null)
            return front.element;
        return null;
    }
}
