package it.unipr.barbato.Model;

import jakarta.jms.Message;
import jakarta.jms.MessageConsumer;
import jakarta.jms.MessageListener;
import jakarta.jms.ObjectMessage;
import jakarta.jms.Session;
import jakarta.jms.Topic;
import jakarta.jms.TopicPublisher;
import jakarta.jms.TopicSession;

import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;

import it.unipr.barbato.Model.Interface.OnMessage;

public class MessageHandler implements MessageListener {
	private OnMessage customOnMessage = null;
	private ActiveMQConnection connection = null;

	public MessageHandler(final String BROKER_URL) throws JMSException {
		ActiveMQConnectionFactory cf = new ActiveMQConnectionFactory(BROKER_URL);
		cf.setTrustAllPackages(true);

		connection = (ActiveMQConnection) cf.createConnection();

		connection.start();
	}

	/**
	 * Publishes a sequence of messages.
	 *
	 * @param n the number of messages.
	 *
	 **/
	public void publish(String topic_name, java.io.Serializable obj) {
		try {
			TopicSession session = connection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);

			Topic topic = session.createTopic(topic_name);

			TopicPublisher publisher = session.createPublisher(topic);

			ObjectMessage objectMessage = session.createObjectMessage();
			objectMessage.setObject(obj);

			publisher.publish(objectMessage);

			publisher.publish(session.createMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void subscribe(String topic_name) {
		try {

			TopicSession session = connection.createTopicSession(false, Session.AUTO_ACKNOWLEDGE);

			Topic topic = session.createTopic(topic_name);

			MessageConsumer consumer = session.createConsumer(topic);

			consumer.setMessageListener(this);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void setOnMessage(OnMessage customOnMessage) {
		this.customOnMessage = customOnMessage;
	}

	@Override
	public void onMessage(Message arg0) {
		this.customOnMessage.onMessage(arg0);
	}

}
