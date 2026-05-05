public class EnvironmentalSensor extends NumericSensor {
    private double temperature;
    private double humidity;
    private double rainfall;

    public double getTemperature() {
        return temperature;
    }

    public double getHumidity() {
        return humidity;
    }

    public double getRainfall() {
        return rainfall;
    }

    public EnvironmentalSensor(String code, Zone zone) {
        this.code = code;
        this.zone = zone;
        this.status = SensorStatus.ACTIVE;
        this.timestamp = System.currentTimeMillis() / 1000.0; // Initialize timestamp to current time
    }

    @Override
    public void display() {
        System.out.println("Environmental Sensor Code: " + code);
        System.out.println("Status: " + status);
        System.out.println("Zone: " + zone.getName());
        System.out.println("Last Update: " + timestamp);
        if (status == SensorStatus.ACTIVE) {
            System.out.println("Current Temperature: " + temperature);
            System.out.println("Current Humidity: " + humidity);
            System.out.println("Current Rainfall: " + rainfall);
        } else {
            System.out.println("Sensor data is unavailable. Sensor is not active.");
        }
    }

    @Override
    public void sendReading(double temperature, double humidity, double rainfall) {
        if (this.status == SensorStatus.ACTIVE) {
            this.temperature = temperature;
            this.humidity = humidity;
            this.rainfall = rainfall;
            this.timestamp = System.currentTimeMillis() / 1000.0; // Update timestamp to current time
            System.out.println("Environmental readings sent: Temperature " + temperature + ", Humidity " + humidity
                    + ", Rainfall " + rainfall);
        } else {
            System.out.println("Cannot send environmental readings. Sensor is not active.");
        }
    }

}