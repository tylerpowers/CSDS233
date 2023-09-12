package assignment6;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Hashtable;
import java.util.Scanner;

public class WordLadders {

    public static Graph<Integer, String> readWordGraph(String filename) {
        Graph<Integer, String> graph = new Graph<>();
        try {
            File file = new File(filename);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                graph.addNode(Integer.parseInt(data.split("\\s+")[0]), data.split("\\s+")[1]);
            }
            scanner = new Scanner(file);
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

    private static void parseLine(Graph<Integer, String> graph, String data) {
        String[] arr = data.split("\\s+");
        for (int i = 2; i < arr.length; i++) {
            graph.addEdge(Integer.parseInt(arr[0]), Integer.parseInt(arr[i]));
        }
    }

    private static Hashtable<String, Integer> makeTable(String filename) {
        Hashtable<String, Integer> hashtable = new Hashtable<>();
        try {
            File file = new File(filename);
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                hashtable.put(data.split("\\s+")[1], Integer.parseInt(data.split("\\s+")[0]));
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found.");
            e.printStackTrace();
        }
        return hashtable;
    }


    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);

        System.out.println("Enter 1 for 3 letter words, enter 2 for all.");

        String filename = in.nextLine().equals("2") ? "src\\LargeWordGraph.txt" : "src\\Length3WordGraph.txt";

        Graph<Integer, String> graph = readWordGraph(filename);
        Hashtable<String, Integer> hashtable = makeTable(filename);


        do {
            System.out.print("Enter first word: ");
            String firstWord = in.nextLine();
            System.out.print("Enter second word: ");
            String secondWord = in.nextLine();
            System.out.println("Your Ladder:");

            for (Integer i : graph.BFS(hashtable.get(firstWord), hashtable.get(secondWord))) {
                System.out.println(graph.get(i));
            }

            System.out.print("Again? (y/n) ");
        } while (in.nextLine().equalsIgnoreCase("y"));

        System.out.println("Thank You!");
    }


}
