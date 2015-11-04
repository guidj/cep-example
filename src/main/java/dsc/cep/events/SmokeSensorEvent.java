package dsc.cep.events;

import java.util.Date;

public class SmokeSensorEvent {

	private String sensor;
	private boolean smoke;
	private Date timeStamp;

	public SmokeSensorEvent() {

	}

	public SmokeSensorEvent(String sensor, boolean smoke, Date timeStamp) {
		this.sensor = sensor;
		this.smoke = smoke;
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

	public Date getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}

}
