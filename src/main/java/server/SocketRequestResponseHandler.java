package server;

import Logic.RequestLogic;
import Logic.ResponseLogic;
import request.ClientRequest;
import response.ServerResponse;
import wrappers.InputOutputWrappers;

import java.io.IOException;
import java.net.Socket;

public class SocketRequestResponseHandler {
    private final Socket clientSocket;
    private final InputOutputWrappers socketMethods;


    public SocketRequestResponseHandler(Socket clientSocket, InputOutputWrappers socketMethods) {
        this.clientSocket = clientSocket;
        this.socketMethods = socketMethods;
    }

    public ClientRequest getRequest() throws IOException {
        return new RequestLogic(socketMethods).inputRead();
    }

    public void getResponse(ServerResponse response) throws IOException {
        new ResponseLogic(socketMethods).outputResponse(response);
    }
}
