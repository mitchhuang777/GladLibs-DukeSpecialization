
/* WordFreqencies */
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class WordFreqencies {
    public static void main(String[] args) throws IOException {
        /* Call non-static tester() */
        WordFreqencies tester = new WordFreqencies();
        tester.tester();
    }

    private ArrayList<String> myWords;
    private ArrayList<Integer> myFreqs;

    /* Call constructor */
    public WordFreqencies() {
        myWords = new ArrayList<String>();
        myFreqs = new ArrayList<Integer>();
    }

    /* void method findUnique() */
    public void findUnique() throws IOException {
        /* Create a file object */
        File file = new File("likeit.txt");
        /* Create a buffered reader object */
        BufferedReader br = new BufferedReader(new FileReader(file));
        /* Declare string variable */
        String st;
        /* While loop to read each line of the file */
        while ((st = br.readLine()) != null) {
            /* Convert string to lower case */
            st = st.toLowerCase();
            /* Split string to array by whitespace */
            String[] words = st.split("\\s+");
            /* For loop to iterate through the array */
            for (int i = 0; i < words.length; i++) {
                /* Call non-static method indexOf() */
                int index = indexOf(words[i]);
                /* If condition to check if the word is unique */
                if (index == -1) {
                    /* Call non-static method addWord() */
                    addWord(words[i]);
                } else {
                    /* Call non-static method addFreq() */
                    addFreq(index);
                }
            }
        }
        br.close();
    }

    /* void method addWord() */
    public void addWord(String word) {
        /* Call non-static method indexOf() */
        int index = indexOf(word);
        /* If condition to check if the word is unique */
        if (index == -1) {
            /* Add word to myWords array list */
            myWords.add(word);
            /* Add 1 to myFreqs array list */
            myFreqs.add(1);
        }
    }

    /* void method addFreq() */
    public void addFreq(int index) {
        /* Get the value of index */
        int value = myFreqs.get(index);
        /* Set the value to index */
        myFreqs.set(index, value + 1);
    }

    /* int method indexOf() */
    public int indexOf(String word) {
        /* For loop to iterate through the array */
        for (int i = 0; i < myWords.size(); i++) {
            /* If condition to check if the word is unique */
            if (myWords.get(i).equals(word)) {
                /* Return the index */
                return i;
            }
        }
        /* Return -1 */
        return -1;
    }

    /* int method findIndexOfMax() */
    public int findIndexOfMax() {
        /* Initialize maxIndex to 0 */
        int maxIndex = 0;
        /* Iterate over every frequency in myFreqs */
        for (int i = 0; i < myFreqs.size(); i++) {
            /* Check if the frequency is greater than the frequency at maxIndex */
            if (myFreqs.get(i) > myFreqs.get(maxIndex)) {
                /* Set maxIndex to the index of the frequency */
                maxIndex = i;
            }
        }
        /* Return maxIndex */
        return maxIndex;
    }

    /* void static method tester() */
    public void tester() throws IOException {
        /* Call findUnique() */
        findUnique();
        /* Print the number of unique words */
        System.out.println("Number of unique words: " + myWords.size());
        /* Print the words and their frequencies */
        for (int i = 0; i < myWords.size(); i++) {
            if (myFreqs.get(i) < 500) {
                continue;
            }
            System.out.println(myFreqs.get(i) + "\t" + myWords.get(i));

            /* Open a new file */
            File file = new File("words.txt");
            /* Write the words and their frequencies to the file */
            java.io.PrintWriter output = new java.io.PrintWriter(file);
            for (int j = 0; j < myWords.size(); j++) {
                output.println(myFreqs.get(j) + "\t\t" + myWords.get(j));
            }
            /* Close the file */
            output.close();

        }

        /* Find the index of the maximum frequency */
        int maxIndex = findIndexOfMax();
        /* Print the word with the maximum frequency */
        System.out.println("The word that occurs most often and its count are: " + myWords.get(maxIndex) + " "
                + myFreqs.get(maxIndex));
    }

}
