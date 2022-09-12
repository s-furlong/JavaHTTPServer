package wrappers;

import Interfaces.SocketInterfaces;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketWrappers implements SocketInterfaces {
    private ServerSocket serverSocket;
    private InputOutputWrappers socketMethods;

    @Override
    public void createNewServerSocket(int clientPort) throws IOException {
        serverSocket = new ServerSocket(clientPort);
        InputOutputWrappers socketMethods;
    }

    @Override
    public Socket acceptClient() throws IOException {
        var socket = serverSocket.accept();
//        socket.setSoTimeout(500);
        return socket;
    }


    @Override
    public void closeClientConnection(Socket clientSocket) throws IOException {
        clientSocket.close();
    }
}
