package networking;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;

public class Server {

    private static final int PORT = 8888;
    //private ServerSocket serverSocket;
    private static final ConcurrentHashMap<String, ClientHandler> clientHandlers = new ConcurrentHashMap<>() ;
    private Gson gson;
    private static int clientCounter = 0;

    public static void main(String[] args)
    {
        try (ServerSocket serverSocket = new ServerSocket( PORT)){
            while( true){
                Socket clientSocket = serverSocket.accept();
                int clientId = ++clientCounter;
                new Thread (()  -> handleClient(clientId, clientSocket)).start();
            }
    }catch (IOException e ){
            e.printStackTrace();
        }
    }

    private static void handleClient(int clientId, Socket clientSocket)
    {
        System.out.println("Client " + clientId + " connected");
        try (BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true)


            ){String msg;
        while ((msg = in.readLine()) != null ){
            System.out.println("client" + clientId + ": " + msg);
            out.println("echo" + msg);
            if ("exit".equalsIgnoreCase(msg)){break;}
        }}catch (IOException e ){
            System.out.println("Client " + clientId + " disconnected");
        } finally { clientHandlers.remove(clientId);
        try { clientSocket.close(); } catch (IOException e) {
            System.out.println("Client " + clientId + " disconnected");
        }
    }

     /* public void startServer() {
        try {
            serverSocket = new ServerSocket(PORT);

            while (true) {
                Socket clientSocket = serverSocket.accept();
                ClientHandler clientHandler = new ClientHandler(clientSocket, this);
                clientHandlers.put(clientSocket, clientHandler);
                new Thread(clientHandler).start();
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }*/
}}