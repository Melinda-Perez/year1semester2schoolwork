import java.io.*;
public class FlashCardReader {
  BufferedReader reader;

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

}
