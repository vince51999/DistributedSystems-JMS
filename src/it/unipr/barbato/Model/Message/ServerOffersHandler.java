package it.unipr.barbato.Model.Message;

import java.io.Serializable;
import java.util.Set;
import java.util.concurrent.CopyOnWriteArraySet;

import it.unipr.barbato.Interface.Message.Handler;
import it.unipr.barbato.Interface.Product.Product;
import it.unipr.barbato.Interface.Product.ProductOffer;
import it.unipr.barbato.Interface.Product.ProductsList;
import it.unipr.barbato.Interface.Product.ProductsOffersList;
import it.unipr.barbato.Model.Product.ProductsOffersListImpl;
import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.MessageListener;
import jakarta.jms.MessageProducer;
import jakarta.jms.ObjectMessage;

/**
 * The {@code ServerOffersHandler} class implements the {@link Handler} and {@link MessageListener} interfaces
 * to handle server-side offers for products. It keeps track of the number of connected clients and response to client offers.
 * 
 * @author Vincenzo Barbato 345728
 */
public class ServerOffersHandler implements Handler, MessageListener {
	/**
	 * The number of connected clients.
	 */
	private int num_client = 0;
	/**
	 * The list of product offers.
	 */
	private ProductsOffersList offersList = null;
	/**
	 * The list of products.
	 */
	private ProductsList productsList = null;
	/**
	 * The message handler.
	 */
	private MessageHandlerImpl messageHandler = null;

	/**
	 * Constructs a ServerOffersHandler object with the specified MessageHandler.
	 *
	 * @param messageHandler the MessageHandler to be used for handling messages
	 * @throws JMSException if there is an error with the JMS connection
	 */
	public ServerOffersHandler(MessageHandlerImpl messageHandler) throws JMSException {
		Set<ProductOffer> offers = new CopyOnWriteArraySet<>();
		offersList = new ProductsOffersListImpl(offers);
		this.messageHandler = messageHandler;
	}

	/**
	 * Gets the number of connected clients.
	 *
	 * @return the number of connected clients
	 */
	public int getSize() {
		return this.num_client;
	}

	/**
	 * Sets the list of products.
	 *
	 * @param productsList the list of products
	 */
	public void setProductList(ProductsList productsList) {
		this.productsList = productsList;
	}

	@Override
	public void start() throws JMSException {
		this.messageHandler.setOnReceive(this);
		this.messageHandler.receive("offersList");
	}

	@Override
	public void close() throws JMSException {
		System.out.println("All clients are disconnected. Shop close.");
	}

	@Override
	public void onMessage(Message message) {
		try {
			if (message instanceof ObjectMessage) {
				ObjectMessage objectMessage = (ObjectMessage) message;
				Serializable rh = objectMessage.getObject();
				ProductOffer obj = (ProductOffer) ((RequestHandler) rh).obj;
				RequestType rt = (RequestType) ((RequestHandler) rh).type;
				switch (rt) {
				case subscribe:
					Boolean subscribe = obj.getSubscribe();
					if (subscribe) {
						System.out.println("Client: " + obj.getID() + " added to offersList");
						this.num_client++;
						offersList.subscribe(obj);
					} else {
						System.out.println("Client: " + obj.getID() + " removed from offersList");
						this.num_client--;
						offersList.unsubscribe(obj);
					}
					break;
				case offer:
					int sn = obj.getSN();
					int o = obj.getOffer();
					if (o > 0 && sn > 0) {
						Product p = productsList.getProduct(sn);

						System.out.println("--------------------");
						System.out.println("SN: " + sn + " Price: " + p.getPrice() + "$" + " Offer: " + o + "$");

						if (p.getPrice() <= o) {
							obj.setConfirm(true);
							System.out.println("Offer accepted");
						} else {
							obj.setConfirm(false);
							System.out.println("Offer rejected");
						}
						ObjectMessage reply = messageHandler.getQueueSession().createObjectMessage();
						MessageProducer producer = messageHandler.getQueueSession().createProducer(null);
						reply.setObject(obj);
						reply.setJMSCorrelationID(message.getJMSCorrelationID());
						producer.send(message.getJMSReplyTo(), reply);
					}
					break;
				}
			}
		} catch (JMSException e) {
			e.printStackTrace();

		}

	}
}
