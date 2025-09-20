import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;

public class Quiz {
  private FlashCardReader flashCardReader;
  private ArrayList<FlashCard> flashCards;
  Toolbox toolbox = new Toolbox();

  private ArrayList<String> results = new ArrayList<>();
  public Quiz(String filename){
    flashCardReader = new FlashCardReader(filename);
    flashCards = flashCardReader.getFlashCards();
  }

  public void play(){
    System.out.println("Would you like to save your results(Y/N)?");
    String save = toolbox.readStringFromCmd();

    int correctAnswers = 0;
    int totalQuestions = flashCards.size();
    for(FlashCard flashCard:flashCards){
      System.out.println(flashCard.getQuestion());
      String input = toolbox.readStringFromCmd();
      if(input.equals(flashCard.getAnswer())){
        correctAnswers ++;
        System.out.println("You are correct.");
      }else{
        System.out.println("You are wrong. The right answer is " + flashCard.getAnswer());
      }
      results.add(flashCard.getQuestion() + "," + input + "," + (input.equals(flashCard.getAnswer())
          ? "right" : "wrong"));
    }
    double scorePercentage = (correctAnswers / (double) totalQuestions) * 100;

    if(save.equals("Y")){
      save(correctAnswers,totalQuestions,scorePercentage);
    }

  }

  public void save(int correctAnswers, int totalQuestions, double scorePercentage){
    try{
      PrintStream printStream = new PrintStream(new File("save.txt"));
      for(String result: results){
        printStream.println(result);
      }
      printStream.println(correctAnswers + "," + totalQuestions + "," + scorePercentage);
      printStream.close();
    }catch (FileNotFoundException e){
      e.printStackTrace();
    }
  }

  public static void main(String args[]){
    String filename = "Questions.txt";
    Quiz quiz = new Quiz(filename);
    quiz.play();
  }
}
