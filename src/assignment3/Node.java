package assignment3;

public class Node {

    // the key; what the node stores
    private int key;

    // the next node to the left
    private Node left;

    // the next node to the right
    private Node right;


    /**
     * Class constructor for assignment3.Node
     * @param key integer to be stored
     * @param left next node to the left
     * @param right next node to the right
     */
    public Node(int key, Node left, Node right) {
        this.key = key;
        this.left = left;
        this.right = right;
    }


    public int getKey() {
        return key;
    }

    public void setKey(int key) {
        this.key = key;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }
}
