package Interfaces;

import java.io.IOException;
import java.net.Socket;

public interface InputOutputInterfaces {
    void createInputStream(Socket clientSocket) throws IOException;

    void createOutputStreamWriter(Socket clientSocket) throws IOException;

    String receivedMessage() throws IOException;

    String readBody(int contentLength) throws IOException;

    void httpResponse(String request) throws IOException;

    void closeInputOutputStreams() throws IOException;

}
