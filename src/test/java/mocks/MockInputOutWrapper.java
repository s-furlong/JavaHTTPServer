package mocks;

import Interfaces.InputOutputInterfaces;
import java.net.Socket;
import java.util.ArrayList;

public class MockInputOutWrapper implements InputOutputInterfaces {
    private int getNumberOfCallsToCreateInputStream = 0;
    private int getNumberOfCallsToCreateOutStream = 0;
    private int getNumberOfCallsReceiveMessages = 0;
    private final ArrayList<String> receivedMessages = new ArrayList<>();
    private final ArrayList<String> echoedMessages = new ArrayList<>();
    private int getNumberOfCallsToCloseConnection = 0;


    @Override
    public void createInputStream(Socket clientSocket) {
        getNumberOfCallsToCreateInputStream++;
    }

    @Override
    public void createOutputStream(Socket clientSocket) {
        getNumberOfCallsToCreateOutStream++;
    }

    @Override
    public String receivedMessage() {
        var message =receivedMessages.remove(0);
        return message;
    }

    @Override
    public void echoedMessage(String s) {
        echoedMessages.add(s);
    }

    @Override
    public void closeInputOutputStreams() {
        getNumberOfCallsToCloseConnection++;

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
