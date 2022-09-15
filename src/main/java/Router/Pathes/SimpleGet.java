package Router.Pathes;

import Router.PathHandler;
import constants.HTTPMethod;
import constants.StatusCode;
import request.Request;
import response.Response;

import java.util.List;

public class SimpleGet implements PathHandler {
    @Override
    public List<HTTPMethod> accessVerb() {
        return List.of(HTTPMethod.GET, HTTPMethod.HEAD);
    }

    @Override
    public Response getResponse(Request request) {
        return new Response(StatusCode.OK);
    }
}
