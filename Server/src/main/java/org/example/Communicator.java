package org.example;

import org.example.data.ClientEntity;
import org.example.data.ClientRepository;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Communicator {
    Pattern pattern = Pattern.compile("(?<=file ).+");

    private final ClientRepository repository;

    public Communicator(ClientRepository repository) {
        this.repository = repository;
    }

    public void receive(ClientEntity client) throws Exception {
        try {

            BufferedReader bufferedReader = new BufferedReader(
                    new InputStreamReader(client.getSocket().getInputStream()));
                    String word;

            while ((word = bufferedReader.readLine()) != null) {
                System.out.println(word);

                if (word.contains("**")){
                    Matcher matcher = pattern.matcher(word);
                    if(matcher.find()){
                        String path = matcher.group().trim();
                        receiveFile(path, client);
                    }
                }
                if (word.contains("exit")){
                    exit(client);
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void exit(ClientEntity client) throws IOException {
        repository.removeClient(client);
        client.getSocket().close();
    }

    private void receiveFile(String fileName, ClientEntity client) throws Exception{
        int bytes;
        String modifiedPath = modifyPath(fileName);
        FileOutputStream fileOutputStream = new FileOutputStream(modifiedPath);
        DataInputStream dataInputStream = new DataInputStream(client.getSocket().getInputStream());
        System.out.println("reseaving file " + fileName);
        long size = dataInputStream.readLong();     // read file size
        byte[] buffer = new byte[4*1024];
        while (size > 0 && (bytes = dataInputStream.read(buffer, 0, (int)Math.min(buffer.length, size))) != -1) {
            fileOutputStream.write(buffer,0,bytes);
            size -= bytes;      // read upto file size
        }
        fileOutputStream.close();
        System.out.println("file reseaved " + modifiedPath);
    }

    private String modifyPath(String path) {

        Path filePath = Paths.get(path);
        String fileName = filePath.getFileName().toString();
        String dirPath = "D:\\IdeaProjects\\Server-Client\\Server\\src\\File folder\\";

        return dirPath + fileName;
    }

    public void sendNotifications(String clientName){

        repository.getAllClients().forEach(clientEntity -> {
            try  {
                DataOutputStream out = new DataOutputStream(clientEntity.getSocket().getOutputStream());
                out.writeUTF("[SERVER] " + clientName + " successfully connected");
                out.flush();//Need to close but it closed socket also
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
