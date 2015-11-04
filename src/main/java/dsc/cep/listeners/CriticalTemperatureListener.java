package dsc.cep.listeners;

import java.util.Date;

import com.espertech.esper.client.EventBean;
import com.espertech.esper.client.UpdateListener;

public class CriticalTemperatureListener implements UpdateListener {

	public void update(EventBean[] newData, EventBean[] oldData) {

		System.out.println(this.getClass().getName() + " called at "
				+ new Date());

		if (newData != null) {
			System.out
					.println("\tTwo consecutive temperatures above threshold: ");
			for (int i = 0; i < newData.length; i++) {
				System.out.println("\t\t" + newData[i].getUnderlying());
			}
		}
	}

}
