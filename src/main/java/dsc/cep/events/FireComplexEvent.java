package dsc.cep.events;

import java.util.Date;


public class FireComplexEvent {
	
	private String sensor;
	private boolean smoke;
	private double temperature;
	private Date timeStamp;
	
	public FireComplexEvent() {
		
	}
	
	public FireComplexEvent(String sensor, boolean smoke, double temperature, Date timeStamp) {
		this.sensor = sensor;
		this.smoke = smoke;
		this.temperature = temperature;
		this.timeStamp = timeStamp;
	}
	
	public String getSensor() {
		return sensor;
	}
	public void setSensor(String sensor) {
		this.sensor = sensor;
	}
	public boolean getSmoke() {
		return smoke;
	}
	public void setSmoke(boolean smoke) {
		this.smoke = smoke;
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
