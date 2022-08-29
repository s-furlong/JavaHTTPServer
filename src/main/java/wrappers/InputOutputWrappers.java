package wrappers;

import Interfaces.InputOutputInterfaces;

import java.io.*;
import java.net.Socket;


public class InputOutputWrappers implements InputOutputInterfaces {

    public BufferedReader input;
    public OutputStream output;
    public Socket clientSocket;


    public BufferedReader createInputStream() throws IOException {
        return new BufferedReader(new InputStreamReader(this.clientSocket.getInputStream()));
    }

    public OutputStream createOutputStreamWriter() throws IOException {
        return clientSocket.getOutputStream();
    }

    @Override
    public void createInputStream(Socket clientSocket) throws IOException {

    }

    @Override
    public void createOutputStreamWriter(Socket clientSocket) throws IOException {

    }

    @Override
    public String receivedMessage() throws IOException {
        return input.readLine();
    }

    @Override
    public void echoedMessage(String s) throws IOException {

    }

    @Override
    public String httpResponse(String s) throws IOException {
        return null;
    }

    public String readMessage(int number) throws IOException {
        char[] deconstrutedMessage = new char[number];
        input.read(deconstrutedMessage, 0, number);
        return new String(deconstrutedMessage, 0, number);
    }

    public void httpResponse(byte[] bytes) throws IOException {
        output.write(bytes);
        output.flush();
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
