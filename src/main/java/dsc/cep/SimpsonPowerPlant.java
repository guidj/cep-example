package dsc.cep;

import com.espertech.esper.client.Configuration;
import com.espertech.esper.client.EPAdministrator;
import com.espertech.esper.client.EPRuntime;
import com.espertech.esper.client.EPServiceProvider;
import com.espertech.esper.client.EPServiceProviderManager;
import com.espertech.esper.client.EPStatement;

import dsc.cep.events.TemperatureSensorEvent;
import dsc.cep.listeners.SimpleListener;
import dsc.cep.subscribers.CriticalTemperatureSubscriber;
import dsc.cep.subscribers.WarningTemperatureSubscriber;


public class SimpsonPowerPlant 
{
	
	private static final double THRESHOLD_TEMP = 70;
    public static void main( String[] args )
    {        
    	Configuration cepConfig = new Configuration();
    	cepConfig.addEventType("TemperatureEventStream", TemperatureSensorEvent.class.getName());
    	
    	EPServiceProvider cep = EPServiceProviderManager.getProvider("myCEPEngine", cepConfig);
    	EPRuntime cepRT = cep.getEPRuntime();
    	
    	EPAdministrator cepAdm = cep.getEPAdministrator();

    	EPStatement temperatureStatement = cepAdm.createEPL(
    			"SELECT istream sensor, temperature FROM TemperatureEventStream.win:time(10 sec) "
    			+ "WHERE temperature > " + THRESHOLD_TEMP
    			);
    	temperatureStatement.addListener(new SimpleListener());

		EPStatement warningStatement = cepAdm
				.createEPL("SELECT istream * FROM TemperatureEventStream "
						+ "MATCH_RECOGNIZE( "
						+ "PARTITION BY sensor "
						+ "MEASURES A as tempA, B as tempB "
						+ "PATTERN (A B) "
						+ "DEFINE "
						+ "	A as A.temperature > "+ THRESHOLD_TEMP + ", "
						+ "	B as B.temperature > "+ THRESHOLD_TEMP + ")");
    	warningStatement.setSubscriber(new WarningTemperatureSubscriber());
    	
		EPStatement criticalStatement = cepAdm
				.createEPL("SELECT istream * FROM TemperatureEventStream "
						+ "MATCH_RECOGNIZE( "
						+ "PARTITION BY sensor "
						+ "MEASURES A as tempA, B as tempB, C as tempC, D as tempD "
						+ "PATTERN (A B C D) "
						+ "DEFINE "
						+ "	A as A.temperature > " + THRESHOLD_TEMP + ","
						+ " B as B.temperature > A.temperature, "
						+ " C as C.temperature > B.temperature, "
						+ " D as D.temperature > C.temperature AND D.temperature > A.temperature*1.5 "
						+ ")");
		criticalStatement.setSubscriber(new CriticalTemperatureSubscriber());    	
    	
    	PowerPlantTemperatureGenerator gen = new PowerPlantTemperatureGenerator(cepRT, 1000, 500);
    	gen.startSendingTemperatureReadings();
    }
}
