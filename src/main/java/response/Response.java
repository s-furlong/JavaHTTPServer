package response;

import java.util.HashMap;
import java.util.Map;

public class Response {
    public int status;
    public String message;
    public Map<String, String> headers = new HashMap<>();
    public String body;
    public String version;
    public Map<Integer, String> statusMessages = Map.of(
            200, "OK",
            404, "Not Found",
            405, "Not Allowed");

    public Response(int status) {
        this.status = status;
    }

    public Response(int status, Map<String, String> headers) {
        this.status = status;
        this.headers = headers;
    }

    @Override
    public String toString() {
        String headerString = "";
        if (!headers.isEmpty()) {
            for (var key : headers.keySet()) {
                headerString += key + ": " + headers.get(key) + "\r\n";
            }
        }
        if (this.message == null) {
            this.message = statusMessages.get(this.status);
        }
        return "HTTP/1.1 " + this.status + " " + this.message + "\r\n" + headerString + "\r\n";
    }

    public void addHeader(String name, String value) {
        headers.put(name, value);
    }


}
