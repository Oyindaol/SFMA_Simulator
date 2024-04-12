import java.util.Random;

/**
 * @author Oyindamola Taiwo-Olupeka 101155729
 * @version 2.0
 *
 * Generates the arrival time for passengers using either an exponential distribution (for commuter passengers) or a
 * normal distribution (for provincial passengers), ensuring realistic variability in arrival times.
 * Determines the classification of passengers (commuter or provincial) and whether they are in business class, with a
 * higher probability set for provincial passengers being in business class.
 */
public class PassengerArrival {
    private static Random random;

    // Arrival time constants
    final double minutesInHour = 60;
    private final double commuterArrivalInterval = minutesInHour;
    private final double provincialArrivalInterval = 6 * minutesInHour;


    // Arrival rates
    private final double commuterArrivalRate = 40.0 / 60.0; // 40 people per hour
    private final double provincialArrivalRate = 1.0 / 6.0; // Every 6 hours


    // Mean and variance for provincial passenger arrival time
    private final double provincialMeanArrivalTime = 75;
    private final double provincialVarianceArrivalTime = 50;
    final double minimumProvincialArrivalTime = 90; // Minimum minutes before flight



    /**
     * PassengerArrival class constructor.
     */
    public PassengerArrival() {
        random = new Random();
    }

    /**
     * Returns if passenger is commuter with their current time.
     * @param currentTime
     * @return currentTime % commuterArrivalInterval == 0
     */
    public boolean isCommuter(double currentTime) {
        //return currentTime % commuterArrivalInterval == 0;
        return random.nextBoolean();
    }

    /**
     * Returns if passenger is provincial with their current time.
     * @param currentTime
     * @return currentTime % provincialArrivalInterval == 0
     */
    public boolean isProvincial(double currentTime) {
        return currentTime % provincialArrivalInterval == 0;
    }

    /**
     * Decides if a commuter passenger is traveling in business class.
     * This example method assigns a lower probability for commuters to be in business class.
     *
     * @return true if the passenger is in business class, false otherwise.
     */
    public boolean isCommuterBusinessClass() {
        // Example: 10% chance for a commuter passenger to be in business class
        //return random.nextDouble() < 0.1;
        return false;
    }

    /**
     * Decides if a provincial passenger is traveling in business class.
     * This example method assigns a higher probability for provincials to be in business class.
     *
     * @return true if the passenger is in business class, false otherwise.
     */
    public boolean isProvincialBusinessClass() {
        // Example: 30% chance for a provincial passenger to be in business class
        //return random.nextDouble() < 0.3;
        return random.nextDouble() < 0.75;
    }


    /**
     * Math function to return random exponential value.
     * @param rate
     * @return -Math.log(1 - random.nextDouble()) / rate
     */
    private double exponentialRandom(double rate) {
        return -Math.log(1 - random.nextDouble()) / rate;
    }

    /**
     * Math function to return random normal value.
     * @param mean
     * @param variance
     * @return mean + random.nextGaussian() * Math.sqrt(variance)
     */
    private double normalRandom(double mean, double variance) {
        return mean + random.nextGaussian() * Math.sqrt(variance);
    }

    /**
     * Generates the next passenger arrival time based on Poisson distribution for commuter and normal distribution for provincial.
     * Ensures no duplicate arrival times (adjusted slightly for clarity).
     *
     * @param isCommuter True if commuter, False if provincial
     * @return Next passenger arrival time in minutes
     */
    public double generateNextArrivalTime(boolean isCommuter) {
        double arrivalTime;
        if (isCommuter) {
            // Poisson distribution for commuter arrivals
            arrivalTime = exponentialRandom(commuterArrivalRate);
        } else {
            // Provincial - normal distribution with minimum arrival time
            do {
                arrivalTime = Math.max(normalRandom(provincialMeanArrivalTime, provincialVarianceArrivalTime), minimumProvincialArrivalTime);
            } while (arrivalTime <= 0); // Ensure arrival time isn't 0 (highly unlikely but possible)
        }
        return arrivalTime * minutesInHour; // Convert to minutes
    }

    /**
     * Generates the number of bags a passenger carries using geometric distribution.
     * Assigns the success bias based on the passenger type (commuter or provincial).
     *
     * @param isCommuter True if commuter, False if provincial
     * @return Number of bags (0 or positive integer)
     */
    public int generateNumberOfBags(boolean isCommuter) {
        double successBias;
        if (isCommuter) {
            successBias = 0.6; // 60% success bias for commuter passengers
        } else {
            successBias = 0.8; // 80% success bias for provincial passengers
        }
        int numBags = 0;
        while (random.nextDouble() > successBias) {
            numBags++;
        }
        return numBags;
    }
}
