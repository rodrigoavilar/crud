package com.example.demo.core;

import java.util.List;

/**
 * DTO for REST messages.
 * 
 * @author Virtus
 *
 */
public class RestMessageDTO extends ModelDTO {

	/**
	 * Message.
	 */
    private String message;
    
    /**
     * List of messages.
     */
    private List<String> messages;

    /**
     * Constructor.
     *
     * @param messages
     * 		Messages Code.
     */
    public RestMessageDTO(List<String> messages) {
        if (!messages.isEmpty()) {
            this.message = messages.get(0);
        }
        this.messages = messages;
    }

    /**
     * Constructor.
     *
     * @param message
     * 		Message Code.
     */
    public RestMessageDTO(String message) {
        this.message = message;
    }

    /**
     * Gets the message.
     * 
     * @return Message.
     */
	public String getMessage() {
		return message;
	}

	/**
	 * Sets the message.
	 * 
	 * @param message
	 * 		Message.
	 */
	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * Gets the list of messages.
	 * 
	 * @return List of messages.
	 */
	public List<String> getMessages() {
		return messages;
	}

	/**
	 * Sets the list of messages.
	 * 
	 * @param messages
	 * 		List of messages.
	 */
	public void setMessages(List<String> messages) {
		this.messages = messages;
	}
}