public class BiometricSensor extends NumericSensor {
    private double bodyTemperature;
    private double activityLevel;
    private int stepsPerMinute;
    private GPS gpsCollar;

    public BiometricSensor(String code, Zone zone) {
        this.code = code;
        this.zone = zone;
        this.status = SensorStatus.ACTIVE;
        this.gpsCollar = new GPS(code + "-GPS", zone);
    }

    @Override
    public void display() {
        System.out.println("Biometric Sensor Code: " + code);
        System.out.println("Status: " + status);
        System.out.println("Zone: " + zone.getName());
        if (status == SensorStatus.ACTIVE) {
            System.out.println("Current Body Temperature: " + bodyTemperature);
            System.out.println("Current Activity Level: " + activityLevel);
            System.out.println("Current Steps Per Minute: " + stepsPerMinute);
            gpsCollar.display();
        } else {
            System.out.println("Sensor data is unavailable. Sensor is not active.");
        }
    }

    @Override
    public void sendReading(double bodyTemperature, double activityLevel, int stepsPerMinute, double latitude,
            double longitude) {
        if (this.status == SensorStatus.ACTIVE) {
            this.bodyTemperature = bodyTemperature;
            this.activityLevel = activityLevel;
            this.stepsPerMinute = stepsPerMinute;
            gpsCollar.sendLocation(latitude, longitude);
            System.out.println("Biometric readings sent: Body Temperature " + bodyTemperature + ", Activity Level "
                    + activityLevel + ", Steps Per Minute " + stepsPerMinute);
        } else {
            System.out.println("Cannot send biometric readings. Sensor is not active.");
        }
    }

}
