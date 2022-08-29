package Router.Pathes;

import Router.IRouter;
import constants.HTTPMethod;
import request.ClientRequest;
import response.ResponseBuild;
import response.ServerResponse;

import java.io.IOException;
import java.util.*;

public class HeadRequest implements IRouter {
    @Override
    public Set<HTTPMethod> accessVerb() {
        return new LinkedHashSet<>(List.of(HTTPMethod.HEAD, HTTPMethod.OPTIONS));
    }

    @Override
    public ServerResponse convertRequest(ClientRequest request) throws IOException {
        ResponseBuild response = new ResponseBuild();
        HTTPMethod verb = request.verb;

        if (verb == HTTPMethod.OPTIONS) {
            response.addAllowHeader(accessVerb());
        }
        return response.buildResponse();
    }
}
