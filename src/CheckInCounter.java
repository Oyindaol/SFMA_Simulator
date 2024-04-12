import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

/**
 * @author Oyindamola Taiwo-Olupeka 101155729
 * @version 2.0
 *
 * Simulates the check-in process for passengers, divided between business class and coach queues.
 * Generates service times for activities such as printing boarding passes and checking bags, using an exponential
 * distribution to simulate the variability of service times.
 */
public class CheckInCounter {
    Queue<Passenger> businessClassQueue;
    Queue<Passenger> coachQueue;
    private Random random;
    private double totalCheckInTime = 0;
    private int checkInPassengerCount = 0;

    private int numBusinessCounters; // Adjusted to be set via constructor
    private int numCoachCounters; // Adjusted to be set via constructor
    final double serviceTimePrintBoardingPass = 2.0; // Minutes
    final double serviceTimeCheckBag = 1.0; // Minutes per bag


    /**
     * CheckInCounter class constructor.
     * Initializes the queues for both business class and coach.
     */
    public CheckInCounter(int numBusinessCounters, int numCoachCounters) {
        businessClassQueue = new LinkedList<>();
        coachQueue = new LinkedList<>();
        random = new Random();
        this.numBusinessCounters = numBusinessCounters;
        this.numCoachCounters = numCoachCounters;
    }

    /**
     * Simulates service time for a single activity (boarding pass, bag check, delays).
     * @param avgServiceTime Average service time for the activity
     * @return Service time in minutes
     */
    double generateServiceTime(double avgServiceTime) {
        return -Math.log(random.nextDouble()) * avgServiceTime;
    }

    public double getAverageCheckInTime() {
        return checkInPassengerCount > 0 ? totalCheckInTime / checkInPassengerCount : 0;
    }

    /**
     * Processes the passenger by dividing the queues to available counters.
     * @param passenger
     */
    public void processPassenger(Passenger passenger) {
        Queue<Passenger> queue;
        int availableCounters;

        if (passenger.isBusinessClass()) {
            queue = businessClassQueue;
            availableCounters = numBusinessCounters;
        } else {
            queue = coachQueue;
            availableCounters = numCoachCounters;
        }

        // Check for available counters and create overflow queues if necessary
        if (queue.size() >= availableCounters) {
            if (passenger.isBusinessClass()) {
                System.out.println("Business Class queue full, creating overflow queue.");
                System.out.println();
                queue = new LinkedList<>(); // Create temporary overflow queue for business class
            } else {
                System.out.println("Coach queue full, creating overflow queue.");
                System.out.println();
                queue = new LinkedList<>(); // Create temporary overflow queue for coach
            }
        }

        queue.offer(passenger);

        // Simulate service times
        double totalServiceTime = 0;
        totalServiceTime += generateServiceTime(serviceTimePrintBoardingPass);
        int numBags = passenger.getNumberOfBags();
        totalServiceTime += numBags * generateServiceTime(serviceTimeCheckBag);
        totalServiceTime += generateServiceTime(3.0); // Other problems and delays
        String classType = passenger.isBusinessClass() ? "Business" : "Coach";

        totalCheckInTime += totalServiceTime; // Add the service time for the current passenger
        checkInPassengerCount++; // Increment the count of processed passengers

        System.out.println("Passenger " + passenger.getId() + " (" + (passenger.isCommuter() ? "Commuter" : "Provincial") + "):");
        System.out.println("Passenger " + passenger.getId() + " (" + classType + "): Processing at Check-In");
        //System.out.println("  - Arrival Time at Airport: " + String.format("%.2f",passenger.getArrivalTime()) + " minutes");
        System.out.println("  - Number of Bags: " + numBags);
        System.out.println("  - Check in Time: " + String.format("%.2f", totalServiceTime) + " minutes");

    }

}
