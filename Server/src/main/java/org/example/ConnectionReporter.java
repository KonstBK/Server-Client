package org.example;

import org.example.data.ClientEntity;
import org.example.data.ConnectionData;

import java.net.InetAddress;

public class ConnectionReporter {

    public ConnectionData getConnectionInfo(ClientEntity clientEntity){
        InetAddress inetAddress = clientEntity.getSocket().getInetAddress();
        String clientName = clientEntity.getId();
        long creationTime = clientEntity.getCreatedAt();
        return new ConnectionData(clientName, creationTime, inetAddress);
    }
}
