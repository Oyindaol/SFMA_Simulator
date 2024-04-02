/**
 * @author Oyindamola Taiwo-Olupeka 101155729
 * @version 2.0
 */
public class Passenger {
    private int id;
    private double arrivalTime;
    private double checkInTime;
    private boolean isCommuter;
    private boolean isBusinessClass;
    private int numBags;

    private static int passengerCount = 1; // Counter for assigning unique IDs


    /**
     * Passenger class constructor.
     * Initializes a passenger that arrives at SFMA.
     *
     * @param currentTime
     * @param arrivalTime
     * @param isCommuter
     * @param numBags
     */
    public Passenger(double currentTime, double arrivalTime, boolean isCommuter, boolean isBusinessClass, int numBags) {
        this.id = passengerCount++; // Assign unique ID
        this.arrivalTime = currentTime;
        this.checkInTime = arrivalTime;
        this.isCommuter = isCommuter;
        this.isBusinessClass = isBusinessClass;
        this.numBags = numBags;
    }

    /**
     * Returns Passenger ID.
     *
     * @return Passenger ID
     */
    public int getId() {
        return id;
    }

    /**
     * Returns Passenger arrival time.
     * @return arrivalTime
     */
    public double getArrivalTime() {
        return arrivalTime;
    }

    /**
     * Returns Passenger check-in time.
     * @return checkInTime
     */
    public double getCheckInTime() {
        return checkInTime;
    }

    /**
     * Returns if Passenger is a commuter or (provincial)
     * @return true, if commuter
     * @return false, if provincial
     */
    public boolean isCommuter() {
        return isCommuter;
    }

    /**
     * Returns if Passenger is business class or (coach)
     * @return true, if business class
     * @return false, if coach
     */
    public boolean isBusinessClass() {
        return isBusinessClass;
    }

    /**
     * Returns the number of bags the passenger carries.
     *
     * @return Number of bags
     */
    public int getNumberOfBags() {
        return numBags;
    }
}
