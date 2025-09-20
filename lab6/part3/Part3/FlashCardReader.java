import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FlashCardReader {
    private BufferedReader reader;
    
    public FlashCardReader(String filename) {
        FileReader fr;
        try {
            fr = new FileReader(filename);
            reader = new BufferedReader(fr);
        } catch (FileNotFoundException e) {
            // nix
        }
    }

    public boolean fileIsReady() {
        try {
            return reader != null && reader.ready();
        } catch (IOException e) {
            return false;
        }
    }

    public String getLine() {
        try {
            return reader.readLine();
        } catch (IOException e) {
            return null;
        }
    }

    public ArrayList<FlashCard> getFlashCards() {
        ArrayList<FlashCard> cards = new ArrayList<>();
        String line;
        while ((line = getLine()) != null) {
            String[] data = line.split(":");
            cards.add(new FlashCard(data[0], data[1]));
        }
        return cards;
    }
}
