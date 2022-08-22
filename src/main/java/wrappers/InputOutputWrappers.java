package wrappers;

import Interfaces.InputOutputInterfaces;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;


public class InputOutputWrappers implements InputOutputInterfaces {

    public BufferedReader input;
    public PrintWriter output;
    public Socket clientSocket;

    public InputOutputWrappers() {
        this.input = null;
        this.output = null;
        this.clientSocket = null;
    }

    public void createInputStream(Socket clientSocket) throws IOException {
        this.clientSocket = clientSocket;
        input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }

    public void createOutputStreamWriter(Socket clientSocket) throws IOException {
        output = new PrintWriter(clientSocket.getOutputStream(), true);
    }

    @Override
    public String receivedMessage() throws IOException {
        return input.readLine();
    }

    public String httpResponse(String clientData) throws IOException {
        output.write(clientData);
        output.flush();
        return clientData;
    }

    @Override
    public void echoedMessage(String s) {
        output.println(s);
    }

    @Override
    public void closeInputOutputStreams() throws IOException {
        input.close();
        output.close();
    }
}
