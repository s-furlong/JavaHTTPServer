package Logic;

import constants.Format;
import constants.HTTPMethod;
import constants.Path;
import request.ClientRequest;
import request.RequestBuild;
import request.RequestParse;
import wrappers.InputOutputWrappers;

import java.io.IOException;
import java.util.HashMap;

public class RequestLogic {
    private final InputOutputWrappers requestServerMethods;

    public RequestLogic(InputOutputWrappers requestServerMethods) {
        this.requestServerMethods = requestServerMethods;
    }

    public ClientRequest inputRead() throws IOException {
        String request = RequestFormatter();
        if (!request.isBlank()) {
            RequestParse parsedRequest = new RequestParse(request);
            HTTPMethod verb = parsedRequest.verb();
            Path path = parsedRequest.path();
            HashMap<String, String> headers = parsedRequest.headers();

            String body = "";
            if (headers.containsKey("Content-Length")) {
                int bytes = Integer.parseInt(headers.get("Content-Length"));
                body = addBody(bytes);
            }
            return new RequestBuild()
                    .setVerb(verb)
                    .setPath(path)
                    .setHeaders(headers)
                    .setBody(body)
                    .buildRequest();
        }
        return null;
    }

    public String RequestFormatter() throws IOException {
        StringBuilder request = new StringBuilder();
        String line;

        while ((line = requestServerMethods.receivedMessage()) != null) {
            request.append(line).append(Format.NEWLINE);
            if (line.isBlank()) {
                break;
            }
        }
        return request.toString();
    }

    public String addBody(int size) throws IOException {
        return requestServerMethods.readMessage(size);
    }

}

