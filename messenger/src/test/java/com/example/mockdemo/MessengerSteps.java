package com.example.mockdemo;

import com.example.mockdemo.app.Messenger;
import com.example.mockdemo.messenger.MalformedRecipientException;
import com.example.mockdemo.messenger.MessageService;
import com.example.mockdemo.messenger.MessageServiceSimpleImpl;
import static org.junit.Assert.*;
import org.jbehave.core.annotations.Given;
import org.jbehave.core.annotations.Then;
import org.jbehave.core.annotations.When;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.either;

public class MessengerSteps {
	
	private String SERVER;
	private String MESSAGE;
	
	private Messenger messenger;
	
	@Given("a messenger")
	public void setUP() throws MalformedRecipientException {
		messenger = new Messenger(new MessageServiceSimpleImpl());
	}
	
	@When("set variables to $message and $server")
	public void setInvalidVariables(String message, String server) throws MalformedRecipientException {
		SERVER = server;
		MESSAGE = message;
	}
	
	@Then("the output connection status should be $status")
	public void checkConnectionStatus(int status) throws MalformedRecipientException {
		assertEquals(status, messenger.testConnection(SERVER));
	}
	
	@Then("the output sending a message status should be $status")
	public void checkSendingStatus(int status) throws MalformedRecipientException {
		assertEquals(status, messenger.sendMessage(SERVER, MESSAGE));
	}
	
	
}
