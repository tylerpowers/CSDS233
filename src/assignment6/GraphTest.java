package assignment6;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;

public class GraphTest {

    private Graph<String, Integer> graph;

    @Before
    public void setUp() {
        graph = new Graph<>();
    }

    @Test
    public void testAddNode() {
        assertTrue(graph.addNode("A", 1));
        assertFalse(graph.addNode("A", 2)); // duplicate
    }

    @Test
    public void testAddNodes() {
        String[] names = {"A", "B", "C"};
        Integer[] data = {1, 2, 3};

        assertTrue(graph.addNodes(names, data));
        assertFalse(graph.addNodes(names, data)); // duplicate
    }

    @Test
    public void testAddEdge() {
        graph.addNode("A", 1);
        graph.addNode("B", 2);

        assertTrue(graph.addEdge("A", "B"));
        assertFalse(graph.addEdge("A", "B")); // duplicate
    }

    @Test
    public void testAddEdges() {
        graph.addNode("A", 1);
        graph.addNode("B", 2);
        graph.addNode("C", 3);

        assertTrue(graph.addEdges("A", "B", "C"));
        assertFalse(graph.addEdges("A", "B", "C")); // duplicate
    }

    @Test
    public void testRemoveNode() {
        graph.addNode("A", 1);

        assertTrue(graph.removeNode("A"));
        assertFalse(graph.removeNode("A")); // already removed
    }

    @Test
    public void testRemoveNodes() {
        graph.addNodes(new String[]{"A", "B", "C"}, new Integer[]{1, 2, 3});

        assertTrue(graph.removeNodes("A", "B"));
        assertFalse(graph.removeNodes("A", "B")); // already removed
    }

    @Test
    public void testPrintGraph() {
        graph.addNode("A", 1);
        graph.addNode("B", 2);
        graph.addNode("C", 3);
        graph.addEdge("A", "B");
        graph.addEdge("A", "C");
        graph.addEdge("B", "C");

        System.out.println("printGraph() test:");
        graph.printGraph();
        // expected output: A -> B, C
        //                  B -> A, C
        //                  C -> A, B
    }

    @Test
    public void testReadBFSDFS() {
        System.out.println("\nread() test:");
        Graph.read("src\\example.txt").printGraph();
        assertEquals(new String[]{"D", "A", "C"}, Graph.read("src\\example.txt").BFS("D", "C"));
        assertEquals(new String[]{"D", "A", "C"}, Graph.read("src\\example.txt").DFS("D", "C"));
    }
}
