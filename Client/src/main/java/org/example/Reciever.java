package org.example;

import java.io.*;
import java.net.Socket;

public class Reciever {

    public Reciever() {
    }

    private InputStreamReader inputStreamReader = null;
    private BufferedReader bufferedReader = null;

    public Thread connect(Socket socket) {
        return new Thread() {
            @Override
            public void run() {
                try {
                    inputStreamReader = new InputStreamReader(socket.getInputStream());
                    bufferedReader = new BufferedReader(inputStreamReader);

                    while (true) {
                        String line = bufferedReader.readLine();
                        System.out.println("Message: " + line);
                        Thread.sleep(300);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    System.out.println("Connection refused");
                }
            }
        };
    }
}