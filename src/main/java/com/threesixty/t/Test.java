package com.threesixty.t;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;

/**
 * The Main Class for run both Single-Tread and Multi-Thread chat test
 *
 * @author Mostafa Anbarmoo
 * @project Messenger
 * @created 2023-06-09 12:35
 */
public class Test {
    /**
     * The Main Method of Test Messenger Application
     * This Method will Test Single-Tread and Multi-Thread chat
     */
    public static void main(String[] args) throws InterruptedException {
        System.out.println("try to run single thread chat...");
        singleThreadChat();
        System.out.println("single thread chat finished.");
        System.out.println("try to run multi thread chat...");
        multiThreadChat();
        System.out.println("multi-thread chat finished.");
        System.out.println("application will be shutdown in 10 seconds...");
        Thread.sleep(10_000);
        System.exit(0);
    }

    /**
     * The singleThreadChat do logic of single thread test and print the conversation between two players to console
     */
    public static void singleThreadChat() throws InterruptedException {
        String message = "Hi!";
        String conversationId = "singleThreadConversation";
        Player initiator = new Player("initiator", conversationId);
        Player player2 = new Player("player2", conversationId);
        while (initiator.getCounter() < Config.messageCounterLimitation) {
            message = initiator.sendMessage(message);
            System.out.println("message send by sender: " + initiator.getName() + " message: " + message);
            message = player2.receiveMessage(initiator);
            System.out.println("message receiver by sender: " + initiator.getName() + " message: " + message);
            Thread.sleep(500);
            player2.sendMessage(message);
            initiator.receiveMessage(player2);
            Thread.sleep(500);
            System.out.println("Initiator sent - " + initiator.getCounter() + " and received - " + initiator.getCounter() + " message(s)");
        }
    }

    /**
     * The multiThreadChat do logic of multi thread test and print the conversation between two players to console
     */
    public static void multiThreadChat() throws InterruptedException {
        String message = "Hello!";
        String conversationId = "MultiThreadConversation";
        Player sender = new Player("initiator", conversationId);
        Player receiver = new Player("receiver", conversationId);
        ExecutorService executorService = Executors.newFixedThreadPool(2);
        PlayerThread senderThread = new PlayerThread(sender, receiver, message, Config.messageCounterLimitation);
        PlayerThread receiverThread = new PlayerThread(receiver, sender, message, Config.messageCounterLimitation);
        executorService.execute(senderThread);
        executorService.execute(receiverThread);
        final ThreadPoolExecutor pool = (ThreadPoolExecutor) executorService;
        while (pool.getActiveCount() > 0) {
            System.out.println("Waiting to close a group chat, active players: " + pool.getActiveCount());
            Thread.sleep(10_000);
        }
        System.out.println("Finishing the group chat. Current active players: " + pool.getActiveCount());
        executorService.shutdown();
    }
}
