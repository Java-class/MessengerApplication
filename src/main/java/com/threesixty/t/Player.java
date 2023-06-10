package com.threesixty.t;

/**
 * The Player class for represent a sender or receiver actor in messenger
 *
 * @author Mostafa Anbarmoo
 * @project Messenger
 * @created 2023-06-09 12:36
 */
public class Player {
    /**
     * name of the player
     */
    private final String name;
    /**
     * conversation id for playing in specific conversation
     */
    private final String conversationId;
    /**
     * counter of a message sent
     */
    private int counter;

    /**
     * Constructor of Player class
     *
     * @param name           String name of player
     * @param conversationId String id of conversation
     */
    public Player(String name, String conversationId) {
        this.name = name;
        this.conversationId = conversationId;
        this.counter = 0;
    }

    /**
     * Get player's counter
     *
     * @return integer counter value
     */
    public int getCounter() {
        return this.counter;
    }

    /**
     * Get player's name
     *
     * @return String player's name
     */
    public String getName() {
        return name;
    }

    /**
     * Send Message to specific conversation
     *
     * @param message String message content
     * @return String message's sent content
     */
    public String sendMessage(String message) {
        this.counter++;
        message = message + counter;
        MessageQueue.push(conversationId, message);
        return message;
    }

    /**
     * Receive Message from specific conversation
     *
     * @return String message's received content
     */
    public String receiveMessage(Player sender) {
        return MessageQueue.pull(conversationId);
    }
}
