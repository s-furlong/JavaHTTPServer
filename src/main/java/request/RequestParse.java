package request;

import constants.Format;
import constants.HTTPMethod;
import constants.Path;

import java.util.HashMap;

public class RequestParse {
    private final String clientResquest;

    public RequestParse(String clientRequest) {
        this.clientResquest = clientRequest;
    }

    public String clientHeadRequest() {
        return clientResquest.split(Format.NEWLINE)[0];
    }

    public HTTPMethod verb() {
        String serverRequest = clientHeadRequest().split(" ")[0];
        return HTTPMethod.findVerb(serverRequest);
    }

    public Path path() {
        String serverRequest = clientHeadRequest().split(" ")[1];
        return Path.findPath(serverRequest);
    }

    public HashMap<String, String> headers() {
        String request = clientResquest.split(Format.BLANKLINE)[0];
        String[] headArray = request.split(Format.NEWLINE);

        HashMap<String, String> pairedHeaders = new HashMap<>();
        for (int i = 1; i < headArray.length; i++) {
            String[] headerKeyValue = headArray[i].split(": ");
            if (headerKeyValue.length == 2) {
                String key = headerKeyValue[0];
                String value;
                if (i == headerKeyValue.length - 1) {
                    value = headerKeyValue[1].split(Format.BLANKLINE, 2)[0];
                    pairedHeaders.put(key, value);
                } else {
                    value = headerKeyValue[1];
                    pairedHeaders.put(key, value);
                }
            }
        }
        return pairedHeaders;
    }


}
