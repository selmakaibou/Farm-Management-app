public abstract class Sensor implements Desactivate {
    protected String code;
    protected SensorStatus status;
    protected Zone zone;
    protected double timestamp;

    public abstract void display();

    public abstract void sendReading(double... values);

    @Override
    public void setStatus(SensorStatus status) {
        this.status = status;
    }

    @Override
    public void activate() {
        this.status = SensorStatus.ACTIVE;
        System.out.println("Sensor " + code + " activated.");
    }

    @Override
    public void desactivate() {
        this.status = SensorStatus.FAULTY;
        System.out.println("Sensor " + code + " deactivated.");
    }

    public enum SensorStatus {
        ACTIVE, FAULTY, SUSPECTED
    }
}
