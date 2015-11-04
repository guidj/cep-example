package dsc.cep;

import com.espertech.esper.client.Configuration;
import com.espertech.esper.client.EPAdministrator;
import com.espertech.esper.client.EPRuntime;
import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPServiceProviderManager;
import com.espertech.esper.client.EPStatement;

import dsc.cep.events.TemperatureSensorEvent;
import dsc.cep.listeners.AverageTemperatureListener;
import dsc.cep.listeners.HighTemperatureListener;


public class App 
{
    public static void main( String[] args )
    {        
    	Configuration cepConfig = new Configuration();
    	cepConfig.addEventType("TemperatureEventStream", TemperatureSensorEvent.class.getName());
    	
    	EPServiceProvider cep = EPServiceProviderManager.getProvider("myCEPEngine", cepConfig);
    	EPRuntime cepRT = cep.getEPRuntime();
    	
    	EPAdministrator cepAdm = cep.getEPAdministrator();
    	
//    	EPStatement cepStatement = cepAdm.createEPL(
//    			"SELECT irstream temperature FROM TemperatureEventStream.win:length(2)");
//    	cepStatement.addListener(new SimpleListener());
    	
    	EPStatement highTempStatement = cepAdm.createEPL(
    			"SELECT istream temperature FROM TemperatureEventStream.win:time(30 sec) "
    			+ "WHERE temperature > 100"
    			);
    	highTempStatement.addListener(new HighTemperatureListener());
    	
    	EPStatement averageTempStatement = cepAdm.createEPL(
//    			"SELECT istream avg(temperature) as avgTemp "
//    			+ "FROM TemperatureEventStream.win:time(30 sec)"
    			"SELECT avg(temperature) as avgTemp "
    			+ "FROM TemperatureEventStream.win:time(30 sec) "
    			+ "HAVING avg(temperature) > 70"
    			);
    	averageTempStatement.addListener(new AverageTemperatureListener());
    	averageTempStatement.setSubscriber(new AverageSubscriber("Average Temperature"));
    	
    	
    	//pass cepTR to event listener to send new events
    	//create event to receive average
    	
    	SimpleTemperatureEventGenerator gen = new SimpleTemperatureEventGenerator(cepRT, 1000, 50);
    	gen.startSendingTemperatureReadings();
    }
}
