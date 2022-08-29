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

public class EchoBody implements IRouter {
    @Override
    public Set<HTTPMethod> accessVerb() {
        return new LinkedHashSet<>(List.of(HTTPMethod.POST));
    }

    @Override
    public ServerResponse convertRequest(ClientRequest request) throws IOException {
        ResponseBuild response = new ResponseBuild();
        byte[] responseBody = request.body.getBytes();

        response.contentLengthHeader(responseBody.length);
        response.setBody(responseBody);
        return response.buildResponse();
    }
}
