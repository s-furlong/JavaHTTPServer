package server;

import Interfaces.InputOutputInterfaces;
import Interfaces.SocketInterfaces;
import wrappers.InputOutputWrappers;
import wrappers.SocketWrappers;

import java.io.*;
import java.util.ArrayList;

public class HttpServer {

    public static void main(String[] args) throws IOException {
        int clientPort = (args.length > 0) ? Integer.parseInt(args[0]) : 4444;
        ArrayList<String> log = new ArrayList<>();
        var socketWrappers = new SocketWrappers();
        var inputOutputWrappers = new InputOutputWrappers();
        var serverLog = new ServerLog(log);

        socketWrappers.createNewServerSocket(clientPort);
        serverLog.logEstablishServer(clientPort);

        while (true) {
            run(inputOutputWrappers, socketWrappers, serverLog);
        }

    }

    static void run(InputOutputInterfaces inputOutputWrappers, SocketInterfaces socketWrappers, ServerLog serverLog) throws IOException {
        var clientSocket = socketWrappers.acceptClient();
        serverLog.logAcceptClient();

        inputOutputWrappers.createInputStream(clientSocket);
        inputOutputWrappers.createOutputStreamWriter(clientSocket);


        String request;
        request = inputOutputWrappers.receivedMessage();
        serverLog.logMessage(request);

        String response = inputOutputWrappers.httpResponse(request);
        serverLog.logResponse(response);


        inputOutputWrappers.closeInputOutputStreams();
        socketWrappers.closeClientConnection(clientSocket);
    }


}