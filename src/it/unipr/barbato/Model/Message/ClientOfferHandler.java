package it.unipr.barbato.Model.Message;

import java.io.Serializable;
import java.lang.management.ManagementFactory;

import it.unipr.barbato.Interface.Message.Handler;
import it.unipr.barbato.Interface.Product.ProductOffer;
import it.unipr.barbato.Model.Product.ProductOfferImpl;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.MessageListener;
import jakarta.jms.ObjectMessage;

/**
 * The {@code ClientOffer} represents a client offer for a product.
 * Implements the {@link Handler} and {@link MessageListener} interfaces.
 * 
 * @author Vincenzo Barbato 345728
 */
public class ClientOfferHandler implements Handler, MessageListener {

	private int productsCount = 0;
	private ProductOfferImpl offer = null;
	private MessageHandler messageHandler = null;

	/**
	 * Constructs a ClientOffer object with the specified MessageHandler.
	 *
	 * @param messageHandler the MessageHandler to use for sending and receiving messages
	 * @throws JMSException if there is an error with the JMS connection
	 */
	public ClientOfferHandler(MessageHandler messageHandler) throws JMSException {
		this.messageHandler = messageHandler;
	}

	/**
	 * Gets the count of products in the client offer.
	 *
	 * @return the count of products
	 */
	public int getProductsCount() {
		return this.productsCount;
	}

	/**
	 * Gets the client offer.
	 *
	 * @return the client offer
	 */
	public ProductOffer getClientOffer() {
		return this.offer;
	}

	/**
	 * Sets the client offer with the specified serial number, offer, and confirmation status.
	 *
	 * @param sn      the serial number of the product
	 * @param offer   the offer price
	 * @param confirm the confirmation status
	 */
	public void setClientOffer(int sn, int offer, Boolean confirm) {
		this.offer.setSN(sn);
		this.offer.setOffer(offer);
		this.offer.setConfirm(confirm);
	}

	@Override
	public void start() throws JMSException {
		offer = new ProductOfferImpl(ManagementFactory.getRuntimeMXBean().getName(), true);
		messageHandler.setOnSend(this);
		messageHandler.send("offersList", offer, RequestType.subscribe);
	}


	@Override
	public void close() throws JMSException {
		offer.setSubscribe(false);
		messageHandler.send("offersList", offer, RequestType.subscribe);
	}


	@Override
	public void onMessage(Message message) {
		try {
			if (message instanceof ObjectMessage) {

				ObjectMessage objectMessage = (ObjectMessage) message;
				Serializable object = objectMessage.getObject();
				ProductOfferImpl response = (ProductOfferImpl) object;
				Boolean confirm = response.getConfirm();

				System.out.println("-------------------------");
				if (confirm) {
					productsCount++;
					System.out.println("Server has accepted offer! Product with sn: " + response.getSN()
							+ " has been bought at price: " + response.getOffer() + "$");
				} else {
					System.out.println("Server has rejected the offered price: " + response.getOffer() + "$ "
							+ " for product with sn: " + response.getSN());
				}

				offer.setConfirm(null);
				offer.setOffer(0);
				offer.setSN(0);
			}
		} catch (JMSException e) {
			e.printStackTrace();
		}

	}
}
