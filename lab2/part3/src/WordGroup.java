public class WordGroup {
  private String words;

  public WordGroup(String word){
    words = word.toLowerCase();
  }

  public String[] getWordArray(){
    String[] word = words.split(" ");
    return word;
  }
}
