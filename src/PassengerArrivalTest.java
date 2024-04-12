import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class PassengerArrivalTest {

    private PassengerArrival passengerArrival;

    @BeforeEach
    void setUp() {
        passengerArrival = new PassengerArrival();
    }

    @Test
    void testIsCommuter() {
        boolean commuter = false;
        boolean provincial = false;

        for (int i = 0; i < 1000; i++) {
            if (passengerArrival.isCommuter(0)) {
                commuter = true;
            } else {
                provincial = true;
            }
        }

        assertTrue(commuter && provincial, "Both commuter and provincial should be generated over multiple calls.");
    }

    @Test
    void testIsProvincialBusinessClass() {
        boolean foundTrue = false;
        boolean foundFalse = false;

        for (int i = 0; i < 1000; i++) {
            if (passengerArrival.isProvincialBusinessClass()) {
                foundTrue = true;
            } else {
                foundFalse = true;
            }

            if (foundTrue && foundFalse) break;
        }

        assertTrue(foundTrue && foundFalse, "isProvincialBusinessClass should return both true and false over multiple invocations.");
    }

    @Test
    void testGenerateNextArrivalTime() {
        double commuterTime = passengerArrival.generateNextArrivalTime(true);
        double provincialTime = passengerArrival.generateNextArrivalTime(false);

        assertTrue(commuterTime > 0, "Generated commuter arrival time should be positive.");
        assertTrue(provincialTime >= passengerArrival.minimumProvincialArrivalTime * passengerArrival.minutesInHour, "Generated provincial arrival time should meet the minimum threshold.");
    }

    @Test
    void testGenerateNumberOfBags() {
        int commuterBags = passengerArrival.generateNumberOfBags(true);
        int provincialBags = passengerArrival.generateNumberOfBags(false);

        assertTrue(commuterBags >= 0, "Commuter should have a non-negative number of bags.");
        assertTrue(provincialBags >= 0, "Provincial should have a non-negative number of bags.");
    }

}

