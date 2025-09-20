import java.util.ArrayList;

public class Quiz {
  FlashCardReader flashCardReader;
  ArrayList<FlashCard> flashCards;
  Toolbox toolbox = new Toolbox();
  public Quiz(String filename){
    flashCardReader = new FlashCardReader(filename);
    flashCards = flashCardReader.getFlashCards();
  }

  public void play(){
    for(FlashCard flashCard:flashCards){
      System.out.println(flashCard.getQuestion());
      String input = toolbox.readStringFromCmd();
      if(input.equals(flashCard.getAnswer())){
        System.out.println("You are correct.");
      }else{
        System.out.println("You are wrong. The right answer is " + flashCard.getAnswer());
      }
    }
  }

  public static void main(String args[]){
    String filename = "/Users/maypan/Desktop/java/programming2/lab6/part2/src/Questions.txt";
    Quiz quiz = new Quiz(filename);
    quiz.play();
  }
}
