package errorhandling;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.core.Response;

@Provider
public class GeneralExceptionMapper implements ExceptionMapper<ServerException>
{

    @Override
    public Response toResponse(ServerException exception) {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        int statusCode = Response.Status.INTERNAL_SERVER_ERROR.getStatusCode();
        String msg = exception.getMessage();
        ErrorMessage err = new ErrorMessage(msg, statusCode, exception.getStackTrace());
        return Response
                .ok()
                .entity(gson.toJson(err))
                .build();
    }

}
