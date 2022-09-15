package Router.Pathes;

import Router.PathHandler;
import constants.HTTPMethod;
import constants.StatusCode;
import request.Request;
import response.Response;

import java.util.List;

public class SimplePost implements PathHandler {
    @Override
    public List<HTTPMethod> accessVerb() {
        return List.of(HTTPMethod.POST);
    }

    @Override
    public Response getResponse(Request request) {
        String body = request.body;

        Response response = new Response(StatusCode.OK, body);
        response.contentLengthHeader(body);
        response.body = body;
        return response;
    }
}
