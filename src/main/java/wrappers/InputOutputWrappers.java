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

    public InputOutputWrappers() {
        this.input = null;
        this.output = null;
    }

    public void createInputStream(Socket clientSocket) throws IOException {
        input = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
    }

    public void createOutputStream(Socket clientSocket) throws IOException {
        output = new PrintWriter(clientSocket.getOutputStream(), true);
        output.println("Enter a Message: ");
    }

    @Override
    public String receivedMessage() throws IOException {
        return input.readLine();
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
