

public class ATM {
  Toolbox myToolbox = new Toolbox();
  int balance;
  public static void main(String[]args){
    ATM myATM = new ATM();
    myATM.go();
  }

  public void go(){
    System.out.println("Welcome to online ATM banking");
    System.out.println("How much do you want in your account?");
    balance = myToolbox.readIntegerFromCmd();
    System.out.println("Your balance is " + balance);

    System.out.println("What do you want to do?");
    System.out.println("1:Withdraw");
    System.out.println("2:Deposit");
    System.out.println("3:Inquire");
    System.out.println("4:Quit");
    int choice = myToolbox.readIntegerFromCmd();
    if (choice == 1){
      withdraw();
    }
    if (choice == 2){
      deposite();
    }
    if(choice == 3){
      inquire();
    }
    if(choice == 4) {
      quit();
    }
  }
  public void withdraw(){
    System.out.println("*****************************************");
    System.out.println("Withdrawal");
    System.out.println("*****************************************");
    System.out.println("How much would you like to withdraw?");
    int withdraw = myToolbox.readIntegerFromCmd();
    balance = balance - withdraw;
    System.out.println("*****************************************");
    System.out.println("Your new balance is " + balance);
    System.out.println("*****************************************");
  }

  public void deposite(){
    System.out.println("*****************************************");
    System.out.println("Deposit");
    System.out.println("*****************************************");
    System.out.println("How much would you like to deposit?");
    int deposit = myToolbox.readIntegerFromCmd();
    balance = balance + deposit;
    System.out.println("*****************************************");
    System.out.println("Your new balance is " + balance);
  }

  public void inquire(){
    System.out.println("*****************************************");
    System.out.println("Your balance is " + balance);
    System.out.println("*****************************************");
  }

  public void quit(){
    System.out.println("*****************************************");
    System.out.println("GoodBye!");
    System.out.println("*****************************************");
  }
}
