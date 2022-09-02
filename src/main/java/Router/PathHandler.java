package Router;

import constants.HTTPMethod;
import request.ClientRequest;
import request.Request;
import response.Response;

import java.io.IOException;
import java.util.Set;

public interface PathHandler {

    Set<HTTPMethod> accessVerb();

    Response getResponse(Request request) throws IOException;

}
