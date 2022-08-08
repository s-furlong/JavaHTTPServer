package Interfaces;

import java.io.IOException;
import java.net.Socket;

public interface SocketInterfaces {
    void createNewServerSocket(int clientPort) throws IOException;
    Socket acceptClient() throws IOException;
    void closeClientConnection(Socket clientSocket) throws IOException;

}
