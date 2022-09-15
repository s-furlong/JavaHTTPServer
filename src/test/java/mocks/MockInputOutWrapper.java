package mocks;

import Interfaces.InputOutputInterfaces;

import java.net.Socket;
import java.util.ArrayList;

public class MockInputOutWrapper implements InputOutputInterfaces {
    private final ArrayList<String> receivedMessages = new ArrayList<>();
    private final ArrayList<String> echoedMessages = new ArrayList<>();
    private int getNumberOfCallsToCreateInputStream = 0;
    private int getNumberOfCallsToCreateOutStream = 0;
    private int getNumberOfCallsReceiveMessages = 0;
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
    public String readBody(int contentLength) {
        if (receivedMessages.size() > 0) {
            var s = receivedMessages.get(0);
            return s.substring(0, contentLength);
        } else {
            return "";
        }

    }


    @Override
    public void httpResponse(String request) {

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


    public int getNumberOfCallsToCloseConnection() {
        return getNumberOfCallsToCloseConnection;
    }

}
