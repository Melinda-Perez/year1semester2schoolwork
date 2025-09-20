public class CyclicQueue implements NumberQueue{
  private int head = 0;
  private int tail = 0;
  private int[] array;


  public CyclicQueue(int capacity){
    array = new int[capacity + 1];
  }

  @Override
  public int dequeue() {
    if(isEmpty())
      throw new IllegalStateException();
    var res = array[tail];
    tail = (tail + 1) % array.length;
    return res;
  }

  @Override
  public void enqueue(int n) {
    if(isFull())
      throw new IllegalStateException();
    array[head] = n;
    head = (head + 1) % array.length;
  }

  @Override
  public boolean isEmpty() {
    return (head == tail);
  }

  @Override
  public boolean isFull() {
    return (head + 1) % array.length == tail;
  }
}
