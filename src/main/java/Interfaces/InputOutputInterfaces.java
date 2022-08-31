package Interfaces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

public interface InputOutputInterfaces {
    void createInputStream(Socket clientSocket) throws IOException;


    void createOutputStreamWriter(Socket clientSocket) throws IOException;

    String receivedMessage() throws IOException;


    String httpResponse(String request) throws IOException;

    void closeInputOutputStreams() throws IOException;

    void setClientSocket(Socket clientSocket);
}
