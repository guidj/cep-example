package dsc.cep.events;

import java.util.Date;

public class TemperatureSensorEvent {

	private String sensor;
	private double temperature;
	private Date timeStamp;

	public TemperatureSensorEvent() {

	}

	public TemperatureSensorEvent(String sensor, double temperature,
			Date timeStamp) {
		this.sensor = sensor;
		this.temperature = temperature;
		this.timeStamp = timeStamp;
	}

	public String getSensor() {
		return sensor;
	}

	public void setSensor(String sensor) {
		this.sensor = sensor;
	}

	public double getTemperature() {
		return temperature;
	}

	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}

	public Date getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}

}
