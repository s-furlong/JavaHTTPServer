package Router.Pathes;

import Router.PathHandler;
import constants.HTTPMethod;
import constants.StatusCode;
import request.Request;
import response.Response;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class HeadRequest implements PathHandler {
    @Override
    public List<HTTPMethod> accessVerb() {
        return List.of(HTTPMethod.HEAD, HTTPMethod.OPTIONS);
    }

    @Override
    public Response getResponse(Request request) throws IOException {
        var response = new Response(StatusCode.OK);
        HTTPMethod verb = request.verb;
        if (verb == HTTPMethod.OPTIONS) {
            response.addAllowHeader(accessVerb());
        }
        return new Response(StatusCode.OK);
    }
}


