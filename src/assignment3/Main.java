package assignment3;

public class Main {
    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();

        // Test insert method
        bst.insert(4);
        bst.insert(2);
        bst.insert(6);
        bst.insert(3);
        bst.insert(8);
        System.out.println("Tree created using insert(): ");
        bst.inorderRec();

        // Test createTree method
        int[] values = { 5, 3, 7, 1, 9 };
        System.out.println("Tree created using createTree(): ");
        bst.createTree(values);
        bst.inorderRec();

        // Test search method
        boolean isFound = bst.search(7);
        System.out.println("Is 7 in BST? " + isFound);

        // Test delete method
        Node deletedNode = bst.delete(7);
        System.out.println("Deleted node: " + deletedNode.getKey());

        // Test inorderRec method
        System.out.println("Inorder traversal:");
        bst.inorderRec();

        // Test preorderRec method
        System.out.println("Preorder traversal:");
        bst.preorderRec();

        // Test postorderRec method
        System.out.println("Postorder traversal:");
        bst.postorderRec();

        // Test kthSmallest method
        int k = 3;
        Node kthSmallestNode = bst.kthSmallest(k);
        System.out.println("Kth smallest element with k = " + k + ": " + kthSmallestNode.getKey());


    }
}
