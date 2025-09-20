/*
 * A test queue which never becomes full and is never empty.
 */
public class VoidQueue implements NumberQueue
{
    public VoidQueue()
    {
    }

    @Override
    public int dequeue() {
        return 1;
    }

    @Override
    public void enqueue(int n) {
        return;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean isFull() {
        return false;
    }
}
