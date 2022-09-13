package wrappers;

import Interfaces.InputOutputInterfaces;
import constants.Format;

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
        StringBuilder request = new StringBuilder();
        String line;
        while ((line = input.readLine()) != null) {
            request.append(line).append(Format.NEWLINE);
            if (line.equals("")) {
                break;
            }
        }
        return request.toString();

    }

    public String readBody(int contentLength) throws IOException {
        char[] charBody = new char[contentLength];
        input.read(charBody, 0, contentLength);
        return new String(charBody, 0, contentLength);
    }


    public void httpResponse(String stringResponse) {
        output.write(stringResponse);
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
