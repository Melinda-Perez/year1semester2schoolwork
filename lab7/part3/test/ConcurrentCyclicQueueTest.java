import static org.junit.jupiter.api.Assertions.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.lang.Thread.State;
import java.lang.reflect.InvocationTargetException;
import java.net.PortUnreachableException;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;

class ConcurrentCyclicQueueTest {
    /**
     * Tests basic operation of the CyclicQueue to ensure it still works as a NumberQueue from a single thread.
     * 
     * @throws InterruptedException
     */
    @Test
    void testCyclicQueueBasicOperation() throws InterruptedException {
        NumberQueue queue = new CyclicQueue(10);

        assertTrue(queue.isEmpty(), "Queue should be empty");
        assertFalse(queue.isFull(), "Queue should not be full");
        
        for (int i = 0; i < 5; i++)
            queue.enqueue(i);

        assertFalse(queue.isEmpty(), "Queue should not be empty");
        assertFalse(queue.isFull(), "Queue should not be full");

        for (int i = 0; i < 5; i++)
            queue.enqueue(i);

        assertFalse(queue.isEmpty(), "Queue should not be empty");
        assertTrue(queue.isFull(), "Queue should be full");

        for (int i = 0; i < 5; i++)
            assertEquals(i, queue.dequeue());

        assertFalse(queue.isEmpty(), "Queue should not be empty");
        assertFalse(queue.isFull(), "Queue should not be full");
    
        for (int i = 0; i < 5; i++)
            assertEquals(i, queue.dequeue());

        assertTrue(queue.isEmpty(), "Queue should be empty");
        assertFalse(queue.isFull(), "Queue should not be full");
    }

    /**
     * Tests whether the producer blocks on a full queue
     * 
     * @throws InterruptedException
     */
    @Test
    void testProducerBlocksOnFullQueue() throws InterruptedException {
        NumberQueue queue = new ConcurrentCyclicQueue(10);
        Producer producer = new Producer(queue);
        
        Thread workerThread = new Thread(producer);
        workerThread.start();

        Thread.sleep(500);

        assertTrue(queue.isFull(), "Queue should be full");
        assertFalse(producer.getErrored());

        producer.setShouldStop();
        queue.dequeue();

        workerThread.join();
    }

    /**
     * Tests whether the consumer blocks on an empty queue
     * 
     * @throws InterruptedException
     */
    @Test
    void testProducerBlocksOnEmptyQueue() throws InterruptedException {
        NumberQueue queue = new ConcurrentCyclicQueue(10);
        queue.enqueue(100);

        Consumer consumer = new Consumer(queue);
        
        Thread workerThread = new Thread(consumer);
        workerThread.start();

        Thread.sleep(500);

        assertTrue(queue.isEmpty(), "Queue should be empty");
        assertFalse(consumer.getErrored());

        consumer.setShouldStop();
        queue.enqueue(50);

        workerThread.join();

        assertEquals(150, consumer.getTotalOfWork());
    }

    /**
     * Tests whether the producer populates the given NumberQueue.
     * 
     * @throws InterruptedException
     */
    @Test
    void testConcurrentProducersAndConsummers() throws InterruptedException {
        NumberQueue queue = new ConcurrentCyclicQueue(10);
        
        int workerCounts = 5;

        ArrayList<Producer> producers = new ArrayList<>();
        ArrayList<Consumer> consumers = new ArrayList<>();
        ArrayList<Thread> producerThreads = new ArrayList<>();
        ArrayList<Thread> consumerThreads = new ArrayList<>();

        for (int i = 0; i < workerCounts; i++) {
            var producer = new Producer(queue);
            var consumer = new Consumer(queue);
            var producerThread = new Thread(producer);
            var consumerThread = new Thread(consumer);

            producers.add(producer);
            consumers.add(consumer);
            producerThreads.add(producerThread);
            consumerThreads.add(consumerThread);

            producerThread.start();
            consumerThread.start();
        }

        Thread.sleep(500);

        for (var producer: producers) {
            producer.setShouldStop();
        }

        for (var producerThread: producerThreads) {
            producerThread.join();
        }

        // all producers are stopped
        for (var consumer: consumers) {
            consumer.setShouldStop();
        }

        // all producers are marked for stop; need to give them a final element to munch
        for (int i = 0; i < workerCounts; i++) {
            queue.enqueue(0); // zero so we don't mess with the totals
        }

        for (var consumerThread: consumerThreads) {
            consumerThread.join();
        }

        int producerSum = producers.stream().mapToInt(p -> p.getTotalOfWork()).sum();
        int consumerSum = consumers.stream().mapToInt(c -> c.getTotalOfWork()).sum();

        // assert consumers consumed the same as produced by the producers
        assertEquals(producerSum, consumerSum);

        // check nobody errored
        for (var producer: producers) {
            assertFalse(producer.getErrored());
        }

        for (var consumer: consumers) {
            assertFalse(consumer.getErrored());
        }

    }
}
