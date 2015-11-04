package dsc.cep.listeners;

import java.util.Date;

import com.espertech.esper.client.EventBean;
import com.espertech.esper.client.UpdateListener;

public class AverageTemperatureListener implements UpdateListener {

	public void update(EventBean[] newData, EventBean[] oldData) {
		
		System.out.println(this.getClass().getName() + " called at " + new Date());
		
		if (newData != null) {
			for (int i = 0; i < newData.length; i++) {
				Double value = Double.parseDouble(newData[i].get("avgTemp").toString());
				
				System.out.println("\tAverage above threshold: " + value);
			}
		}	
	}

}
