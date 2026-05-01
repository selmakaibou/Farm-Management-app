public class GPS extends Sensor {
    private double latitude;
    private double longitude;

    public GPS(String code, Zone zone) {
        this.code = code;
        this.zone = zone;
        this.status = SensorStatus.ACTIVE;

    }

    public void sendLocation(double latitude, double longitude) {
        if (this.status == SensorStatus.ACTIVE) {
            this.latitude = latitude;
            this.longitude = longitude;
            System.out.println("Location sent: Latitude " + latitude + ", Longitude " + longitude);
        } else {
            System.out.println("Cannot send location. Sensor is not active.");
        }
    }

    public void display() {
        System.out.println("GPS Sensor Code: " + code);
        System.out.println("Status: " + status);
        System.out.println("Zone: " + zone.getName());
        if (status == SensorStatus.ACTIVE) {
            System.out.println("Current Location: Latitude " + latitude + ", Longitude " + longitude);
        } else {
            System.out.println("Location data is unavailable. Sensor is not active.");
        }
    }
}
