package response;

import constants.Format;
import constants.HTTPMethod;
import constants.StatusCode;

import java.util.*;

public class Response {
    public StatusCode rawStatus;
    public Map<String, String> mapHeaders = new HashMap<>();
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

    public void parse() {
        this.version = getVersion();
        this.status = getStatus();
        this.headers = getHeaders();

    }

    public String format() {
        return version + " " + status + Format.NEWLINE + headers + Format.NEWLINE;
    }

    public String getVersion() {
        String version = "HTTP/1.1";
        return version;
    }

    public String getStatus() {
        String stringStatus = rawStatus.formatFromCode();
        return stringStatus;
    }

    public String getHeaders() {
        String headers = "";
        if (!mapHeaders.isEmpty()) {
            for (var key : mapHeaders.keySet()) {
                headers += key + ": " + mapHeaders.get(key) + Format.NEWLINE;
            }
        }
        return headers;
    }

    public void addHeader(String name, String value) {
        mapHeaders.put(name, value);
    }

    public void addAllowHeader(List<HTTPMethod> verbs) {
        String name = "Allow";
        ArrayList<String> methods = new ArrayList<>();
        for (HTTPMethod verb : verbs) {
            methods.add(verb.name());
        }
        String value = String.join(", ", methods);

        addHeader(name, value);
    }
}
