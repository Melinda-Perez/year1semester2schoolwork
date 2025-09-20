import java.util.Random;
public class Producer extends QueueWorker{
  Random random = new Random();

  /**
   * Initalises a new QueueWorker for the given {@link NumberQueue}
   *
   * @param queue
   */
  public Producer(NumberQueue queue) {
    super(queue);
  }

  @Override
  protected int action() {
    int number = random.nextInt(10) + 1;
    queue.enqueue(number);
    return number;
  }
}
