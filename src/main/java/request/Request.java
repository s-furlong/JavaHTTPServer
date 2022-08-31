package request;


import constants.Format;
import constants.HTTPMethod;
import constants.Path;

import java.util.HashMap;

public class Request {
    public String rawRequest;
    public HTTPMethod verb;
    public Path path;
    public String version;
    public HashMap<String, String> headers;
    public String body;


    public Request(String rawRequest) {
        this.rawRequest = rawRequest;
    }

    public void parse() {
        this.verb = getVerb();
        this.path = getPath();
        this.version = getVersion();
        this.headers = getHeaders();
        this.body = getBody();
    }

    public String extractComponents() {
        return rawRequest;
    }

    public HTTPMethod getVerb() {
        String requestString = extractComponents().split(" ")[0];
        return HTTPMethod.findVerb(requestString);
    }

    public Path getPath() {
        String requestString = extractComponents().split(" ")[1];
        return Path.findPath(requestString);
    }

    public String getVersion() {
        var requestString = extractComponents().split(Format.NEWLINE)[0];
        var version = requestString.split(" ")[2];
        return version.trim();
    }

    public HashMap<String, String> getHeaders() {
        String request = extractComponents().split(Format.BLANKLINE)[0];
        String[] headerArray = request.split(Format.NEWLINE);

        HashMap<String, String> headers = new HashMap<>();
        for (int i = 1; i < headerArray.length; i++) {
            String[] pairedHeaders = headerArray[i].split(": ");
            if (pairedHeaders.length == 2) {
                String key = pairedHeaders[0];
                String value;
                if (i == pairedHeaders.length - 1) {
                    value = pairedHeaders[1].split(Format.BLANKLINE, 2)[0];
                    headers.put(key, value);
                } else {
                    value = pairedHeaders[1];
                    headers.put(key, value);
                }
            }
        }
        return headers;
    }

    public String getBody() {
        var requestComponents = extractComponents().split(Format.BLANKLINE);
        if (requestComponents.length > 1) {
            return requestComponents[1];
        } else {
            return null;
        }
    }
}
