package Router.Pathes;

import Router.PathHandler;
import constants.HTTPMethod;
import constants.StatusCode;
import request.Request;
import response.Response;

import java.util.List;

public class HeadRequest implements PathHandler {
    @Override
    public List<HTTPMethod> accessVerb() {
        return List.of(HTTPMethod.HEAD, HTTPMethod.OPTIONS);
    }

    @Override
    public Response getResponse(Request request) {

        HTTPMethod verb = request.verb;

        var response = new Response(StatusCode.OK);
        if (verb == HTTPMethod.OPTIONS) {
            response.addAllowHeader(accessVerb());
        }
        return response;
    }
}


