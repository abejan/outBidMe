package com.outbidme.model.notifications;

import java.util.Stack;

import javax.persistence.Entity;
import javax.persistence.Id;


/**
 * Represents a container of messages associated to an account.
 */
@Entity
public class MailBox {

	@Id
	private int accountId;
	private Stack<Message> messages;
	
	public MailBox(int accountId) {
		this.accountId = accountId;
		this.messages = new Stack<>();
	}

	public int getAccountId() {
		return accountId;
	}

	public Message getLastMessage() {
		return messages.lastElement();
	}

	public void addMessage(Message message) {
		this.messages.add(message);
	}

	public boolean isEmpty() {
		return this.messages.isEmpty();
	}
	
}
