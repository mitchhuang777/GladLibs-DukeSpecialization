/* Import the necessary libraries */
import java.util.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/* codonCount */
public class codonCount{
    /* main */
    public static void main(String[] args) throws IOException {
        /* Call non-static tester() */
        codonCount tester = new codonCount();
        tester.tester();
    }

    /* Create a private variable to store a HashMap to map DNA codons to their count. */
    private HashMap<String, Integer> codonMap;

    /* Create a constructor to initialize the HashMap variable. */
    public codonCount(){
        codonMap = new HashMap<String, Integer>();
    }

    /* Create a void buildCodonMap method that has two parameters, an int start and a String dna. */
    public void buildCodonMap(int start, String dna){
        /* This method will build a new map of codons mapped to their counts from the string dna with the reading frame with the position start (a value of 0, 1, or 2). */
        codonMap.clear();
        for(int i = start; i <= dna.length() - 3; i += 3){
            String codon = dna.substring(i, i + 3);
            if(!codonMap.containsKey(codon)){
                codonMap.put(codon, 1);
            } else {
                codonMap.put(codon, codonMap.get(codon) + 1);
            }
        }
    }

    /* Create a String getMostCommonCodon method that has no parameters. */
    public String getMostCommonCodon(){
        /* This method will return the codon in a reading frame that has the largest count. */
        int max = 0;
        String mostCommonCodon = "";
        for(String codon : codonMap.keySet()){
            if(codonMap.get(codon) > max){
                max = codonMap.get(codon);
                mostCommonCodon = codon;
            }
        }
        return mostCommonCodon;
    }

    /* Create a void printCodonCounts method that has two int parameters, start and end. */
    public void printCodonCounts(int start, int end){
        /* This method will print all the codons in the HashMap along with their counts if their count is between start and end, inclusive. */
        for(String codon : codonMap.keySet()){
            if(codonMap.get(codon) >= start && codonMap.get(codon) <= end){
                System.out.println(codon + "\t" + codonMap.get(codon));
            }
        }
    }

    /* Create a tester method that has no parameters. */
    public void tester() throws IOException{
        /* This method will call buildCodonMap with the file data/GRch38dnapart.fa and 0 as the start position. */
        File file = new File("dnaMystery2.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        String dna = "";
        for (String line; (line = br.readLine()) != null;) {
            dna += line;
        }
        
        buildCodonMap(0, dna);
        System.out.println("Reading frame starting with 0 results in " + codonMap.size() + " unique codons");
        String mostCommonCodon = getMostCommonCodon();
        System.out.println("and most common codon is " + mostCommonCodon + " with count " + codonMap.get(mostCommonCodon));
        System.out.println("Counts of codons between 1 and 5 inclusive are:");
        printCodonCounts(1, 1000);
        

        /* This method will then call buildCodonMap with the same file but start position 1. */
        buildCodonMap(1, dna);
        System.out.println("Reading frame starting with 1 results in " + codonMap.size() + " unique codons");
        mostCommonCodon = getMostCommonCodon();
        System.out.println("and most common codon is " + mostCommonCodon + " with count " + codonMap.get(mostCommonCodon));
        System.out.println("Counts of codons between 1 and 5 inclusive are:");
        printCodonCounts(1, 1000);

        
        /* This method will then call buildCodonMap with the same file but start position 2. */
        buildCodonMap(2, dna);
        System.out.println("Reading frame starting with 2 results in " + codonMap.size() + " unique codons");
        mostCommonCodon = getMostCommonCodon();
        System.out.println("and most common codon is " + mostCommonCodon + " with count " + codonMap.get(mostCommonCodon));
        System.out.println("Counts of codons between 1 and 5 inclusive are:");
        printCodonCounts(1, 1000);
        
        br.close();
    }
}