package it.unipr.barbato.Model.Interface;

import jakarta.jms.Message;

public interface OnMessage {
	public void onMessage(Message message);
}
