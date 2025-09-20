import java.util.HashMap;
import java.util.HashSet;

public class Main {
  public static void main(String[]args){
    WordGroup wordGroup1 = new WordGroup("You can discover more about a person in an hour of play than in a year of conversation");
    WordGroup wordGroup2 = new WordGroup("When you play play hard when you work dont play at all");

    String[] word1 = wordGroup1.getWordArray();
    String[] word2 = wordGroup2.getWordArray();

    for(String word:word1){
      System.out.println(word);
    }
    for(String word:word2){
      System.out.println(word);
    }

    HashMap<String,Integer> map1 = wordGroup1.getWordCounts();
    HashMap<String,Integer> map2 = wordGroup2.getWordCounts();

    for(String key:map1.keySet()){
      Integer value = map1.get(key);
      System.out.println(key + ":" + value);
    }

    for(String key:map2.keySet()){
      Integer value = map2.get(key);
      System.out.println(key + ":" + value);
    }

    HashSet<String> set = wordGroup1.getWordSet(wordGroup1);
    set.addAll(wordGroup2.getWordSet(wordGroup2));

    for(String word:set){
      int count1 = map1.getOrDefault(word,0);
      int count2 = map2.getOrDefault(word,0);
      System.out.println(word + ": " + count1);
      System.out.println(word + ": " + count2);
    }




  }

}
