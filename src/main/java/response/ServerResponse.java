package response;

import java.util.List;

public class ServerResponse {
    private String serverline;
    private List<String> headers;
    private byte[] body;

    public ServerResponse(String serverline, List<String> headers, byte[] body) {
        this.serverline = serverline;
        this.headers = headers;
        this.body = body;
    }
}
