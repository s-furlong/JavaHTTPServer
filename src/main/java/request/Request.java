package request;

import java.util.HashMap;
import java.util.Map;

public class Request {
    private String clientRequest;
    private String[] chunks;
    public String verb;
    public String path;
    public String version;
    public Map<String, String> headers;
    public String body = "";

    public Request(String clientRequest) {
        this.clientRequest = clientRequest;
        this.headers = new HashMap<>();
        this.chunks = new String[]{};
    }

    public void parse() {
        this.chunks = getChunks();
        this.clientRequest = chunks[0];
        this.verb = getVerb();
        this.path = getPath();
        this.version = getVersion();
        this.headers = getHeaders();
        if (chunks.length > 1) {
            this.body = chunks[1];
        } else {
            this.body = "";
        }

    }

    public String getVerb() {
        return clientRequest.split(" ")[0];
    }

    public String getPath() {
        return clientRequest.split(" ")[1];
    }

    public String getVersion() {
        var firstLine = clientRequest.split("\r\n")[0];
        var version = firstLine.split(" ")[2];
        return version.trim();
    }

    public Map<String, String> getHeaders() {
        return new HashMap<>();
    }

    public String[] getChunks() {
        return clientRequest.split("\r\n\r\n", 2);
    }
}
