package dsc.cep.events;

public class HighTemperatureEvent {

	private String sensor;
	private Double temperature;

	public HighTemperatureEvent(String sensor, Double temperature) {
		this.sensor = sensor;
		this.temperature = temperature;
	}

	public String getSensor() {
		return sensor;
	}

	public void setSensor(String sensor) {
		this.sensor = sensor;
	}

	public Double getTemperature() {
		return temperature;
	}

	public void setTemperature(Double temperature) {
		this.temperature = temperature;
	}

}
