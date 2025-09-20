public class Counter {
  private int count;
  public synchronized void addOne(){
    count = count + 1;
  }

  public synchronized int getCount(){
    return count;
  }

}
