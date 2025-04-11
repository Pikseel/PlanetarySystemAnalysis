/**
 * Represents sensor data for a celestial body with validation.
 */
public class SensorData {
    private double temperature; // in Kelvin
    private double pressure;    // in Pascals
    private double humidity;    // percentage (0-100)
    private double radiation;   // in Sieverts

    /**
     * Constructs SensorData with validation.
     * @param temperature in Kelvin
     * @param pressure in Pascals
     * @param humidity percentage (0-100)
     * @param radiation in Sieverts
     * @throws IllegalArgumentException if data is invalid
     */
    public SensorData(double temperature, double pressure, double humidity, double radiation) {
        if (temperature < 0 || pressure < 0 || humidity < 0 || humidity > 100 || radiation < 0) {
            throw new IllegalArgumentException("Invalid sensor data: values must be non-negative, humidity 0-100.");
        }
        this.temperature = temperature;
        this.pressure = pressure;
        this.humidity = humidity;
        this.radiation = radiation;
    }

    /**
     * Gets temperature.
     * @return temperature in Kelvin
     */
    public double getTemperature() { return temperature; }

    /**
     * Gets pressure.
     * @return pressure in Pascals
     */
    public double getPressure() { return pressure; }

    /**
     * Gets humidity.
     * @return humidity percentage
     */
    public double getHumidity() { return humidity; }

    /**
     * Gets radiation.
     * @return radiation in Sieverts
     */
    public double getRadiation() { return radiation; }
}