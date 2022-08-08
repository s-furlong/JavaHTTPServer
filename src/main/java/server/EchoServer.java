package server;

import Interfaces.InputOutputInterfaces;
import Interfaces.SocketInterfaces;
import wrappers.InputOutputWrappers;
import wrappers.SocketWrappers;

import java.io.*;
import java.util.ArrayList;

public class EchoServer {

    public static  void main(String[] args) throws IOException {
        int clientPort = (args.length > 0) ? Integer.parseInt(args[0]) : 4444;
        ArrayList<String> log = new ArrayList<>();
        var socketWrappers = new SocketWrappers();
        var inputOutputWrappers = new InputOutputWrappers();
        var serverLog = new ServerLog(log);

        socketWrappers.createNewServerSocket(clientPort);
        serverLog.logEstablishServer(clientPort);

        while(true) {
            run(inputOutputWrappers, socketWrappers, serverLog);
        }
    }
    static void run(InputOutputInterfaces inputOutputWrappers, SocketInterfaces socketWrappers, ServerLog serverLog) throws IOException {
        var clientSocket = socketWrappers.acceptClient();
        serverLog.logAcceptClient();

        inputOutputWrappers.createInputStream(clientSocket);
        inputOutputWrappers.createOutputStream(clientSocket);

        String s;
        while ((s = inputOutputWrappers.receivedMessage()) != null) {
            if(s.equalsIgnoreCase("off")) {
                serverLog.logCloseConnection();
                break;
            }
            serverLog.logMessageFromClient();
            inputOutputWrappers.echoedMessage(s);
        }

        inputOutputWrappers.closeInputOutputStreams();
        socketWrappers.closeClientConnection(clientSocket);
    }


}