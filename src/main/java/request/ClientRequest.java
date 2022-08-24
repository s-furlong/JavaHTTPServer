package request;

import constants.HTTPMethod;
import constants.Path;

import java.util.HashMap;


public class ClientRequest {
    public HTTPMethod verb;
    public Path path;
    public HashMap<String, String> headers;
    public String body;

    public ClientRequest(HTTPMethod verb, Path path, HashMap<String, String> headers, String body) {
        this.verb = verb;
        this.path = path;
        this.headers = headers;
        this.body = body;


    }
}

