package it.unipr.barbato.Model;

import java.io.Serializable;

import org.apache.activemq.ActiveMQConnection;

import it.unipr.barbato.Model.Interface.OnMessage;
import it.unipr.barbato.Model.Interface.Product;
import it.unipr.barbato.Model.Interface.ProductsList;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.ObjectMessage;

public class ServerOnMessage implements OnMessage {
	private ActiveMQConnection connection = null;

	@Override
	public void onMessage(Message message) {
		try {
			if (message instanceof ObjectMessage) {
				ObjectMessage objectMessage = (ObjectMessage) message;
				Serializable object = objectMessage.getObject();
				System.out.println(((ProductsList) object).getSNs());
			}
		} catch (JMSException e) {
			e.printStackTrace();
		}
		if (connection != null) {
			try {
				connection.close();
			} catch (JMSException e) {
				e.printStackTrace();
			}
		}

	}

}
