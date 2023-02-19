package org.example;

import java.io.BufferedWriter;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.util.Scanner;

public class Sender {

    public Sender() {
    }

    private OutputStreamWriter outputStreamWriter;
    private Scanner scanner;
    private BufferedWriter bufferedWriter;

    public Thread connect(Socket socket) {
        return new Thread() {
            @Override
            public void run() {
                try {
                    outputStreamWriter = new OutputStreamWriter(socket.getOutputStream());
                    scanner = new Scanner(System.in);
                    bufferedWriter = new BufferedWriter(outputStreamWriter);
                    FileSender fileSender = new FileSender();

                    while (true) {
                        if (scanner.hasNextLine()) {
                            String messageToSend = scanner.nextLine();
                            if (messageToSend.contains("** -file")) {
                                String[] splited = messageToSend.split("-file ");
                                String path = splited[1].trim();
                                fileSender.sendFile(path, socket);
                            } else {
                                bufferedWriter.write(messageToSend);
                                bufferedWriter.newLine();
                                bufferedWriter.flush();
                            }
                            if (messageToSend.equals("exit")) {
                                return;
                            }
                        }
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        };
    }
}