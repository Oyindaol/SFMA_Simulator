import java.util.Random;

/**
 * @author Oyindamola Taiwo-Olupeka 101155729
 * @version 1.0
 */
public class PassengerArrival {
    private Random random;

    // Arrival time constants
    private final double minutesInHour = 60;
    private final double commuterArrivalInterval = minutesInHour;
    private final double provincialArrivalInterval = 6 * minutesInHour;


    // Arrival rates
    private final double commuterArrivalRate = 40.0 / 60.0; // 40 people per hour
    private final double provincialArrivalRate = 1.0 / 6.0; // Every 6 hours


    // Mean and variance for provincial passenger arrival time
    private final double provincialMeanArrivalTime = 75;
    private final double provincialVarianceArrivalTime = 50;


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
        return currentTime % commuterArrivalInterval == 0;
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
     * Generates the next passenger arrival time if commuter or provincial.
     * @param isCommuter
     * @return exponentialRandom(commuterArrivalRate), if commuter
     * @return Math.max(normalRandom, 0), if provincial
     */
    public double generateNextArrivalTime(boolean isCommuter) {
        if (isCommuter) {
            return exponentialRandom(commuterArrivalRate);
        } else {
            double normalRandom = normalRandom(provincialMeanArrivalTime, provincialVarianceArrivalTime);
            return Math.max(normalRandom, 0);
        }
    }

    public static void main(String[] args) {
        PassengerArrival arrivalModule = new PassengerArrival();
        boolean isCommuter = true;

        for (int i = 0; i < 10; i++) {
            double arrivalTime = arrivalModule.generateNextArrivalTime(isCommuter);
            System.out.println("Passenger " + (i + 1) + " arrival time: " + arrivalTime);
        }
    }

}
