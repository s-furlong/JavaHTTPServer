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

// Rename this to RequestFactory -- pass into it a raw string that somebody else has read
// new RequestFactory() => this can be injected into your HTTPServer class somewhere
// requestFactory.createRequest(rawString)
public class RequestLogic {
    private final InputOutputWrappers requestServerMethods;

    // TD: This is passing the entire instance of an InputOutputWrapper -- do we really need all that?
    public RequestLogic(InputOutputWrappers requestServerMethods) {
        this.requestServerMethods = requestServerMethods;
    }

    // This is a hard one to name, which means it is probably doing too many things
    // That is, it is both reading the raw string, and converting it to a Request.
    // Can we break these out?
    public ClientRequest inputRead() throws IOException {
        // TD Where is the raw string going to come from?
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

