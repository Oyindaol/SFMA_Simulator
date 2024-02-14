/**
 * @author Oyindamola Taiwo-Olupeka 101155729
 * @version 1
 */
public class Passenger {
    private double arrivalTime;
    private double checkInTime;
    private boolean isCommuter;

    /**
     * Passenger class constructor.
     * Initializes a passenger that arrives at SFMA.
     *
     * @param currentTime
     * @param arrivalTime
     * @param isCommuter
     */
    public Passenger(double currentTime, double arrivalTime, boolean isCommuter) {
        this.arrivalTime = currentTime;
        this.checkInTime = arrivalTime;
        this.isCommuter = isCommuter;
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
        return isBusinessClass();
    }
}
