package com.threesixty.t;

/**
 * The Player Thread for chat between 2 players simultaneously
 *
 * @author Mostafa Anbarmoo
 * @project Messenger
 * @created 2023-06-09 17:25
 */
public class PlayerThread implements Runnable {
    /**
     * sender player to start send messages
     */
    private final Player sender;
    /**
     * receiver player to start send messages
     */
    private final Player receiver;
    /**
     * message content
     */
    private String message;
    /**
     * message counter limitation
     */
    private final int messageCounterLimitation;

    /**
     * The Constructor of PlayerThread class
     *
     * @param sender                   Player for send message
     * @param receiver                 Player for receive message
     * @param message                  String message content
     * @param messageCounterLimitation max message counter limitation threshold
     */
    public PlayerThread(Player sender, Player receiver, String message, int messageCounterLimitation) {
        this.sender = sender;
        this.receiver = receiver;
        this.message = message;
        this.messageCounterLimitation = messageCounterLimitation;
    }


    /**
     * runnable method for process send and receive messages between sender and receiver players
     */
    @Override
    public void run() {
        while (sender.getCounter() < messageCounterLimitation) {
            message = sender.sendMessage(message);
            System.out.println("message sent by sender: " + sender.getName() + " with thread number: " + Thread.currentThread().getName() +
                    " message: " + message + " " + " counter: " + sender.getCounter());
            try {
                Thread.sleep(500);
                receiver.receiveMessage(sender);
                while (message == null) {
                    message = sender.receiveMessage(sender);
                }
                System.out.println("message received form thread number: " + Thread.currentThread().getName() +
                        " message: " + message + " " + " counter: " + receiver.getCounter());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
