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
    public boolean isValidValue(String measurement, double value) {
        if (sensor == null) {
            System.out.println("Anomaly detected: no sensor attached to this reading.");
            return false;
        }

        boolean isValid;
        String sensorName = sensor.getClass().getSimpleName();

        if (sensor instanceof BiometricSensor) {
            isValid = validateBiometricMeasurement(measurement, value);
        } else if (sensor instanceof WaterSensor) {
            isValid = validateWaterMeasurement(measurement, value);
        } else if (sensor instanceof EnvironmentalSensor) {
            isValid = validateEnvironmentalMeasurement(measurement, value);
        } else if (sensor instanceof SoilSensor) {
            isValid = validateSoilMeasurement(measurement, value);
        } else {
            System.out.println("Anomaly detected: unknown numeric sensor type.");
            return false;
        }

        if (!isValid) {
            System.out.println("Invalid reading for " + sensorName + " [" + measurement + "]: " + value);
        }

        return isValid;
    }

    private boolean validateBiometricMeasurement(String measurement, double value) {
        if (measurement == null) {
            System.out.println("Measurement name is required for biometric sensor validation.");
            return false;
        }

        if (measurement.equalsIgnoreCase("bodyTemperature")) {
            return checkRange("Body temperature", value, MIN_BODY_TEMPERATURE, MAX_BODY_TEMPERATURE);
        }
        if (measurement.equalsIgnoreCase("activityLevel")) {
            return checkRange("Activity level", value, MIN_ACTIVITY_LEVEL, MAX_ACTIVITY_LEVEL);
        }
        if (measurement.equalsIgnoreCase("stepsPerMinute")) {
            return checkRange("Steps per minute", value, MIN_STEPS_PER_MINUTE, MAX_STEPS_PER_MINUTE);
        }

        System.out.println("Unknown biometric measurement: " + measurement);
        return false;
    }

    private boolean validateWaterMeasurement(String measurement, double value) {
        if (measurement == null) {
            System.out.println("Measurement name is required for water sensor validation.");
            return false;
        }

        if (measurement.equalsIgnoreCase("temperature")) {
            return checkRange("Water temperature", value, MIN_WATER_TEMPERATURE, MAX_WATER_TEMPERATURE);
        }
        if (measurement.equalsIgnoreCase("dissolvedOxygen")) {
            return checkRange("Dissolved oxygen", value, MIN_DISSOLVED_OXYGEN, MAX_DISSOLVED_OXYGEN);
        }

        System.out.println("Unknown water sensor measurement: " + measurement);
        return false;
    }

    private boolean validateEnvironmentalMeasurement(String measurement, double value) {
        if (measurement == null) {
            System.out.println("Measurement name is required for environmental sensor validation.");
            return false;
        }

        if (measurement.equalsIgnoreCase("temperature")) {
            return checkRange("Weather temperature", value, MIN_WEATHER_TEMPERATURE, MAX_WEATHER_TEMPERATURE);
        }
        if (measurement.equalsIgnoreCase("humidity")) {
            return checkRange("Humidity", value, MIN_HUMIDITY, MAX_HUMIDITY);
        }
        if (measurement.equalsIgnoreCase("rainfall")) {
            return checkRange("Rainfall", value, MIN_RAINFALL, MAX_RAINFALL);
        }

        System.out.println("Unknown environmental measurement: " + measurement);
        return false;
    }

    private boolean validateSoilMeasurement(String measurement, double value) {
        if (measurement == null) {
            System.out.println("Measurement name is required for soil sensor validation.");
            return false;
        }

        if (measurement.equalsIgnoreCase("PH")) {
            return checkRange("Soil pH", value, MIN_PH, MAX_PH);
        }
        if (measurement.equalsIgnoreCase("moistureLevel")) {
            return checkRange("Moisture level", value, MIN_MOISTURE_LEVEL, MAX_MOISTURE_LEVEL);
        }
        if (measurement.equalsIgnoreCase("nitrogenContent")) {
            return checkRange("Nitrogen content", value, MIN_NITROGEN_CONTENT, MAX_NITROGEN_CONTENT);
        }

        System.out.println("Unknown soil measurement: " + measurement);
        return false;
    }

    private boolean checkRange(String name, double value, double min, double max) {
        if (value < min) {
            System.out.println(name + " is too low: " + value + " < " + min);
            return false;
        }
        if (value > max) {
            System.out.println(name + " is too high: " + value + " > " + max);
            return false;
        }
        return true;
    }

}
