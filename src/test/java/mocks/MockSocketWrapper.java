package mocks;

import Interfaces.SocketInterfaces;

import java.net.Socket;

public class MockSocketWrapper implements SocketInterfaces {
    private final Socket clientSocket;
    private int getNumberOfCallsToAcceptClient = 0;
    private int getCallToCreateServerSocket = 0;
    private int getNumberOfCallsToCloseClientConnection = 0;
    private String message;

    public MockSocketWrapper(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    @Override
    public void createNewServerSocket(int clientPort) {
        getCallToCreateServerSocket++;
    }

    @Override
    public Socket acceptClient() {
        getNumberOfCallsToAcceptClient++;
        return new MockSocket(this.message);
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public void closeClientConnection(Socket clientSocket) {
        getNumberOfCallsToCloseClientConnection++;

    }

    public int getNumberOfCallsToAcceptClient() {
        return getNumberOfCallsToAcceptClient;
    }


    public int getNumberOfCallsToCloseClientConnection() {
        return getNumberOfCallsToCloseClientConnection;
    }

}
