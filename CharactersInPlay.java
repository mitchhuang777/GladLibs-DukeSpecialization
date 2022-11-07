
/* Import */
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/* CharactersInPlay */
public class CharactersInPlay {
    /* main */
    public static void main(String[] args) throws IOException {
        /* Call non-static tester() */
        CharactersInPlay tester = new CharactersInPlay();
        tester.tester();
        tester.charactersWithNumParts(10, 15);

    }

    /* Instance variables */
    private ArrayList<String> myCharacters;
    private ArrayList<Integer> myFreqs;

    /* Constructor */
    public CharactersInPlay() {
        myCharacters = new ArrayList<String>();
        myFreqs = new ArrayList<Integer>();
    }

    /* Update */
    public void update(String person) {
        int index = myCharacters.indexOf(person);
        if (index == -1) {
            myCharacters.add(person);
            myFreqs.add(1);
        } else {
            int value = myFreqs.get(index);
            myFreqs.set(index, value + 1);
        }
    }

    /* Find characters */
    public void findAllCharacters() throws IOException {
        myCharacters.clear();
        myFreqs.clear();
        File file = new File("likeit.txt");
        BufferedReader br = new BufferedReader(new FileReader(file));
        for (String line; (line = br.readLine()) != null;) {
            if (line.contains(".")) {
                int index = line.indexOf(".");
                String person = line.substring(0, index);
                update(person);
            }
        }
        br.close();
    }

    /* Characters with num parts */
    public void charactersWithNumParts(int num1, int num2) {
        for (int i = 0; i < myCharacters.size(); i++) {
            if (myFreqs.get(i) >= num1 && myFreqs.get(i) <= num2) {
                System.out.println(myCharacters.get(i) + "\t" + myFreqs.get(i));
            }
        }
    }

    /* Tester */
    public void tester() throws IOException {
        findAllCharacters();
        for (int i = 0; i < myCharacters.size(); i++) {
            if (myFreqs.get(i) > 1) {
                System.out.println(myCharacters.get(i) + "\t" + myFreqs.get(i));
            }
        }
    }
}