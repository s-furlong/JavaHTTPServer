package Router;

import constants.HTTPMethod;
import request.ClientRequest;
import response.ServerResponse;

import java.io.IOException;
import java.util.Set;

public interface IRouter {
    Set<HTTPMethod> accessVerb();

    ServerResponse convertRequest(ClientRequest request) throws IOException;

}
