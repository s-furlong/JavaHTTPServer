package Router.Pathes;

import Router.IRouter;
import constants.HTTPMethod;
import request.ClientRequest;
import response.ResponseBuild;
import response.ServerResponse;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class SimpleGet implements IRouter {
    @Override
    public Set<HTTPMethod> accessVerb() {
        return new LinkedHashSet<>(List.of(HTTPMethod.GET, HTTPMethod.HEAD));
    }

    @Override
    public ServerResponse convertRequest(ClientRequest request) {
        return new ResponseBuild().buildResponse();
    }
}
