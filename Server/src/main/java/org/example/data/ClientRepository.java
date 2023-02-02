package org.example.data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ClientRepository {
    private final Map<String, ClientEntity> connectedClients = new HashMap<>();

    public void saveClient(ClientEntity client){
        connectedClients.put(client.getId(), client);
    }

    public List<ClientEntity> getAllClients(){
        return connectedClients.values().stream().toList();
    }

    public void removeClient(ClientEntity client) {
        connectedClients.remove(client.getId());
    }
}
