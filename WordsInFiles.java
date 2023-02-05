/* Import necessary libraries */
import java.util.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.FileReader;


public class WordsInFiles {
    /* main */
    public static void main(String[] args) throws IOException {
        /* Call non-static tester() */
        WordsInFiles tester = new WordsInFiles();
        tester.tester();
    }

    /* Create a private variable to store a HashMap that maps a words to an ArrayList of filenames */
    private HashMap<String, ArrayList<String>> wordsInFiles;

    /* Create a constructor to initialize the HashMap variable */
    public WordsInFiles(){
        wordsInFiles = new HashMap<String, ArrayList<String>>();
    }

    /* Create a void addWordsFromFile method that has one parameter, a File named f */
    public void addWordsFromFile(File f) throws IOException{
        /* This method will add all the words from f into the map. If a word is not in the map, then you must create a new ArrayList of type String with this word, and have the word map to this ArrayList. If a word is already in the map, then add the current filename to its ArrayList, unless the filename is already in the ArrayList. */
        FileReader fr = new FileReader(f);
        BufferedReader br = new BufferedReader(fr);
        String line = br.readLine();
        while(line != null){
            String[] words = line.split("\\W+");
            for(String word : words){
                if(!wordsInFiles.containsKey(word)){
                    ArrayList<String> filenames = new ArrayList<String>();
                    filenames.add(f.getName());
                    wordsInFiles.put(word, filenames);
                } else {
                    ArrayList<String> filenames = wordsInFiles.get(word);
                    if(!filenames.contains(f.getName())){
                        filenames.add(f.getName());
                    }
                }
            }
            line = br.readLine();
        }
        br.close();
    }

    /* Create a void buildWordFileMap method that has no parameters */
    public void buildWordFileMap() throws IOException{
        /* This method first clears the map, and then uses a DirectoryResource to select a group of files. For each file, it puts all of its words into the map by calling the method addWordsFromFile. */
        wordsInFiles.clear();
        
        DirectoryResource dr = new DirectoryResource();
        for(File f : dr.selectedFiles()){
            addWordsFromFile(f);
        }

    }

    /* Create a int maxNumber method that has no parameters */
    public int maxNumber(){
        /* This method returns the maximum number of files any word appears in, considering all words from a group of files. */
        int max = 0;
        for(String word : wordsInFiles.keySet()){
            /* If key equal to "", then skip */
            if(word.equals("")){
                continue;
            }
            if(wordsInFiles.get(word).size() > max){
                max = wordsInFiles.get(word).size();
            }
        }
        return max;
    }

    /* Create a ArrayList<String> wordsInNumFiles method that has one int parameter, number */
    public ArrayList<String> wordsInNumFiles(int number){
        /* This method returns an ArrayList of words that appear in exactly number files. */
        ArrayList<String> words = new ArrayList<String>();
        for(String word : wordsInFiles.keySet()){
            if(wordsInFiles.get(word).size() == number){
                words.add(word);
            }
        }
        return words;
    }

    /* Create a void printFilesIn method that has one String parameter, word */
    public void printFilesIn(String word) throws IOException{
        /* This method prints the names of the files this word appears in, one filename per line. */
        for(String filename : wordsInFiles.get(word)){
            System.out.println(filename);

        }
        
    }
    /* Create a void tester method that has no parameters */
    public void tester() throws IOException{
        /* call buildWordFileMap */
        try{
            buildWordFileMap();
        } catch(IOException e){
            System.out.println("Error: " + e);
        }
        /* determine the maximum number of files any word is in, considering all words, and print the maximum number of files any word is in, considering all words */
        int max = maxNumber();
        System.out.println("The maximum number of files any word is in is: " + max);
        /* determine all the words that are in the maximum number of files and for each such word, print the filenames of the files it is in */
        ArrayList<String> words = wordsInNumFiles(max);
        for(String word : words){
            /* If ArrayList words equal to "", then skip */
            if(word.equals("")){
                continue;
            }
            System.out.println("The word " + word + " appears in the following files: ");
            printFilesIn(word);
 
        }
        // System.out.println("The number of words that appear in the 4 files is: " + wordCounter);
        
    }
}