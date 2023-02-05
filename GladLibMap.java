/* Import the necessary libraries */
import java.util.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class GladLibMap {
	/* main */
	public static void main(String[] args) throws IOException {
		/* Call non-static tester() */
		GladLibMap tester = new GladLibMap();
        tester.makeStory();
	}

    /* Replace the ArrayLists with HashMaps */
	private HashMap<String, ArrayList<String>> myMap;
    private ArrayList<String> usedWords;
    private ArrayList<String> usedCategories;

	private Random myRandom;
	
	private static String dataSourceDirectory = "data";
	
	/* Create the new HashMap constructor */
	public GladLibMap() throws IOException {
        myMap = new HashMap<String, ArrayList<String>>();
        initializeFromSource(dataSourceDirectory);
        myRandom = new Random();
	}
	
	/* Create the new HashMap constructor */
	public GladLibMap(String source) throws IOException {
        myMap = new HashMap<String, ArrayList<String>>();
        initializeFromSource(source);
        myRandom = new Random();
	}
	
	/* private void initializeFromSource(String source) */
	private void initializeFromSource(String source) throws IOException {
        String[] categories = {"adjective", "noun", "color", "country", "name", "animal", "timeframe", "verb", "fruit"};
        for (String s : categories) {
            myMap.put(s, readIt(source+"/"+s+".txt"));
        }
        usedWords = new ArrayList<String>();
        usedCategories = new ArrayList<String>();
	}
	
	/* private String randomFrom(ArrayList<String> source) */
	private String randomFrom(ArrayList<String> source){
        int index = myRandom.nextInt(source.size());
        return source.get(index);
	}
	
	/* private String getSubstitute(String label) */
	private String getSubstitute(String label) {
        if (label.equals("number")) {
            return ""+myRandom.nextInt(50)+5;
        }
        return randomFrom(myMap.get(label));
	}
	
	/* private String processWord(String w) */
	private String processWord(String w) {
        int first = w.indexOf("<");
        int last = w.indexOf(">",first);
        if (first == -1 || last == -1){
            return w;
        }
        String prefix = w.substring(0,first);
        String suffix = w.substring(last+1);
        String sub;
        do {
            sub = getSubstitute(w.substring(first+1,last));

        } while (usedWords.contains(sub));
        /* usedCategories */
        usedWords.add(sub);
        if (!usedCategories.contains(w.substring(first+1,last))) {
            usedCategories.add(w.substring(first+1,last));
        }
        return prefix+sub+suffix;
	}
	
	/* private void printOut(String s, int lineWidth) */
    private void printOut(String s, int lineWidth) {
        int charsWritten = 0;
        for(String w : s.split("\\s+")){
            if (charsWritten + w.length() > lineWidth) {
                System.out.println();
                charsWritten = 0;
            }
            System.out.print(w+" ");
            charsWritten += w.length() + 1;
        }
    }
	
	/* private String fromTemplate(String source) */
	private String fromTemplate(String source) throws IOException {
		File file = new File(source);
		BufferedReader br = new BufferedReader(new FileReader(file));
		/* for word in words */
		String line = br.readLine();
		String story = "";
		while (line != null) {
			String[] words = line.split("\\s+");
			for (String word : words) {
				story = story + processWord(word) + " ";
			}
			line = br.readLine();
		}
		br.close();
		return story;
	}
	
	/* private ArrayList<String> readIt(String source) */
	private ArrayList<String> readIt(String source) throws IOException{
		ArrayList<String> list = new ArrayList<String>();
		File f = new File(source);
		BufferedReader br = new BufferedReader(new FileReader(f));
		String line;
		/* Readline and the line is not empty */
		while ((line = br.readLine()) != null && !line.isEmpty()) {
			list.add(line);
		}
		br.close();

		return list;
	}

    /* private int totalWordsInMap() */
    private int totalWordsInMap() {
        int total = 0;
        for (String s : myMap.keySet()) {
            total += myMap.get(s).size();
        }
        return total;
    }

    /* private int totalWordsConsidered() */
    private int totalWordsConsidered() {
        int total = 0;
        for (String s : usedCategories) {
            total += 1;
        }
        return total;
    }

	/* public void makeStory() */
	public void makeStory() throws IOException{
        System.out.println("\n");
        String story = fromTemplate("data/madtemplate1.txt");
        printOut(story, 60);
        System.out.println("\n");
        System.out.println("Total words replaced: " + usedWords.size());
        System.out.println("Total words in the map: " + totalWordsInMap());
        System.out.println("Total words considered: " + totalWordsConsidered());
	}
}
