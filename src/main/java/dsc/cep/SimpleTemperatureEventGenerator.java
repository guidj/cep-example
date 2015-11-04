package dsc.cep;

import java.util.Date;
import java.util.Random;

import com.espertech.esper.client.EPRuntime;

import dsc.cep.events.TemperatureSensorEvent;

public class SimpleTemperatureEventGenerator {
	
	private EPRuntime cepRT;
	private int noe;
	private int sleepTime;
	
	public SimpleTemperatureEventGenerator(EPRuntime cepRT, int sleepTime, int neo) {
		
		this.cepRT = cepRT;
		this.noe = neo;
		this.sleepTime = sleepTime;
	}
	
	public void startSendingTemperatureReadings() {
		
		int count = 0;
		
		while (count < this.noe) {
			TemperatureSensorEvent event = new TemperatureSensorEvent("sensorX", new Random().nextInt(150), new Date());
			//System.out.println("Sending " + event.getTemperature() + " at " + event.getTimeStamp());
			cepRT.sendEvent(event);
			count++;
			
			try {
				Thread.sleep(this.sleepTime);
			}catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}
