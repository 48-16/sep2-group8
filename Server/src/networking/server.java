package networking;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class server {

    private static final int PORT = 8888;
    private ServerSocket serverSocket;
    private Map<Socket, clientHandler> clientHandlers;
    private Gson gson;

    public server() {
        clientHandlers = new ConcurrentHashMap<>();
        gson = new Gson();
    }

    public void startServer() {
        try {
            serverSocket = new ServerSocket(PORT);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                clientHandler clientHandler = new clientHandler(clientSocket, this);
                clientHandlers.put(clientSocket, clientHandler);
                new Thread(clientHandler).start();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
