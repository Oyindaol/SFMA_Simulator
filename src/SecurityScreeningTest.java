import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class SecurityScreeningTest {

    private SecurityScreening securityScreening;

    @BeforeEach
    void setUp() {
        securityScreening = new SecurityScreening();
    }

    @Test
    void testProcessPassenger() {
        Passenger businessPassenger = new Passenger(0, 0, true, true, 1);
        Passenger coachPassenger = new Passenger(0, 0, false, false, 2);

        securityScreening.processPassenger(businessPassenger);
        securityScreening.processPassenger(coachPassenger);

        // Assuming methods to retrieve queue sizes for validation. If not available, consider adding or using reflection.
        assertEquals(0, securityScreening.getBusinessClassQueue().size());
        assertEquals(0, securityScreening.getCoachQueue().size());
    }

    @Test
    void testGenerateScreeningTime() {
        double serviceTime = securityScreening.generateScreeningTime();
        assertTrue(serviceTime > 0, "Service time should be positive.");
    }

    @Test
    void testFullSecurityScreeningProcess() {
        // Adding multiple passengers to simulate a queue
        for (int i = 0; i < 3; i++) {
            Passenger coachPassenger = new Passenger(0, 0, false, false, 2);
            securityScreening.processPassenger(coachPassenger);
        }

    }
}
