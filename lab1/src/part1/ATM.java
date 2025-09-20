public class ATM {
  Toolbox myToolbox = new Toolbox();
  public static void main(String[]args){
    ATM myATM = new ATM();
    myATM.go();
  }

  public int go(){
    System.out.println("Welcome to online ATM banking");
    System.out.println("How much do you want in your account?");
    int balance = myToolbox.readIntegerFromCmd();
    System.out.println("Your balance is " + balance);
    return balance;
  }
}
