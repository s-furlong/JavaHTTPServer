package response;

import constants.HTTPMethod;
import constants.StatusCode;
import constants.TypeOfContent;

import java.util.ArrayList;
import java.util.Set;

public class ResponseBuild {
    private String serverLine = StatusCode.OK.formatFromCode();
    private ArrayList<String> headers = new ArrayList<>();
    private byte[] body;

    public void setServerLine(String ServerLine) {
        this.serverLine = serverLine;
    }

    public void addAllowHeader(Set<HTTPMethod> methods) {
        ArrayList<String> stringMethod = new ArrayList<>();
        for (HTTPMethod method : methods) {
            stringMethod.add(method.name());
        }
        String joinedMethods = String.join(", ", stringMethod);
        StringBuilder header = new StringBuilder();
        header.append("Allow: ");
        header.append(joinedMethods);

        headers.add(header.toString());
    }

    public void contentLengthHeader(int size) {
        StringBuilder header = new StringBuilder();
        header.append("Content-Length: ");
        header.append(size);

        headers.add(header.toString());
    }

    public void contentTypeHeader(TypeOfContent type) {
        StringBuilder header = new StringBuilder();
        header.append("Content-Type: ");
        header.append(type);

        headers.add(header.toString());
    }

    public ResponseBuild setBody(byte[] body) {
        this.body = body;
        return this;
    }

    public ServerResponse buildResponse() {
        if (headers.isEmpty()) {
            headers = null;
        }
        return new ServerResponse(serverLine, headers, body);
    }
}
