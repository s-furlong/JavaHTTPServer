package mocks;

import Interfaces.SocketInterfaces;

import java.io.IOException;
import java.net.Socket;

public class MockSocketWrapper implements SocketInterfaces {
    private int getNumberOfCallsToAcceptClient = 0;
    private int getCallToCreateServerSocket = 0;
    private int getNumberOfCallsToCloseClientConnection = 0;

    @Override
    public void createNewServerSocket(int clientPort) {
        getCallToCreateServerSocket++;
    }
    @Override
    public Socket acceptClient() {
        getNumberOfCallsToAcceptClient++;
        return null;
    }

    @Override
    public void closeClientConnection(Socket clientSocket) {
        getNumberOfCallsToCloseClientConnection++;

    }
    public int getNumberOfCallsToAcceptClient() {
        return getNumberOfCallsToAcceptClient;
    }

    public int getCallToCreateServerSocket() {
        return getCallToCreateServerSocket;
    }

    public int getNumberOfCallsToCloseClientConnection() {
        return getNumberOfCallsToCloseClientConnection;
    }

}
