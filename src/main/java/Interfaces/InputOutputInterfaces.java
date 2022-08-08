package Interfaces;

import java.io.IOException;
import java.net.Socket;

public interface InputOutputInterfaces {
    void createInputStream(Socket clientSocket) throws IOException;

    void createOutputStream(Socket clientSocket) throws IOException;

    String receivedMessage() throws IOException;

    void echoedMessage(String s) throws IOException;

    void closeInputOutputStreams() throws IOException;
}
