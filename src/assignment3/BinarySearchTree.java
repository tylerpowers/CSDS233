package assignment3;

import java.util.ArrayList;

public class BinarySearchTree {

    // the root of the tree
    private Node root;

    // array to be used for KthSmallest
    private ArrayList<Node> inorderArrayNodes = new ArrayList<>();


    /**
     * private method which allows me to recursively insert a value to the tree
     * @param node the current node that we're "on"
     * @param key the value to be inserted
     */
    private void insertRecursive(Node node, int key) {
        if (key < node.getKey()) {
            if (node.getLeft() == null)
                node.setLeft(new Node(key, null, null));
            else
                insertRecursive(node.getLeft(), key);
        }
        else {
            if (node.getRight() == null)
                node.setRight(new Node(key, null, null));
            else
                insertRecursive(node.getRight(), key);
        }
    }


    /**
     * inserts a new value into the tree
     * @param key the value to be inserted
     */
    public void insert(int key) {
        if (root == null) {
            root = new Node(key, null, null);
        }
        else
            insertRecursive(root, key);
    }


    /**
     * creates a tree from the given values
     * @param values an int[] of values to make a tree with
     */
    public void createTree(int[] values) {
        root = new Node(values[0], null, null);
        for (int i = 1; i < values.length; i++)
            insertRecursive(root, values[i]);
    }


    /**
     * private method which allows me to recursively search the tree
     * @param node assignment3.Node to analyze
     * @param key int key to search for
     * @return whether the value was found
     */
    private boolean searchRecursive(Node node, int key) {
        if (node.getKey() == key)
            return true;
        else {
            if (key < node.getKey()) {
                if (node.getLeft() == null)
                    return false;
                else
                    return searchRecursive(node.getLeft(), key);
            }
            else {
                if (node.getRight() == null)
                    return false;
                else
                    return searchRecursive(node.getRight(), key);
            }
        }
    }


    /**
     * searches for a node in the tree
     * @param key int key to be found
     * @return whether the value was found
     */
    public boolean search(int key) {
        return searchRecursive(root, key);
    }


    private Node searchForParentNode(Node node, int key) {
        if (key < node.getKey()) {
            if (node.getLeft() == null)
                return null;
            else if (node.getLeft().getKey() == key)
                return node;
            else
                return searchForParentNode(node.getRight(), key);
        }
        else {
            if (node.getRight() == null)
                return null;
            else if (node.getRight().getKey() == key)
                return node;
            else
                return searchForParentNode(node.getLeft(), key);
        }
    }


    public Node deleteRecursive(Node usefulNode, Node toBeDeleted) {
        if (usefulNode.getLeft() == toBeDeleted) {
            if (toBeDeleted.getLeft() == null && toBeDeleted.getRight() == null) {
                usefulNode.setLeft(null);
                return toBeDeleted;
            } else if (toBeDeleted.getLeft() != null && toBeDeleted.getRight() == null) {
                usefulNode.setLeft(toBeDeleted.getLeft());
                return toBeDeleted;
            } else if (toBeDeleted.getRight() != null && toBeDeleted.getLeft() == null) {
                usefulNode.setLeft(toBeDeleted.getRight());
                return toBeDeleted;
            } else {
                Node replacementParent = toBeDeleted;
                Node replacement = toBeDeleted.getRight();
                while (replacement.getLeft() != null) {
                    replacementParent = replacement;
                    replacement = replacement.getLeft();
                }
                toBeDeleted.setKey(replacement.getKey());
                deleteRecursive(replacementParent, replacement);
            }
        }
        else {
            if (toBeDeleted.getLeft() == null && toBeDeleted.getRight() == null) {
                usefulNode.setRight(null);
                return toBeDeleted;
            }
            else if (toBeDeleted.getLeft() != null && toBeDeleted.getRight() == null) {
                usefulNode.setRight(toBeDeleted.getLeft());
                return toBeDeleted;
            }
            else if (toBeDeleted.getRight() != null && toBeDeleted.getLeft() == null) {
                usefulNode.setRight(toBeDeleted.getRight());
                return toBeDeleted;
            }
            else {
                Node replacementParent = toBeDeleted;
                Node replacement = toBeDeleted.getRight();
                while (replacement.getLeft() != null) {
                    replacementParent = replacement;
                    replacement = replacement.getLeft();
                }
                toBeDeleted.setKey(replacement.getKey());
                deleteRecursive(replacementParent, replacement);
                return toBeDeleted;
            }
        }
        return null;
    }


    public Node delete(int key) {

            Node usefulNode;
            Node toBeDeleted;

            if (root.getKey() == key)
                usefulNode = root;
            else
                usefulNode = searchForParentNode(root, key);

            if (usefulNode == null)
                return null;

            if (usefulNode.getLeft().getKey() == key)
                toBeDeleted = usefulNode.getLeft();
            else
                toBeDeleted = usefulNode.getRight();


            return deleteRecursive(usefulNode, toBeDeleted);
    }



    /**
     * private method which allows me to perform an inorder traversal of a tree
     * @param node assignment3.Node to analyze
     */
    private void inorderRecursive(Node node) {
        if (node.getLeft() != null)
            inorderRecursive(node.getLeft());
        System.out.println(node.getKey());
        if(node.getRight() != null)
            inorderRecursive(node.getRight());
    }


    /**
     * performs an inorder traversal of the tree
     */
    public void inorderRec() {
        inorderRecursive(root);
    }


    /**
     * private method which allows me to perform a preorder traversal of a tree
     * @param node assignment3.Node to analyze
     */
    private void preorderRecursive(Node node) {
        System.out.println(node.getKey());
        if (node.getLeft() != null)
            preorderRecursive(node.getLeft());
        if (node.getRight() != null)
            preorderRecursive(node.getRight());
    }


    /**
     * performs a preorder traversal of a tree
     */
    public void preorderRec() {
        preorderRecursive(root);
    }


    /**
     * private method which allows me to perform a postorder traversal of a tree
     * @param node assignment3.Node to analyze
     */
    private void postorderRecursive(Node node) {
        if (node.getLeft() != null)
            postorderRecursive(node.getLeft());
        if (node.getRight() != null)
            postorderRecursive(node.getRight());
        System.out.println(node.getKey());
    }


    /**
     * performs a postorder traversal of a tree
     */
    public void postorderRec() {
        postorderRecursive(root);
    }


    /**
     * private method which performs an inorder traversal of the BST and appends all values to an ArrayList
     * @param node assignment3.Node to analyze
     */
    private void inorderArray(Node node) {
        if (node.getLeft() != null)
            inorderArray(node.getLeft());
        inorderArrayNodes.add(node);
        if(node.getRight() != null)
            inorderArray(node.getRight());
    }


    /**
     * finds the kth smallest element of the tree
     * @param k an integer k
     * @return a assignment3.Node containing the smallest element
     */
    public Node kthSmallest(int k) {
        inorderArray(root);
        return inorderArrayNodes.get(k - 1);
    }

}
