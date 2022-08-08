package server;

import java.util.ArrayList;

public class ServerLog {
    static ArrayList<String> completeServerLog = new ArrayList<>();

    public ServerLog(ArrayList<String> completeServerLog) {
        ServerLog.completeServerLog = completeServerLog;
    }

    private void addToLog(String message) {
        completeServerLog.add(message);
    }

    public void logEstablishServer(int clientPort){
        String establishServer = "ESTABLISHED ON LOCALHOST: " + clientPort;
        System.err.println(establishServer);
        addToLog(establishServer);
    }
    public void logAcceptClient(){
        String acceptClient = "ACCEPTED CONNECTION";
        System.err.println(acceptClient);
        addToLog(acceptClient);
    }

    public void logMessageFromClient(){
        String clientMessage = "CLIENT SENT MESSAGE";
        System.err.println(clientMessage);
        addToLog(clientMessage);
    }

    public void logCloseConnection(){
        String closeConnection = "CLIENT CONNECTION CLOSED";
        System.err.println(closeConnection);
        addToLog(closeConnection);
    }


}
