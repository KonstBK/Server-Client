package org.example;

import java.net.Socket;

public class Client {
    public static void main(String[] args) {
        Sender sender = new Sender();
        Reciever reciever = new Reciever();

        try (Socket socket = new Socket("localhost", 8085)) {
            sender.connect(socket).start();
            reciever.connect(socket).start();
            while (true){
                Thread.sleep(10000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}