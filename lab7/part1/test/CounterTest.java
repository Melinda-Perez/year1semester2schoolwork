import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

/**
 * Tests for the counter and Gate classes.
 */
class CounterTest {
    /**
     * Tests whether the counter has counted every guest with a single gate.
     * 
     * @throws InterruptedException
     */
    @Test
    void testOneGate() throws InterruptedException {
        Counter counter = new Counter();
        var numberOfGuests = 100000;
        Gate gate = new Gate(counter, numberOfGuests);
        gate.run();
        assertEquals(numberOfGuests, counter.getCount(), "Wrong number of guests counted with a single Gate.");
    }

    /**
     * Tests whether the counter has counted every guest with two gates.
     * 
     * @throws InterruptedException
     */
    @Test
    void testTwoGates() throws InterruptedException {
        Counter counter = new Counter();
        int numberGates = 2;
        int numberGuestsPerGate = 50000;
        List<Gate> allGates = new ArrayList<>();
        List<Thread> allGateThreads = new ArrayList<>();
        for (int i = 0; i < numberGates; i++) {
            allGates.add(new Gate(counter, numberGuestsPerGate));
            Thread gateThread = new Thread(allGates.get(i));
            allGateThreads.add(gateThread);
            gateThread.start();
        }
        for (int i = 0; i < numberGates; i++) {
            allGateThreads.get(i).join();
        }
        assertEquals(numberGates * numberGuestsPerGate, counter.getCount(), "Wrong number of guests counted with multiple gates.");
    }

    /**
     * Tests whether the counter has counted every guest with a large number of gates and guests.
     * 
     * @throws InterruptedException
     */
    @Test
    void testManyGates() throws InterruptedException {
        Counter counter = new Counter();
        int numberGates = 20;
        int numberGuestsPerGate = 15000;
        List<Gate> allGates = new ArrayList<>();
        List<Thread> allGateThreads = new ArrayList<>();
        for (int i = 0; i < numberGates; i++) {
            allGates.add(new Gate(counter, numberGuestsPerGate));
            Thread gateThread = new Thread(allGates.get(i));
            allGateThreads.add(gateThread);
            gateThread.start();
        }
        for (int i = 0; i < numberGates; i++) {
            allGateThreads.get(i).join();
        }
        assertEquals(numberGates * numberGuestsPerGate, counter.getCount(), "Wrong number of guests counted with multiple gates.");
    }
}