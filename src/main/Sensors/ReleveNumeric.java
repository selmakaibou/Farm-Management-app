public class ReleveNumeric extends ReleveSensor {
    private NumericSensor sensor;
    private final double MIN_BODY_TEMPERATURE = 35.0;
    private final double MAX_BODY_TEMPERATURE = 42.0;
    private final double MIN_ACTIVITY_LEVEL = 0.0;
    private final double MAX_ACTIVITY_LEVEL = 10.0;
    private final int MIN_STEPS_PER_MINUTE = 0;
    private final int MAX_STEPS_PER_MINUTE = 200;
    private final double MIN_WATER_TEMPERATURE = 0.0;
    private final double MAX_WATER_TEMPERATURE = 35.0;
    private final double MIN_DISSOLVED_OXYGEN = 0.0;
    private final double MAX_DISSOLVED_OXYGEN = 14.0;
    private final double MIN_WEATHER_TEMPERATURE = -40.0;
    private final double MAX_WEATHER_TEMPERATURE = 60.0;
    private final double MIN_HUMIDITY = 0.0;
    private final double MAX_HUMIDITY = 100.0;
    private final double MIN_RAINFALL = 0.0;
    private final double MAX_RAINFALL = 500.0;
    private final double MIN_PH = 0.0;
    private final double MAX_PH = 14.0;
    private final double MIN_MOISTURE_LEVEL = 0.0;
    private final double MAX_MOISTURE_LEVEL = 100.0;
    private final double MIN_NITROGEN_CONTENT = 0.0;
    private final double MAX_NITROGEN_CONTENT = 100.0;

    public ReleveNumeric(NumericSensor sensor) {
        this.sensor = sensor;
    }

    // return a boolean indicating whether the value is valid for the sensor type
    public boolean isValidValue(double value) {
        if (sensor == null) {
            System.out.println("Anomaly detected: no sensor attached to this reading.");
            return false;
        }

        boolean isValid;
        String sensorName = sensor.getClass().getSimpleName();

        if (sensor instanceof BiometricSensor) {
            isValid = (value >= MIN_BODY_TEMPERATURE && value <= MAX_BODY_TEMPERATURE)
                    || (value >= MIN_ACTIVITY_LEVEL && value <= MAX_ACTIVITY_LEVEL)
                    || (value >= MIN_STEPS_PER_MINUTE && value <= MAX_STEPS_PER_MINUTE);
        } else if (sensor instanceof WaterSensor) {
            isValid = (value >= MIN_WATER_TEMPERATURE && value <= MAX_WATER_TEMPERATURE)
                    || (value >= MIN_DISSOLVED_OXYGEN && value <= MAX_DISSOLVED_OXYGEN);
        } else if (sensor instanceof EnvironmentalSensor) {
            isValid = (value >= MIN_WEATHER_TEMPERATURE && value <= MAX_WEATHER_TEMPERATURE)
                    || (value >= MIN_HUMIDITY && value <= MAX_HUMIDITY)
                    || (value >= MIN_RAINFALL && value <= MAX_RAINFALL);
        } else if (sensor instanceof SoilSensor) {
            isValid = (value >= MIN_PH && value <= MAX_PH)
                    || (value >= MIN_MOISTURE_LEVEL && value <= MAX_MOISTURE_LEVEL)
                    || (value >= MIN_NITROGEN_CONTENT && value <= MAX_NITROGEN_CONTENT);
        } else {
            System.out.println("Anomaly detected: unknown numeric sensor type.");
            return false;
        }

        if (!isValid) {
            System.out
                    .println("Anomaly detected for " + sensorName + ": value " + value + " is outside expected range.");
        }

        return isValid;
    }

}
