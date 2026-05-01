public class SoilSensor extends NumericSensor {
    private double PH;
    private double moistureLevel;
    private double nitrogenContent;

    public SoilSensor(String code, Zone zone) {
        this.code = code;
        this.zone = zone;
        this.status = SensorStatus.ACTIVE;
    }

    public void display() {
        System.out.println("Soil Sensor Code: " + code);
        System.out.println("Status: " + status);
        System.out.println("Zone: " + zone.getName());
        if (status == SensorStatus.ACTIVE) {
            System.out.println("Current PH: " + PH);
            System.out.println("Current Moisture Level: " + moistureLevel);
            System.out.println("Current Nitrogen Content: " + nitrogenContent);
        } else {
            System.out.println("Sensor data is unavailable. Sensor is not active.");
        }
    }

    public void sendReading(double PH, double moistureLevel, double nitrogenContent) {
        if (this.status == SensorStatus.ACTIVE) {
            this.PH = PH;
            this.moistureLevel = moistureLevel;
            this.nitrogenContent = nitrogenContent;
            System.out.println("Soil readings sent: PH " + PH + ", Moisture Level " + moistureLevel
                    + ", Nitrogen Content " + nitrogenContent);
        } else {
            System.out.println("Cannot send soil readings. Sensor is not active.");
        }
    }

}
