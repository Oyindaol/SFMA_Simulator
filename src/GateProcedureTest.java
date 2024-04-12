import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class GateProcedureTest {

    private GateProcedure gateProcedure;
    private final double initialFlightDepartureTime = 0.1;

    @BeforeEach
    void setUp() {
        gateProcedure = new GateProcedure(initialFlightDepartureTime);
    }

    @Test
    void testProcessPassenger() {
        Passenger commuterPassenger = new Passenger(500, 530, true, false, 1); // Arrival time allows making the flight
        gateProcedure.processPassenger(commuterPassenger);
        Passenger provincialPassenger = new Passenger(400, 410, false, false, 2); // Arrival time results in missing the flight
        gateProcedure.processPassenger(provincialPassenger);

    }

    @Test
    void testGetNextFlightTimeCommuter() {
        double commuterNextFlightTime = gateProcedure.getNextFlightTime(true, 90); // Assuming current time is 1:30 AM
        assertEquals(150, commuterNextFlightTime, "Next commuter flight time should be at 2:30 AM.");

        double provincialNextFlightTime = gateProcedure.getNextFlightTime(false, 360); // Assuming current time is 6:00 AM
        assertEquals(720, provincialNextFlightTime, "Next provincial flight time should be at 12:00 PM.");
    }


}
