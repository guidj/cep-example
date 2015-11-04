package dsc.cep.subscribers;

import java.util.Date;
import java.util.Map;

import dsc.cep.events.TemperatureSensorEvent;

public class WarningTemperatureSubscriber {
	
	public void update(Map<String, TemperatureSensorEvent> events) {
		
		System.out.println(this.getClass().getName() + " called at " + new Date());
		
		System.out.println("\tTwo consecutive temperatures above threshold: ");
		
		for (String alarm: new String[] {"tempA", "tempB"}) {
			System.out.println("\t\t" + alarm + ": " + events.get(alarm).getSensor() + "["+ events.get(alarm).getTemperature() + "]");
		}	
	}

}
