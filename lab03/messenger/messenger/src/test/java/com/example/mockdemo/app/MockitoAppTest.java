package com.example.mockdemo.app;

import org.junit.*;

import com.example.mockdemo.messenger.ConnectionStatus;
import com.example.mockdemo.messenger.MalformedRecipientException;
import com.example.mockdemo.messenger.MessageService;
import com.example.mockdemo.messenger.MessageServiceSimpleImpl;

import static org.easymock.EasyMock.createMock;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class MockitoAppTest {

	private final String VALID_SERVER = "inf.ug.edu.pl";
	private final String INVALID_SERVER = "inf.ug.edu.eu";

	private final String VALID_MESSAGE = "some message";
	private final String INVALID_MESSAGE = "ab";
	
	private Messenger messenger;
	private MessageService msMock;
	
	@Before
	public void setUP() throws MalformedRecipientException {
		msMock = mock(MessageService.class);
		messenger = new Messenger(msMock);
	}
	
	@Test
	public void checkingSendingResults() {
		when(msMock.checkConnection(VALID_SERVER)).thenReturn(ConnectionStatus.FAILURE);		
		assertEquals(ConnectionStatus.FAILURE, msMock.checkConnection(VALID_SERVER));
	}

	@Test
	public void checkingSendingResults2() {
		when(msMock.checkConnection(VALID_SERVER)).thenReturn(ConnectionStatus.FAILURE);		
		assertEquals(ConnectionStatus.FAILURE, msMock.checkConnection(VALID_SERVER));
	}
	
	
	@Test
	public void checkingInvalidResults() {
		when(msMock.checkConnection(INVALID_SERVER)).thenReturn(ConnectionStatus.SUCCESS);
		assertEquals(ConnectionStatus.SUCCESS, msMock.checkConnection(INVALID_SERVER));
		verify(msMock);
	}
	
	
	/*
	@Test
	public void sendingInvalidReceipient() throws MalformedRecipientException {

		when(msMock.send(VALID_SERVER, INVALID_MESSAGE)).thenThrow(
				new MalformedRecipientException());

		replay(msMock);

		assertEquals(2, messenger.sendMessage(VALID_SERVER, INVALID_MESSAGE));
		verify(msMock);
	}
	*/
}
