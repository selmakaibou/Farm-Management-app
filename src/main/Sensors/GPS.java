public class GPS extends Sensor {
    private double latitude;
    private double longitude;
    private double timestamp;

    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public GPS(String code, Zone zone) {
        this.code = code;
        this.zone = zone;
        this.status = SensorStatus.ACTIVE;
        this.timestamp = System.currentTimeMillis() / 1000.0; // Initialize timestamp to current time

    }

    public void sendLocation(double latitude, double longitude) {
        if (this.status == SensorStatus.ACTIVE) {
            this.latitude = latitude;
            this.longitude = longitude;
            this.timestamp = System.currentTimeMillis() / 1000.0; // Update timestamp to current time
            System.out.println("Location sent: Latitude " + latitude + ", Longitude " + longitude);
        } else {
            System.out.println("Cannot send location. Sensor is not active.");
        }
    }

    public void display() {
        System.out.println("GPS Sensor Code: " + code);
        System.out.println("Status: " + status);
        System.out.println("Zone: " + zone.getName());
        System.out.println("Last Update: " + timestamp);
        if (status == SensorStatus.ACTIVE) {
            System.out.println("Current Location: Latitude " + latitude + ", Longitude " + longitude);
        } else {
            System.out.println("Location data is unavailable. Sensor is not active.");
        }
    }
}
