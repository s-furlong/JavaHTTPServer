package mocks;

import Interfaces.SocketInterfaces;
import request.ClientRequest;
import response.ServerResponse;
import wrappers.InputOutputWrappers;

import java.io.IOException;
import java.net.Socket;

public class MockSocketWrapper implements SocketInterfaces {
    private final Socket clientSocket;
    private final InputOutputWrappers inputOutputWrappers;
    private int getNumberOfCallsToAcceptClient = 0;
    private int getCallToCreateServerSocket = 0;
    private int getNumberOfCallsToCloseClientConnection = 0;
    private String message;

    public MockSocketWrapper(Socket clientSocket, InputOutputWrappers inputOutputWrappers) {
        this.clientSocket = clientSocket;
        this.inputOutputWrappers = inputOutputWrappers;
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

    @Override
    public ClientRequest getRequest() throws IOException {
        return null;
    }

    @Override
    public void getResponse(ServerResponse response) throws IOException {

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

    public int getCallToCreateServerSocket() {
        return getCallToCreateServerSocket;
    }

    public int getNumberOfCallsToCloseClientConnection() {
        return getNumberOfCallsToCloseClientConnection;
    }

}
