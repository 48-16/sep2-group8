package networking;

import java.net.Socket;

public class clientHandler implements Runnable{

    private Socket clientSocket;
    private server server;

    public clientHandler(Socket socket, server server) {

        this.clientSocket = socket;
        this.server = server;

    }

    @Override
    public void run() {

    }
}
