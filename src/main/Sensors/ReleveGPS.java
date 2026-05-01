public class ReleveGPS extends ReleveSensor {
    private GPS gpsCollar;
    private final double MIN_LATITUDE = -90.0;
    private final double MAX_LATITUDE = 90.0;
    private final double MIN_LONGITUDE = -180.0;
    private final double MAX_LONGITUDE = 180.0;

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
