import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PassengerTest {

    private Passenger passenger;

    @BeforeEach
    void setUp() {
        // Initialize a Passenger instance before each test
        // Assuming the `currentTime` and `arrivalTime` for simplicity
        double currentTime = 100; // Example current time
        double arrivalTime = 120; // Example arrival time, not directly used in current constructor logic
        boolean isCommuter = true;
        boolean isBusinessClass = false;
        int numBags = 2;

        passenger = new Passenger(currentTime, arrivalTime, isCommuter, isBusinessClass, numBags);
    }

    @Test
    void testPassengerIDIsSet() {
        assertTrue(passenger.getId() > 0, "Passenger ID should be greater than 0.");
    }

    @Test
    void testArrivalTimeIsGenerated() {
        assertTrue(passenger.getArrivalTime() > 0, "Arrival time should be set and greater than 0.");
    }

    @Test
    void testCheckInTimeIsSet() {
        assertEquals(120, passenger.getCheckInTime(), "Check-in time should match the input.");
    }

    @Test
    void testCommuterStatusIsSet() {
        assertTrue(passenger.isCommuter(), "Passenger should be marked as commuter.");
    }

    @Test
    void testBusinessClassStatusIsSet() {
        assertFalse(passenger.isBusinessClass(), "Passenger should not be in business class.");
    }

    @Test
    void testNumberOfBagsIsSet() {
        assertEquals(2, passenger.getNumberOfBags(), "Passenger should have 2 bags.");
    }

    @Test
    void testFlightDepartureTimeCanBeSet() {
        passenger.setFlightDepartureTime(200);
        assertEquals(200, passenger.getFlightDepartureTime(), "Flight departure time should be set to 200.");
    }
}
