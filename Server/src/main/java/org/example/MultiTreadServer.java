package org.example;

import org.example.data.ClientEntity;
import org.example.data.ClientRepository;
import org.example.data.ConnectionData;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MultiTreadServer {
    public static void main(String[] args) throws IOException {

        ClientRepository repository = new ClientRepository();
        ConnectionReporter connectionReporter = new ConnectionReporter();
        Communicator communicator = new Communicator(repository);
        ClientService clientService = new ClientService(communicator);

        try (ServerSocket serverSocket = new ServerSocket(8085)) {

            while (true) {
                Socket clientSocket = serverSocket.accept();
                ClientEntity clientEntity = clientService.createClient(clientSocket);
                repository.saveClient(clientEntity);

                ConnectionData report = connectionReporter.getConnectionInfo(clientSocket);
                System.out.println(report);

                new Thread(() -> {
                    try {
                        communicator.receive(clientEntity);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }).start();
            }
        }
    }
}