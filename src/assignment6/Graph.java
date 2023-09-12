package assignment6;

import com.sun.deploy.security.SelectableSecurityManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.lang.reflect.Array;
import java.util.*;

public class Graph<K, V> {

    private class Node {
        private K key;
        private V value;
        private boolean visited = false;
        private final LinkedList<Node> adjacencyList = new LinkedList<>();
        private Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private final LinkedList<Node> nodeList = new LinkedList<>();

    private int size = 0;


    /**
     * adds a single node with no connections to the graph
     * @param name key
     * @param data value
     * @return true if the node was inserted, false if it was a duplicate
     */
    public boolean addNode(K name, V data) {
        for (Node node : nodeList) {  // assure it is not a duplicate
            if (node.key.equals(name))
                return false;
        }
        nodeList.add(new Node(name, data));
        size++;
        return true;
    }


    /**
     * adds an array of nodes to the graph
     * @param names array of node keys
     * @param data array of node values
     * @return true if at least one node was added to the graph, false otherwise
     */
    public boolean addNodes(K[] names, V[] data) {
        if (names.length != data.length)
            throw new IllegalArgumentException("input arrays must be same length.");
        boolean add;
        int nodesAdded = 0;
        for (int i = 0; i < names.length; i++) {
            add = true;
            for (Node node : nodeList) {
                if (node.key.equals(names[i])) {  // check for duplicates
                    add = false;
                    break;
                }
            }
            if (add) {
                nodeList.add(new Node(names[i], data[i]));
                nodesAdded++;
                size++;
            }
        }
        return nodesAdded != 0;
    }


    /**
     * adds an undirected edge from one node to another (order does not matter)
     * @param from one node
     * @param to another node
     * @return true if the edge was successfully added
     */
    public boolean addEdge(K from, K to) {
        Node n1 = null;
        Node n2 = null;
        for (Node node: nodeList) {  // make sure both nodes are in list
            if (node.key.equals(from))
                n1 = node;
            else if (node.key.equals(to))
                n2 = node;
        }
        if (n1 == null || n2 == null)
            return false;


        // connect the nodes
        if (checkEdgeDuplicates(n1, n2)) {
            n1.adjacencyList.add(n2);
            n2.adjacencyList.add(n1);
            return true;
        }
        return false;
    }


    /**
     * adds a list of edges from one node to any amount of others
     * @param from a node
     * @param toList other nodes
     * @return true if at least one edge was successfully added
     */
    public boolean addEdges(K from, K... toList) {
        Node fromNode = null;
        for (Node node: nodeList) {
            if (node.key.equals(from))
                fromNode = node;
        }
        if (fromNode == null)
            return false;

        int edgesDrawn = 0;
        for (K key : toList) {
            for (Node node : nodeList) {
                if (node.key.equals(key)) {
                    if (checkEdgeDuplicates(node, fromNode)) {
                        node.adjacencyList.add(fromNode);
                        fromNode.adjacencyList.add(node);
                        edgesDrawn++;
                    }
                }
            }
        }

        return edgesDrawn != 0;
    }


    /**
     * removes a node from the graph
     * @param name key of node
     * @return true if there was a node removed
     */
    public boolean removeNode(K name) {
        Node remove = null;
        for (Node node : nodeList) {
            if (node.key == name)
                remove = node;
        }
        if (remove == null)
            return false;

        nodeList.remove(remove); // remove node

        for (Node node : nodeList) {  // remove from all adjacency lists
            for (Node connection: node.adjacencyList) {
                if (connection == remove)
                    connection.adjacencyList.remove(connection);
            }
        }

        return true;
    }

    /**
     * removes a list of nodes from the graph
     * @param nodelist a list of nodes
     * @return true if at least one node was removed
     */
    public boolean removeNodes(K... nodelist) {
        int nodesRemoved = 0;
        for (K key : nodelist) {
            if (removeNode(key))
                nodesRemoved++;
        }
        return nodesRemoved != 0;
    }


    /**
     * prints all nodes of graph in given format
     */
    public void printGraph() {
        for (Node node : nodeList) {
            System.out.print(node.key.toString() + " ");
            for (Node adjacent : node.adjacencyList)
                System.out.print(adjacent.key.toString() + " ");
            System.out.println();
        }
    }


