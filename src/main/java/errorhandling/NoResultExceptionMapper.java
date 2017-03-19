package errorhandling;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javax.persistence.NoResultException;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class NoResultExceptionMapper implements ExceptionMapper<NoResultException>
{
    static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Override
    @Produces(MediaType.APPLICATION_JSON)
    public Response toResponse(NoResultException exception)
    {
        int statusCode = Response.Status.NO_CONTENT.getStatusCode();
        String msg = exception.getMessage();
        ErrorMessage err = new ErrorMessage(msg, statusCode, exception.getStackTrace());
        return Response
                .ok()
                .entity(gson.toJson(err))
                .build();
    }
}
