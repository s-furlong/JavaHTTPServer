package Router.Pathes;

import Router.PathHandler;
import constants.HTTPMethod;
import constants.StatusCode;
import request.Request;
import response.Response;

import java.util.List;

public class SimpleGetWithBody implements PathHandler {
    @Override
    public List<HTTPMethod> accessVerb() {
        return List.of(HTTPMethod.GET, HTTPMethod.HEAD);
    }

    @Override
    public Response getResponse(Request request) {
        HTTPMethod verb = request.verb;
        String body = "Hello world";

        Response response = new Response(StatusCode.OK, body);
        if (verb == HTTPMethod.GET) {
            response.contentLengthHeader(body);
        }

        return response;
    }
}
