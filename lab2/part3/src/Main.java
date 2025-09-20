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
  }

}
