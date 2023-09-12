package assignment4;

import java.util.ArrayList;
import java.util.Objects;

/**
 * a class which shows statistics of different .txt files or String[]'s
 * implemented using my implementation of a hashtable and a priority queue
 * @author rtp32
 */
public class WordStat {

    // the hashtable used to store all tokenized words/word counts
    private HashTable<Integer> table = new HashTable<>();

    // an int that stores the tokenized input
    private ArrayList<String> rawWordList;

    // a String[] that stores the most common words in order
    private final String[] commonWords;

    // the priority queue that stores all words
    private PriorityQueue<Integer, String> queue = new PriorityQueue<>();


    /**
     * class constructor that takes in a .txt file
     * @param file a .txt file
     */
    public WordStat(String file) {
        Tokenizer t = new Tokenizer(file);
        for (String word : t.wordList()) {  // puts all words in a hashtable
            table.put(word, 1);
        }
        rawWordList = t.wordList();
        queue = new PriorityQueue<>(table.getValueList(), table.getStringList());
        commonWords = queue.peek(queue.size() - 1);
    }


    /**
     * class constructor that takes in a String[]
     * @param text a String[]
     */
    public WordStat(String[] text) {
        Tokenizer t = new Tokenizer(text);
        for (String word : t.wordList()) {  // puts all words in a hashtable
            table.put(word, 1);
        }
        rawWordList = t.wordList();
        queue = new PriorityQueue<>(table.getValueList(), table.getStringList());
        commonWords = queue.peek(queue.size());
    }


    /**
     * returns the number of times a given word was found
     * @param word a String
     * @return the number of times the word was found
     */
    public int wordCount(String word) {
        try {
            return table.get(word);
        } catch (Exception e) {
            return 0;
        }
    }


    /**
     * finds the "rank" of a word based on how common it is
     * 1 would be the most common word, and greater than 1 would be less common
     * @param word a String
     * @return the rank of the word
     */
    public int wordRank(String word) {
        for (int i = 0; i < commonWords.length; i++) {
            if (Objects.equals(commonWords[i], word))  // if it's the word
                return i + 1;  // index + 1
        }
        return -1;
    }


    /**
     * returns the k most common words found
     * @param k the number of words to be returned
     * @return a String[] containing the k most common words
     */
    public String[] mostCommonWords(int k) {
        String[] arr = new String[k];
        for (int i = 0; i < k; i++) {  // copies array up to k
            arr[i] = commonWords[i];
        }
        return arr;
    }


    /**
     * returns the k least common words found
     * @param k the number of words to be returned
     * @return a String[] containing the k least common words
     */
    public String[] leastCommonWords(int k) {
        String[] arr = new String[k];
        for (int i = k - 1; i > -1; i--) {  // reverse copies array
            arr[i] = commonWords[i];
        }
        return arr;
    }


    /**
     * finds the k most common words before/after a given word
     * @param k an int
     * @param baseWord a String, word to be found
     * @param precede true = before baseWord, false = after baseWord
     * @return a String[]
     */
    public String[] mostCommonCollocations(int k, String baseWord, boolean precede) {

        // find the index of the word
        int i = 0;
        while (!Objects.equals(rawWordList.get(i), baseWord)) {
            i++;
            if (i > rawWordList.size())
                throw new IllegalArgumentException();
        }

        // create a new hashtable
        HashTable<Integer> subTable = new HashTable<>();
        if (precede) {
            for (int j = 0; j < i; j++) {
                subTable.put(rawWordList.get(j), 1);
            }
        }
        else {
            for (int j = i + 1; j < rawWordList.size(); j++) {
                subTable.put(rawWordList.get(j), 1);
            }
        }

        // create a new priorityQueue
        PriorityQueue<Integer, String> subQueue = new PriorityQueue<>(subTable.getValueList(), subTable.getStringList());
        String[] commonCollocations = subQueue.peek(subQueue.size() - 1);

        // return the first k of these
        String[] kCommonCollocations = new String[k];
        for (int z = 0; z < k; z++) {
            kCommonCollocations[z] = commonCollocations[z];
        }
        return kCommonCollocations;
    }

}
