package response;

import constants.Format;
import constants.HTTPMethod;
import constants.StatusCode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Response {
    public StatusCode rawStatus;
    public Map<String, String> mapHeaders = new HashMap<>();
    public String body = "";
    public String version;
    public String status;
    public String headers;


    public Response(StatusCode rawStatus) {
        this.rawStatus = rawStatus;
    }

    public Response(StatusCode rawStatus, Map<String, String> mapHeaders) {
        this.rawStatus = rawStatus;
        if (mapHeaders != null) {
            this.mapHeaders = mapHeaders;
        } else {
            throw new IllegalArgumentException("Headers cant be null");
        }

    }

    public Response(StatusCode rawStatus, Map<String, String> mapHeaders, String body) {
        this.rawStatus = rawStatus;
        if (mapHeaders != null) {
            this.mapHeaders = mapHeaders;
        } else {
            throw new IllegalArgumentException("Headers cant be null");
        }
        this.body = body;

    }

    public Response(StatusCode rawStatus, String body) {
        this.rawStatus = rawStatus;
        this.body = body;

    }

    public void parse() {
        this.version = getVersion();
        this.status = getStatus();
        this.body = getBody();
        this.headers = getHeaders();


    }

    public String format() {
        return version + " " + status + Format.NEWLINE + headers + Format.NEWLINE + body;
    }

    public String getVersion() {
        return "HTTP/1.1";
    }

    public String getStatus() {
        return rawStatus.formatFromCode();
    }

    public String getHeaders() {
        StringBuilder headers = new StringBuilder();
        if (!mapHeaders.isEmpty()) {
            for (var key : mapHeaders.keySet()) {
                headers.append(key)
                        .append(": ")
                        .append(mapHeaders.get(key))
                        .append(Format.NEWLINE);
            }
        }
        return headers.toString();
    }

    public String getBody() {
        return body;
    }

    public void addHeader(String name, String value) {
        mapHeaders.put(name, value);
    }

    public void addAllowHeader(List<HTTPMethod> verbs) {
        String name = "allow";
        ArrayList<String> methods = new ArrayList<>();
        for (HTTPMethod verb : verbs) {
            methods.add(verb.name());
        }
        String value = String.join(", ", methods);

        addHeader(name, value);
    }

    public void contentLengthHeader(String body) {
        String name = "Content-Length";
        String value = String.valueOf(body.length());


        addHeader(name, value);
    }
}
