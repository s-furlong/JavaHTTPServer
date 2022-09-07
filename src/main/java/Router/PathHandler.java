package Router;

import constants.HTTPMethod;
import request.ClientRequest;
import request.Request;
import response.Response;

import java.io.IOException;
import java.util.List;
import java.util.Set;

public interface PathHandler {

    List<HTTPMethod> accessVerb();

    Response getResponse(Request request) throws IOException;

}
