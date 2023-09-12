package assignment4;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;

/**
 * Tokenizer class for assignment 4
 * @author rtp32
 */
public class Tokenizer {

    // an array of words from the text file/array
    private ArrayList<String> wordList = new ArrayList<>();

    /**
     * constructor for Tokenizer class which takes a .txt file
     * @param file a .txt file
     */
    public Tokenizer(String file) {
        try (BufferedReader in = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = in.readLine()) != null)  // reads all lines
                tokenizeLine(line);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * constructor for Tokenizer class which takes an array of strings
     * @param text an array of strings
     */
    public Tokenizer(String[] text) {
        for (String line: text) {  // this method is a little more straightforward
            tokenizeLine(line);
        }
    }


    /**
     * private method to tokenize a line of text
     * does the bulk of the work in this class
     * @param line a line of text
     */
    private void tokenizeLine(String line) {
        String[] cleaned = line.trim().split("\\s+");  // trims out whitespace
        for (String word : cleaned) {
            StringBuilder newStr = new StringBuilder();
            for (int i = 0; i < word.length(); i++) {
                if ((Character.isAlphabetic(word.charAt(i)) || Character.isDigit(word.charAt(i))))
                    newStr.append(Character.toLowerCase(word.charAt(i)));  // strips down to alphanumerics only
            }
            if (newStr.length() > 0)
                wordList.add(newStr.toString());
        }
    }


    /**
     * returns the final, tokenized word list
     * @return the word list
     */
    public ArrayList<String> wordList() {
        return wordList;
    }

}
