package Interfaces;

import request.ClientRequest;
import response.ServerResponse;

import java.io.IOException;
import java.net.Socket;

public interface SocketInterfaces {
    void createNewServerSocket(int clientPort) throws IOException;

    Socket acceptClient() throws IOException;

    ClientRequest getRequest() throws IOException;

    void getResponse(ServerResponse response) throws IOException;

    void closeClientConnection(Socket clientSocket) throws IOException;

}
