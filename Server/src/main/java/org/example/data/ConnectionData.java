package org.example.data;

import java.net.InetAddress;
import java.time.LocalDateTime;
import java.util.Arrays;

public class ConnectionData {

    private final String name;
    private final LocalDateTime connectionTime;
    private final InetAddress socketData;

    public ConnectionData(String name, LocalDateTime connectionTime, InetAddress socketData) {
        this.name = name;
        this.connectionTime = connectionTime;
        this.socketData = socketData;
    }

    public String getName() {
        return name;
    }
//
//    public LocalDateTime getConnectrionTime() {
//        return connectrionTime = LocalDateTime.now();
//    }
//
//    public InetAddress getSocketData() {
//        return socketData;
//    }

    @Override
    public String toString() {
        return "ConnectionReport{" +
                "name='" + name + '\'' +
                ", connectionTime=" + connectionTime +
                ", hostName" + socketData.getHostName() + " , hostAddress " + socketData.getHostAddress() +
                " , address " + Arrays.toString(socketData.getAddress()) +
                '}';
    }
}
