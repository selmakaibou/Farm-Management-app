public class WaterSensor extends NumericSensor {
    protected double temperature;
    protected double disolvedOxygen;

    public WaterSensor(String code, Zone zone) {
        this.code = code;
        this.zone = zone;
        this.status = SensorStatus.ACTIVE;
        this.timestamp = System.currentTimeMillis() / 1000.0; // Initialize timestamp to current time
    }

    @Override
    public void display() {
        System.out.println("Water Sensor Code: " + code);
        System.out.println("Status: " + status);
        System.out.println("Zone: " + zone.getName());
        System.out.println("Last Update: " + timestamp);
        if (status == SensorStatus.ACTIVE) {
            System.out.println("Current Temperature: " + temperature);
            System.out.println("Current Disolved Oxygen: " + disolvedOxygen);
        } else {
            System.out.println("Sensor data is unavailable. Sensor is not active.");
        }
    }

    @Override
    public void sendReading(double temperature, double disolvedOxygen) {
        if (this.status == SensorStatus.ACTIVE) {
            this.temperature = temperature;
            this.disolvedOxygen = disolvedOxygen;
            this.timestamp = System.currentTimeMillis() / 1000.0; // Update timestamp to current time
            System.out
                    .println("Water readings sent: Temperature " + temperature + ", Disolved Oxygen " + disolvedOxygen);
        } else {
            System.out.println("Cannot send water readings. Sensor is not active.");
        }
    }

}
