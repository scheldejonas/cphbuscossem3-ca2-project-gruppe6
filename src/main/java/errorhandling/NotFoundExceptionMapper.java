package errorhandling;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.jersey.api.NotFoundException;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class NotFoundExceptionMapper implements ExceptionMapper<NotFoundException>
{
    static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Override
    @Produces(MediaType.APPLICATION_JSON)
    public Response toResponse(NotFoundException exception)
    {
        int statusCode = Response.Status.NOT_FOUND.getStatusCode();
        String msg = "Api address does not exist, uri does not exist";
        ErrorMessage err = new ErrorMessage(msg, statusCode, exception.getStackTrace());
        return Response
                .ok()
                .entity(gson.toJson(err))
                .build();
    }

}