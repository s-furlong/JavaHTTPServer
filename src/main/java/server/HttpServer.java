package server;

import Interfaces.InputOutputInterfaces;
import Interfaces.SocketInterfaces;
import Router.Router;
import request.ClientRequest;
import request.Request;
import response.ServerResponse;
import wrappers.InputOutputWrappers;
import wrappers.SocketWrappers;

import java.io.IOException;
import java.util.ArrayList;

public class HttpServer {

    public static void main(String[] args) throws IOException {
        int clientPort = (args.length > 0) ? Integer.parseInt(args[0]) : 5000;
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
        inputOutputWrappers.setClientSocket(clientSocket);

        serverLog.logAcceptClient();

        inputOutputWrappers.createInputStream(clientSocket);
        inputOutputWrappers.createOutputStreamWriter(clientSocket);

        String rawRequest;
        Request request;

        rawRequest = inputOutputWrappers.receivedMessage();
        serverLog.logMessage(rawRequest);

        request = new Request(rawRequest);
        request.parse();

//        var response = Router.generateResponse(request);
//
//        String rawResponse = inputOutputWrappers.httpResponse(response.toString());
//        serverLog.logResponse(rawResponse);


        inputOutputWrappers.closeInputOutputStreams();
        socketWrappers.closeClientConnection(clientSocket);
    }


}