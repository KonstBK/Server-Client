package org.example.data;

import java.net.Socket;

public class ClientEntity {
    private final long createdAt;
    private final String id;
    private final Socket socket;

    public ClientEntity(String id, Socket socket) {
        this.createdAt = System.currentTimeMillis();
        this.id = id;
        this.socket = socket;
    }

    public String getId() {
        return id;
    }

    public Socket getSocket() {
        return socket;
    }

    public long getCreatedAt() {
        return createdAt;
    }
}
