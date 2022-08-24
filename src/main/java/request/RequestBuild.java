package request;

import constants.HTTPMethod;
import constants.Path;

import java.util.HashMap;

public class RequestBuild {
    private HTTPMethod verb;
    private Path path;
    private HashMap<String, String> headers;
    private String body;


    public RequestBuild setVerb(HTTPMethod verb) {
        this.verb = verb;
        return this;
    }

    public RequestBuild setPath(Path path) {
        this.path = path;
        return this;
    }

    public RequestBuild setHeaders(HashMap<String, String> headers) {
        this.headers = headers;
        return this;
    }

    public RequestBuild setBody(String body) {
        this.body = body;
        return this;
    }

    public ClientRequest buildRequest() {
        return new ClientRequest(verb, path, headers, body);
    }

    ;
}
