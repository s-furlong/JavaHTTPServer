package mocks;

import Interfaces.InputOutputInterfaces;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;

public class MockInputOutWrapper implements InputOutputInterfaces {
    private int getNumberOfCallsToCreateInputStream = 0;
    private int getNumberOfCallsToCreateOutStream = 0;
    private int getNumberOfCallsReceiveMessages = 0;
    private final ArrayList<String> receivedMessages = new ArrayList<>();
    private final ArrayList<String> echoedMessages = new ArrayList<>();
    private int getNumberOfCallsToCloseConnection = 0;
    private Socket clientSocket;


    @Override
    public void createInputStream(Socket clientSocket) {
        getNumberOfCallsToCreateInputStream++;
    }

    @Override
    public void createOutputStreamWriter(Socket clientSocket) {
        getNumberOfCallsToCreateOutStream++;
    }

    @Override
    public String receivedMessage() {
        if (receivedMessages.size() > 0) {
            return receivedMessages.remove(0);
        } else {
            return "";
        }
    }

    @Override
    public String httpResponse(String request) throws IOException {

        return request;
    }

    @Override
    public void closeInputOutputStreams() {
        getNumberOfCallsToCloseConnection++;

    }

    @Override
    public void setClientSocket(Socket clientSocket) {

    }


    public String setReceivedMessage(String s) {
        receivedMessages.add(s);
        getNumberOfCallsReceiveMessages++;
        return s;
    }

    public int getNumberOfCallsToCreateInputStream() {

        return getNumberOfCallsToCreateInputStream;
    }

    public int getNumberOfCallsToCreateOutStream() {

        return getNumberOfCallsToCreateOutStream;
    }

    public int getNumberOfCallsReceiveMessages() {

        return getNumberOfCallsReceiveMessages;
    }

    public ArrayList<String> getEchoedMessages() {
        return echoedMessages;
    }

    public int getNumberOfCallsToCloseConnection() {
        return getNumberOfCallsToCloseConnection;
    }
}
