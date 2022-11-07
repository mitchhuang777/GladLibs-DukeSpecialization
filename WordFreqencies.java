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
        /* Clear both myWords and myFreqs */
        myWords.clear();
        myFreqs.clear();
        /* Select a file and then iterates over every word in the file */
        File file = new File("testwordfreqs.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        /* Iterate over every word in the file */
        for (String line; (line = br.readLine()) != null; ) {
            /* Split the line into words and lowercase the text*/
            String[] words = line.toLowerCase().split(" ");
            /* Iterate over every word in the line */
            for (String word : words) {
                /* Check if the word is already in myWords */
                if (myWords.contains(word)) {
                    /* Get the index of the word in myWords */
                    int index = myWords.indexOf(word);
                    /* Increment the frequency of the word in myFreqs */
                    myFreqs.set(index, myFreqs.get(index) + 1);
                } else {
                    /* Add the word to myWords */
                    myWords.add(word);
                    /* Add the frequency of the word to myFreqs */
                    myFreqs.add(1);
                }
            }
        }
        /* Close file */
        br.close();
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
            System.out.println(myFreqs.get(i) + "\t" + myWords.get(i));
        }
        /* Find the index of the maximum frequency */
        int maxIndex = findIndexOfMax();
        /* Print the word with the maximum frequency */
        System.out.println("The word that occurs most often and its count are: " + myWords.get(maxIndex) + " " + myFreqs.get(maxIndex));
    }
    
}
