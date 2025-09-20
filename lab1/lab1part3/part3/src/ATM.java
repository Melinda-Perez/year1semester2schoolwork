

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
    do {
      balance = myToolbox.readIntegerFromCmd();
    }while (balance < 0);
    System.out.println("Your balance is " + balance);

    while(true) {
      System.out.println("What do you want to do?");
      System.out.println("1 : Withdraw");
      System.out.println("2 : Deposit");
      System.out.println("3 : Inquire");
      System.out.println("4 : Quit");
      int choice = myToolbox.readIntegerFromCmd();
      if (choice == 1) {
        withdraw();
      }
      if (choice == 2) {
        deposit();
      }
      if (choice == 3) {
        inquire();
      }
      if (choice == 4) {
        quit();
      }
    }
  }
  public void withdraw(){
    System.out.println("*****************************************");
    System.out.println("Withdrawal");
    System.out.println("*****************************************");
    System.out.println("How much would you like to withdraw?");
    int withdraw;
    int balance1;
    do {
      withdraw = myToolbox.readIntegerFromCmd();
      balance1 = balance - withdraw;
    }while(withdraw < 0 || balance1 < 0);
    balance = balance1;
    System.out.println("*****************************************");
    System.out.println("Your new balance is " + balance);
    System.out.println("*****************************************");
  }

  public void deposit(){
    System.out.println("*****************************************");
    System.out.println("Deposit");
    System.out.println("*****************************************");
    System.out.println("How much would you like to deposit?");
    int deposit;
    do{
      deposit= myToolbox.readIntegerFromCmd();
    }while (deposit < 0);
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
    System.exit(0);
  }
}
