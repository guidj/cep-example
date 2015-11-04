package dsc.cep.listeners;

import java.util.Date;

import com.espertech.esper.client.EventBean;
import com.espertech.esper.client.UpdateListener;

public class SimpleListener implements UpdateListener {
	
	public void update(EventBean[] newData, EventBean[] oldData) {
		
		System.out.println("Listener called at " + new Date());
		
		if (newData != null) {
			for (int i = 0; i < newData.length; i++) {
				System.out.println("New event received " + newData[i].getUnderlying());
			}
		}
		
		if (oldData != null) {
			for (int i = 0; i < oldData.length; i++) {
				System.out.println("Old event received " + oldData[i].getUnderlying());
			}
		}		
	}

}
