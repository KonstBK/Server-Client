package org.example.data;

import java.net.InetAddress;
import java.util.Arrays;

public class ConnectionData {

    private final String name;
    private final long connectionTime;
    private final InetAddress socketData;

    public ConnectionData(String name, long connectionTime, InetAddress socketData) {
        this.name = name;
        this.connectionTime = connectionTime;
        this.socketData = socketData;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "[SERVER] New connection\n" + name + "\n" +
                "connectionTime= " + connectionTime + "\n" +
                "hostName " + socketData.getHostName() + "\n" +
                "hostAddress " + socketData.getHostAddress() + "\n" +
                "address " + Arrays.toString(socketData.getAddress()) + "\n";
    }
}
