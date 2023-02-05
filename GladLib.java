/* Import the necessary libraries */
import java.util.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class GladLib {
	/* main */
	public static void main(String[] args) throws IOException {
		/* Call non-static tester() */
		GladLib tester = new GladLib();
		tester.makeStory();
	}
	/* Declare the necessary variables */
	private ArrayList<String> adjectiveList;
	private ArrayList<String> nounList;
	private ArrayList<String> colorList;
	private ArrayList<String> countryList;
	private ArrayList<String> nameList;
	private ArrayList<String> animalList;
	private ArrayList<String> timeList;
	private ArrayList<String> verbList;
	private ArrayList<String> fruitList;
	private ArrayList<String> usedWords;

	private Random myRandom;
	
	private static String dataSourceDirectory = "data";
	
	/* Constructor */
	public GladLib() throws IOException {
		initializeFromSource(dataSourceDirectory);
		myRandom = new Random();
	}
	
	/* Constructor */
	public GladLib(String source) throws IOException {
		initializeFromSource(source);
		myRandom = new Random();
	}
	
	/* private void initializeFromSource(String source) */
	private void initializeFromSource(String source) throws IOException {
		adjectiveList= readIt(source+"/adjective.txt");	
		nounList = readIt(source+"/noun.txt");
		colorList = readIt(source+"/color.txt");
		countryList = readIt(source+"/country.txt");
		nameList = readIt(source+"/name.txt");		
		animalList = readIt(source+"/animal.txt");
		timeList = readIt(source+"/timeframe.txt");
		verbList = readIt(source+"/verb.txt");
		fruitList = readIt(source+"/fruit.txt");
	}
	
	/* private String randomFrom(ArrayList<String> source) */
	private String randomFrom(ArrayList<String> source){
		int index = myRandom.nextInt(source.size());
		return source.get(index);
	}
	
	/* private String getSubstitute(String label) */
	private String getSubstitute(String label) {
		if (label.equals("country")) {
			return randomFrom(countryList);
		}
		if (label.equals("color")){
			return randomFrom(colorList);
		}
		if (label.equals("noun")){
			return randomFrom(nounList);
		}
		if (label.equals("name")){
			return randomFrom(nameList);
		}
		if (label.equals("adjective")){
			return randomFrom(adjectiveList);
		}
		if (label.equals("animal")){
			return randomFrom(animalList);
		}
		if (label.equals("timeframe")){
			return randomFrom(timeList);
		}
		if (label.equals("number")){
			return ""+myRandom.nextInt(50)+5;
		}
		if (label.equals("verb")){
			return randomFrom(verbList);
		}
		if (label.equals("fruit")){
			return randomFrom(fruitList);
		}
		return "**UNKNOWN**";
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
		String sub = getSubstitute(w.substring(first+1,last));
		while (usedWords.contains(sub)) {
			sub = getSubstitute(w.substring(first+1,last));
		}
		usedWords.add(sub);
		return prefix+sub+suffix;
	}
	
	/* private void printOut(String s, int lineWidth) */
	private void printOut(String s, int lineWidth){
		int charsWritten = 0;
		for(String w : s.split("\\s+")){
			if (charsWritten + w.length() > lineWidth){
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
	
	/* public void makeStory() */
	public void makeStory() throws IOException{
		usedWords = new ArrayList<String>();
		System.out.println();
		String story = fromTemplate("datalong/madtemplate.txt");
		printOut(story, 60);
		System.out.println("\n");
		System.out.println("The number of words that were replaced: " + usedWords.size());
	}
}
