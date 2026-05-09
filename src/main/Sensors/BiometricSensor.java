public class BiometricSensor extends NumericSensor {
    protected double bodyTemperature;
    protected double activityLevel;
    protected int stepsPerMinute;
    protected GPS gpsCollar;

    public double getBodyTemperature() {
        return bodyTemperature;
    }

    public double getActivityLevel() {
        return activityLevel;
    }

    public int getStepsPerMinute() {
        return stepsPerMinute;
    }

    public GPS getGpsCollar() {
        return gpsCollar;
    }

    public BiometricSensor(String code, Zone zone) {
        this.code = code;
        this.zone = zone;
        this.status = SensorStatus.ACTIVE;
        this.gpsCollar = new GPS(code + "-GPS", zone);
        this.timestamp = System.currentTimeMillis() / 1000.0; // Initialize timestamp to current time
    }

    @Override
    public void display() {
        System.out.println("Biometric Sensor Code: " + code);
        System.out.println("Status: " + status);
        System.out.println("Zone: " + zone.getName());
        System.out.println("Last Update: " + timestamp);
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
            this.timestamp = System.currentTimeMillis() / 1000.0; // Update timestamp to current time
            System.out.println("Biometric readings sent: Body Temperature " + bodyTemperature + ", Activity Level "
                    + activityLevel + ", Steps Per Minute " + stepsPerMinute);
        } else {
            System.out.println("Cannot send biometric readings. Sensor is not active.");
        }
    }

}
