public class Consumer extends QueueWorker{

  /**
   * Initalises a new QueueWorker for the given {@link NumberQueue}
   *
   * @param queue
   */
  public Consumer(NumberQueue queue) {
    super(queue);
  }

  @Override
  protected int action() {
    int element = queue.dequeue();
    return element;
  }
}
