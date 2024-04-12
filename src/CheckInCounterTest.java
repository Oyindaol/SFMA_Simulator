import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

public class CheckInCounterTest {

    private CheckInCounter checkInCounter;
    private Passenger passenger;
    private Random rand;

    @BeforeEach
    public void setUp() {
        checkInCounter = new CheckInCounter(2, 3); // Example: 2 business counters, 3 coach counters
        passenger = new Passenger(0,0, true, true, 2); // Example passenger (commuter, business class, 2 bags)
        rand = new Random(100); // Set seed for predictable random values in tests
    }

    @Test
    public void testProcessPassenger() {
        checkInCounter.processPassenger(passenger);

        // Assertions for processing a business class passenger with available counters
        assertTrue(checkInCounter.businessClassQueue.contains(passenger));
        assertEquals(1, checkInCounter.businessClassQueue.size());
    }

    @Test
    public void testGenerateServiceTime() {
        double generatedServiceTime = checkInCounter.generateServiceTime(2.0);

        assertTrue(generatedServiceTime > 0); // Ensure positive service time
    }

    @Test
    void testQueueOverflowHandling() {
        // Populate the business class queue to trigger overflow handling
        for (int i = 0; i < 5; i++) { // Assuming a small number triggers overflow for testing
            checkInCounter.processPassenger(passenger);
        }
    }

}

