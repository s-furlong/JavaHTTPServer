package wrappers;

import Interfaces.SocketInterfaces;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketWrappers implements SocketInterfaces {
    private ServerSocket serverSocket;

    @Override
    public void createNewServerSocket(int clientPort) throws IOException {
        serverSocket = new ServerSocket(clientPort);
    }

    @Override
    public Socket acceptClient() throws IOException {
        return serverSocket.accept();
    }

    @Override
    public void closeClientConnection(Socket clientSocket) throws IOException {
        clientSocket.close();
    }
}
