public class ConcurrentCyclicQueue extends CyclicQueue{

  public ConcurrentCyclicQueue(int capacity) {
    super(capacity);
  }

  public synchronized void enqueue(int number){
    while(isFull()){
      try{
        wait();
      }catch (InterruptedException e){
        throw new RuntimeException();
      }
    }
    super.enqueue(number);
    notifyAll();
  }

  public synchronized int dequeue(){
    while(isEmpty()){
      try{
        wait();
      }catch (InterruptedException e){
        throw new RuntimeException();
      }
    }
    int number = super.dequeue();
    notifyAll();;
    return number;
  }
}
