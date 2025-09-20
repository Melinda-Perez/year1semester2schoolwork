import java.io.*;
import java.util.ArrayList;

public class FlashCardReader {
  BufferedReader reader;
  ArrayList<FlashCard> flashCards;

  public FlashCardReader(String filename)  {
    try{
      reader = new BufferedReader(new FileReader(filename));
    }catch (FileNotFoundException e){
      e.printStackTrace();
    }
  }

  public String getLine() {
    if(reader == null){
      return null;
    }
    try{
      return reader.readLine();
    }catch (IOException e){
      e.printStackTrace();
      return null;
    }
  }

  public boolean fileIsReady() {
    if(reader == null){
      return false;
    }
    try{
      return reader.ready();
    }catch (IOException e){
      return false;
    }
  }

  public ArrayList<FlashCard> getFlashCards(){
    flashCards = new ArrayList<>();
    try{
      String line;
      while((line = getLine()) != null){
        String[] parts = line.split(":");
        if(parts.length == 2){
          flashCards.add(new FlashCard(parts[0],parts[1]));
        }
      }
    }catch (Exception e){
      e.printStackTrace();
    }
    return flashCards;
  }

}
