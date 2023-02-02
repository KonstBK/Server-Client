package org.example;

import org.example.data.ConnectionData;

import java.net.InetAddress;
import java.net.Socket;
import java.time.LocalDateTime;

public class ConnectionReporter {

    public ConnectionData getConnectionInfo(Socket clientSocket){
        InetAddress inetAddress = clientSocket.getInetAddress();
        String clientName = "Client-";
        return new ConnectionData(clientName, LocalDateTime.now(), inetAddress);
    }
}
