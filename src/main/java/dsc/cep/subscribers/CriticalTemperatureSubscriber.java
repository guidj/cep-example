package dsc.cep.subscribers;

import java.util.Date;
import java.util.Map;

import dsc.cep.events.TemperatureSensorEvent;

public class CriticalTemperatureSubscriber {
	
	public void update(Map<String, TemperatureSensorEvent> events) {
		
		System.out.println(this.getClass().getName() + " called at " + new Date());
		
		System.out.println("\t******Critical Alarm Triggered @" + new Date() + "******");
		
		for (String alarm: new String[] {"tempA", "tempB", "tempC", "tempD"}) {
			System.out.println("\t\t" + alarm + ": " + events.get(alarm).getSensor() + "["+ events.get(alarm).getTemperature() + "]");
		}	
	}

}