    /**
     * constructs a graph from a .txt file that has the same format as the printGraph() method
     * @param filename .txt file name
     * @return a Graph
     * @param <V> Generic type
     */
    public static <V> Graph<String, V> read(String filename) {
        Graph<String, V> graph = new Graph<>();
        try {
            File file = new File(filename);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                parseLine(graph, data);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            e.printStackTrace();
        }
        return graph;
    }


    /**
     * performs a breadth first search on the graph
     * @param from beginning node
     * @param to ending node
     * @return array of nodes in traversal
     */
    public K[] BFS(K from, K to) {
        Node beginningNode = null;
        Node endingNode = null;
        for (Node node: nodeList) {  // locate nodes
            if (node.key.equals(from))
                beginningNode = node;
            else if (node.key.equals(to))
                endingNode = node;
        }
        if (beginningNode == null || endingNode == null)
            throw new IllegalArgumentException("One or both nodes is not in graph.");

        List<Node> path = breadthFirst(beginningNode, endingNode);

        // Convert List<Node> to K[]
        K[] result = (K[]) Array.newInstance(beginningNode.key.getClass(), path.size());
        for (int i = 0; i < result.length; i++) {
            result[i] = path.get(i).key;
        }

        for (Node node : nodeList)  // un-visit the nodes :]
            node.visited = false;

        return result;
    }


    public K[] DFS(K from, K to) {
        Node beginningNode = null;
        Node endingNode = null;
        for (Node node: nodeList) {  // locate nodes
            if (node.key.equals(from))
                beginningNode = node;
            else if (node.key.equals(to))
                endingNode = node;
        }
        if (beginningNode == null || endingNode == null)
            throw new IllegalArgumentException("One or both nodes is not in graph.");

        List<Node> path = depthFirst(beginningNode, endingNode);

        // Convert List<Node> to K[]
        K[] result = (K[]) Array.newInstance(beginningNode.key.getClass(), path.size());
        for (int i = 0; i < result.length; i++) {
            result[i] = path.get(i).key;
        }

        for (Node node : nodeList)  // un-visit the nodes :]
            node.visited = false;

        return result;
    }


    protected V get(K key) {
        for (Node node : nodeList) {
            if (node.key == key)
                return node.value;
        }
        return null;
    }

    private static void parseLine(Graph<String, ?> graph, String data) {
        String[] arr = data.split("\\s+");
        for (String value : arr)
            graph.addNode(value, null);
        for (int i = 1; i < arr.length; i++) {
            graph.addEdge(arr[0], arr[i]);
        }
    }

    private boolean checkEdgeDuplicates (Node n1, Node n2) {
        for (Node node : n1.adjacencyList) {  // check for duplicates
            if (node == n2)
                return false;
        }
        for (Node node : n2.adjacencyList) {
            if (node == n1)
                return false;
        }
        return true;
    }

    private List<Node> breadthFirst(Node start, Node target) {
        LinkedList<List<Node>> queue = new LinkedList<>();
        List<Node> path = new ArrayList<>();
        path.add(start);
        queue.add(path);

        while (!queue.isEmpty()) {
            List<Node> currentPath = queue.removeFirst();
            Node current = currentPath.get(currentPath.size() - 1);
            if (current == target) {
                return currentPath;
            }

            for (Node neighbor : current.adjacencyList) {
                if (!currentPath.contains(neighbor)) {
                    List<Node> newPath = new ArrayList<>(currentPath);
                    newPath.add(neighbor);
                    queue.add(newPath);
                }
            }
        }

        throw new IllegalArgumentException("No path found between " + start.key + " and " + target.key);

    }


    private List<Node> depthFirst(Node start, Node target) {
        LinkedList<List<Node>> stack = new LinkedList<>();
        List<Node> path = new ArrayList<>();
        path.add(start);
        stack.add(path);

        while (!stack.isEmpty()) {
            List<Node> currentPath = stack.removeLast();
            Node current = currentPath.get(currentPath.size() - 1);
            if (current == target) {
                return currentPath;
            }

            for (Node neighbor : current.adjacencyList) {
                if (!currentPath.contains(neighbor)) {
                    List<Node> newPath = new ArrayList<>(currentPath);
                    newPath.add(neighbor);
                    stack.add(newPath);
                }
            }
        }

        throw new IllegalArgumentException("No path found between " + start.key + " and " + target.key);

    }

}
