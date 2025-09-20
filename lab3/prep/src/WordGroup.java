import java.util.HashSet;

public class WordGroup {
  private String words;

  public WordGroup(String word){
    words = word.toLowerCase();
  }

  public String[] getWordArray(){
    String[] word = words.split(" ");
    return word;
  }

  public HashSet<String> getWordSet(WordGroup wordGroup){
    HashSet<String> set = new HashSet<>();
    for(String word: this.getWordArray()){
      set.add(word);
    }
    for(String word:wordGroup.getWordArray()){
      set.add(word);
    }
    return set;
  }
}
