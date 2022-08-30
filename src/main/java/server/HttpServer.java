package server;

import Interfaces.InputOutputInterfaces;
import Interfaces.SocketInterfaces;
import Router.Router;
import constants.HTTPMethod;
import constants.Path;
import request.ClientRequest;
import response.ServerResponse;
import wrappers.InputOutputWrappers;
import wrappers.SocketWrappers;

import java.io.*;
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
        // We don't need this if we are passing clientSocket to the other methods below
        inputOutputWrappers.setClientSocket(clientSocket);

        serverLog.logAcceptClient();

        // TD: This is ok, but let's talk about 'overloaded' methods
        // These are not doing anything b/c the method definitions are 'overloaded'
        // and the wrong thing is being called
        inputOutputWrappers.createInputStream(clientSocket);
        inputOutputWrappers.createOutputStreamWriter(clientSocket);



        Router router = new Router();

        // We're up to the point where we need to
        // 1) Read the raw request coming in from the client -- Who should do that? The thing with the input stream,
        // and the thing with the input stream should have gotten the socket
        // 2) Parse the raw request into a Request object -- Who should do that? Could be many things.
        // Why not just have a class that does that one thing? (Single Responsibility Principle)

        // TD: the `getRequest` below requires `socketMethods`, but never gets it
        // See java/wrappers/SocketWrappers.java line 35
        // What's the difference b/w Request, and ClientRequest -- keep it as a simple "data object", no behavior
        // other than getting or setting values
        // Let's take a look at Request -- it's not being used
        ClientRequest request = socketWrappers.getRequest();
        ServerResponse response;
        // Not sure this is going to work.  ClientRequest will always be a "something" -- an instantiated object, no?
        // I think it's ok to assume there will be some "request".  It's up to the router to handle that request,
        // even if it's a bad one
        if (request != null) {
            response = router.generateResponse(request);
            socketWrappers.getResponse(response);
        }


        /*
            1. Something takes the socket and opens up input stream
            2. Something takes that input stream and reads a raw string message
            3. Something converts that raw string message into a Request object
            4. Something takes that Request object and figures out what to do with it (Router), returning
                a Response object with values filled in
            5. Something takes that Response object and turns it into a raw string
            6. Something takes the socket and opens up an output stream
            7. Something takes the output stream and sends the raw string

         */


        inputOutputWrappers.closeInputOutputStreams();
        socketWrappers.closeClientConnection(clientSocket);
    }


}