package wrappers;

import Interfaces.InputOutputInterfaces;

import java.io.*;
import java.net.Socket;


public class InputOutputWrappers implements InputOutputInterfaces {
    public Socket clientSocket;
    public BufferedReader input;
    public PrintWriter output;

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

    public String httpResponse(String bytes) throws IOException {
        output.write(Integer.parseInt(bytes));
        output.flush();
        return bytes;
    }

    @Override
    public void closeInputOutputStreams() throws IOException {
        input.close();
        output.close();
    }

    public void setClientSocket(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }
}
