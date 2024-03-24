package it.unipr.barbato.Model.Message;

import java.io.Serializable;

import it.unipr.barbato.Interface.Message.Handler;
import it.unipr.barbato.Interface.Product.ProductsList;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.MessageListener;
import jakarta.jms.ObjectMessage;

/**
 * The {@code ClientProductsHandler} class implements the {@link Handler} and {@link MessageListener} interfaces.
 * It is responsible for handling client product messages and updating the products list.
 * 
 *  @author Vincenzo Barbato 345728
 */
public class ClientProductsHandler implements Handler, MessageListener {

	/**
	 * The products list.
	 */
	private ProductsList productsList = null;
	/**
	 * The message handler.
	 */
	private MessageHandlerImpl messageHandler = null;

	/**
	 * Constructs a ClientProductsHandler object with the specified MessageHandler.
	 *
	 * @param messageHandler the MessageHandler to be used for handling messages
	 */
	public ClientProductsHandler(MessageHandlerImpl messageHandler) {
		this.messageHandler = messageHandler;
	}

	/**
	 * Gets the current products list.
	 *
	 * @return the current products list
	 */
	public ProductsList getProductsList() {
		return this.productsList;
	}


	@Override
	public void start() throws JMSException {
		messageHandler.setOnSubscribe(this);
		messageHandler.subscribe("productsList");
	}


	@Override
	public void close() throws JMSException {
		System.out.println("Leave the shop.");
	}


	@Override
	public void onMessage(Message message) {
		try {
			if (message instanceof ObjectMessage) {
				ObjectMessage objectMessage = (ObjectMessage) message;
				Serializable object = objectMessage.getObject();
				this.productsList = (ProductsList) object;
			}
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
}
