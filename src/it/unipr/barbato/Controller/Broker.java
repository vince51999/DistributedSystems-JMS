package it.unipr.barbato.Controller;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.broker.BrokerFactory;
import org.apache.activemq.broker.BrokerService;

public class Broker {
	private static final String BROKER_URL = "tcp://localhost:61616";
	private static final String BROKER_PROPS = "persistent=false&useJmx=false";
	private static ActiveMQConnection connection = null;
	public static void main(String[] args) throws Exception {

		try {
			BrokerService broker = BrokerFactory.createBroker("broker:(" + BROKER_URL + ")?" + BROKER_PROPS);

			broker.start();

			ActiveMQConnectionFactory cf = new ActiveMQConnectionFactory(BROKER_URL);
			cf.setTrustAllPackages(true);

			connection = (ActiveMQConnection) cf.createConnection();

			connection.start();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
