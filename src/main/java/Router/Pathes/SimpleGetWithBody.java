package Router.Pathes;

import Router.IRouter;
import constants.HTTPMethod;
import request.ClientRequest;
import response.ResponseBuild;
import response.ServerResponse;

import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

public class SimpleGetWithBody implements IRouter {
    String specBody = "Hello World";

    @Override
    public Set<HTTPMethod> accessVerb() {
        return new LinkedHashSet<>(List.of(HTTPMethod.GET, HTTPMethod.HEAD));
    }

    @Override
    public ServerResponse convertRequest(ClientRequest request) throws IOException {
        ResponseBuild responseBuild = new ResponseBuild();
        HTTPMethod verb = request.verb;
        byte[] body = specBody.getBytes();

        responseBuild.contentLengthHeader(body.length);

        if (verb == HTTPMethod.GET) {
            responseBuild.setBody(body);
        }

        return responseBuild.buildResponse();
    }
}
