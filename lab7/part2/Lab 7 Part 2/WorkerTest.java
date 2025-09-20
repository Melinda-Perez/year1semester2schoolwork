import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class WorkerTest {
    /**
     * Tests that random numbers produced by producer are in expected range
     */
    @Test
    void testProducerRandomNumbers() {
        for (int r = 0; r < 100; r++) {
            NumberQueue queue = new CyclicQueue(10);
            Producer producer = new Producer(queue);
            
            for (int i = 0; i < 1; i++)
                producer.runOnce();

            assertTrue(producer.getTotalOfWork() > 0);
            assertTrue(producer.getTotalOfWork() <= 10);
        }
    }

    /**
     * Tests whether the producer populates the given NumberQueue.
     */
    @Test
    void testProducerAction() {
        NumberQueue queue = new CyclicQueue(10);
        Producer producer = new Producer(queue);
        
        for (int i = 0; i < 5; i++)
            producer.runOnce();

        assertFalse(queue.isEmpty());
        assertFalse(queue.isFull());

        for (int i = 0; i < 5; i++)
            producer.runOnce();

        assertFalse(queue.isEmpty());
        assertTrue(queue.isFull());

        // check that producer total work matched values on queue
        int expected = 0;
        while (!queue.isEmpty())
            expected += queue.dequeue();

        assertEquals(expected, producer.getTotalOfWork());
    }

    /**
     * Tests whether a consumer depopulates the given NumberQueue.
     */
    @Test
    void testConsumerAction() {
        NumberQueue queue = new CyclicQueue(10);
        for (int i = 0; i < 10; i++)
            queue.enqueue(i);

        Consumer consumer = new Consumer(queue);
        assertEquals(0, consumer.getTotalOfWork());
        
        for (int i = 0; i < 5; i++)
            consumer.runOnce();

        assertFalse(queue.isEmpty());
        assertFalse(queue.isFull());
        assertEquals(10, consumer.getTotalOfWork());

        for (int i = 0; i < 5; i++)
            consumer.runOnce();

        assertTrue(queue.isEmpty());
        assertFalse(queue.isFull());
        assertEquals(45, consumer.getTotalOfWork());
    }

    /**
     * Tests whether the producer fully populates the given NumberQueue and then throws once it is full.
     */
    @Test
    void testProducerLoopThrows() {
        NumberQueue queue = new CyclicQueue(100);
        Producer producer = new Producer(queue);
        
		Throwable exception = assertThrows(IllegalStateException.class, () -> {
            while (true)
    			producer.runOnce();
		});
        
        assertTrue(queue.isFull());
    }

    /**
     * Tests whether the producer fully populates the given NumberQueue and then throws once it is full.
     */
    @Test
    void testProducerFreeRunningErrors() {
        NumberQueue queue = new CyclicQueue(100);
        Producer producer = new Producer(queue);
        
		producer.run();
        
        assertTrue(producer.getErrored());
        assertTrue(queue.isFull());
    }

    /**
     * Tests whether the consumer fully depopulates the given NumberQueue and then throws once it is empty.
     */
    @Test
    void testConsumerLoopThrows() {
        NumberQueue queue = new CyclicQueue(100);
        Consumer consumer = new Consumer(queue);
        
        for (int i = 0; i < 100; i++)
            queue.enqueue(i);

		Throwable exception = assertThrows(IllegalStateException.class, () -> {
            while (true)
    			consumer.runOnce();
		});
        
        assertTrue(queue.isEmpty());
        assertEquals(4950, consumer.getTotalOfWork());
    }

    /**
     * Tests whether the consumer fully depopulates the given NumberQueue and then throws once it is empty.
     */
    @Test
    void testConsumerFreeRunningErrors() {
        NumberQueue queue = new CyclicQueue(100);
        Consumer consumer = new Consumer(queue);
        
        for (int i = 0; i < 100; i++)
            queue.enqueue(i);

        consumer.run();
        
        assertTrue(consumer.getErrored());
        assertTrue(queue.isEmpty());
        assertEquals(4950, consumer.getTotalOfWork());
    }

    /**
     * Tests whether a consumer stops when asked to do so.
     * 
     * @throws InterruptedException
     */
    @Test
    void testConsumerStops() throws InterruptedException {
        NumberQueue queue = new VoidQueue();
        Consumer consumer = new Consumer(queue);

        Thread workerThread = new Thread(consumer);
        workerThread.start();
        Thread.sleep(500);
        consumer.setShouldStop();
        workerThread.join();

        // VoidQueue always dequeues 1, so this should be positive
        assertTrue(consumer.getTotalOfWork() > 0);
    }

    /**
     * Tests whether a consumer stops when asked to do so.
     * 
     * @throws InterruptedException
     */
    @Test
    void testProducerStops() throws InterruptedException {
        NumberQueue queue = new VoidQueue();
        Producer producer = new Producer(queue);

        Thread workerThread = new Thread(producer);
        workerThread.start();
        Thread.sleep(500);
        producer.setShouldStop();
        workerThread.join();

        assertTrue(true);
    }

    /**
     * Tests whether a consumer stops when asked to do so.
     * 
     * @throws InterruptedException
     */
    @Test
    void testProducerErrors() throws InterruptedException {
        NumberQueue queue = new CyclicQueue(100);
        Producer producer = new Producer(queue);

        Thread workerThread = new Thread(producer);
        workerThread.start();
        Thread.sleep(500);
        producer.setShouldStop();
        workerThread.join();

        assertTrue(producer.getErrored());
    }
}