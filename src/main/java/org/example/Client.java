package org.example;

import java.io.BufferedReader;
import java.io.IOException;
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
            printWriter.println("hello my socket");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

            while (true){
                String line = scanner.nextLine();
                String[] splited = line.split(" ");
                String command = splited[0];
                String path = splited[1];
                if (command.contains("**")){
                    fileSender.sendFile(path, socket);
                }else {
                    printWriter.println(line);
                    String serverMessage = bufferedReader.readLine();
                    System.out.println(serverMessage);
                }

            }

        }catch (IOException e){
            throw new RuntimeException();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}