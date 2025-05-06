package networking;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;

public class ClientHandler implements Runnable {

  private Socket socket;
  private String clientId;
  private BufferedReader input;
  private PrintWriter output;
  private final ConcurrentHashMap<String, ClientHandler> clients;

  public ClientHandler(Socket socket, String clientId,
      ConcurrentHashMap<String, ClientHandler> clients)
  {
    this.socket = socket;
    this.clients = clients;
    this.clientId = clientId;
    try
    {
      this.input = new BufferedReader(
          new InputStreamReader(socket.getInputStream()));
      this.output = new PrintWriter(socket.getOutputStream(), true);
    }
    catch (IOException e)
    {
      System.out.println("Error in Client:" + clientId);
    }

  }

  @Override public void run()
  {
    try
    {
      String msg;
      while ((msg = input.readLine()) != null)
      {
        System.out.println("[" + clientId + "] " + msg);
        output.println("Echo" + msg);
        if (msg.equalsIgnoreCase("exit"))
        {
          break;
        }
      }
    }
    catch (IOException e)
    {
      throw new RuntimeException(e);
    }
    finally
    {
      cleanup();
    }
  }

  private void cleanup()
  {
    clients.remove(clientId);

    try
    {
      socket.close();

    }
    catch (IOException e)
    {
      System.out.println("Client:" + clientId + "disconnected ");
    }

  }
}

