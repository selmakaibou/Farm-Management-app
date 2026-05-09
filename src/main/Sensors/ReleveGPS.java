public class ReleveGPS extends ReleveSensor {
    protected GPS gpsCollar;
    protected final double MIN_LATITUDE = -90.0;
    protected final double MAX_LATITUDE = 90.0;
    protected final double MIN_LONGITUDE = -180.0;
    protected final double MAX_LONGITUDE = 180.0;

    public ReleveGPS(GPS gpsCollar) {
        this.gpsCollar = gpsCollar;
    }

    public boolean isWithinRange(double latitude, double longitude) {
        if (latitude < MIN_LATITUDE || latitude > MAX_LATITUDE || longitude < MIN_LONGITUDE
                || longitude > MAX_LONGITUDE) {
            System.out.println("Animal is out of range.");
            return false;
        } else {
            System.out.println("Animal is within range.");
            return true;
        }
    }

}
