package com.threesixty.t;

import java.util.concurrent.ConcurrentHashMap;

/**
 * MessageQueue for storing and retrieving message between players
 *
 * @author Mostafa Anbarmoo
 * @project Messenger
 * @created 2023-06-09 17:52
 */
public class MessageQueue {

    /**
     * Data structure for manage messages
     */
    public static ConcurrentHashMap<String, String> messageQueue = new ConcurrentHashMap<>();

    /**
     * The push method in MessageQueue class
     * We Call for send a message to another player in specific conversation
     * This method add a message to message queue data structure
     *
     * @param conversationId mandatory string for specific conversation
     * @param message        String content of a message
     */
    public static void push(String conversationId, String message) {
        messageQueue.put(conversationId, message);
    }

    /**
     * The pull method in MessageQueue class
     * We Call for receives a new message from another player in specific conversation
     * This method pulls a message from message queue data structure
     *
     * @param conversationId mandatory string for specific conversation
     * @return message String content of a message
     */
    public static String pull(String conversationId) {
        return messageQueue.remove(conversationId);
    }
}
