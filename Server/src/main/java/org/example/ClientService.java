package org.example;

import org.example.data.ClientEntity;

import java.net.Socket;

public class ClientService {
    private int clientCount = 1;
    private final Communicator communicator;

    public ClientService(Communicator communicator) {
        this.communicator = communicator;
    }

    public ClientEntity createClient(Socket socket){
        String clientName = "Client-" + clientCount;
        clientCount++;
        communicator.sendNotifications(clientName);
        return new ClientEntity(clientName, socket);
    }


}
