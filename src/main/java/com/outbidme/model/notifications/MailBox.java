package com.outbidme.model.notifications;

import java.util.Queue;
import java.util.concurrent.LinkedBlockingQueue;


/**
 * Represents a container of messages associated to an account.
 */
public class MailBox {

	private int accountId =-0;
	private Queue<Message> messages;
	
	public MailBox(int accountId) {
		this.accountId = accountId;
		this.messages = new LinkedBlockingQueue<>();
	}

	public int getAccountId() {
		return accountId;
	}

	public Message getLastMessage() {
		return messages.peek();
	}
	
}
