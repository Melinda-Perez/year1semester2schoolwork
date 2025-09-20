public class Gate implements Runnable{
  private int numOfGuests;
  private Counter counter;
  public Gate(Counter counter,int guests){
    numOfGuests = guests;
    this.counter = counter;
  }

  @Override
  public void run() {
    int guests = numOfGuests;
    while(guests > 0){
      counter.addOne();
      guests --;
    }

  }
}
