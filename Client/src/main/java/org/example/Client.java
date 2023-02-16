package org.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        FileSender fileSender = new FileSender();

        try (Socket socket = new Socket("localhost" ,8085)){
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
            printWriter.println("Connection successful");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            while (true){
                String line = scanner.nextLine();
                if (line.contains("** -file")){
                    String[] splited = line.split("-file ");

                    printWriter.println(line);

                    String path = splited[1].trim();
                    fileSender.sendFile(path, socket);
                }else {
                    printWriter.println(line);
                    String serverMessage = bufferedReader.readLine();
                    System.out.println(serverMessage);
                }

            }

        } catch (Exception e){
            System.out.println("Connection refused");
        }
    }
}