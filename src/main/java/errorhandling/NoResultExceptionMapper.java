package errorhandling;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javax.persistence.NoResultException;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class NoResultExceptionMapper implements ExceptionMapper<NoResultException>
{
    static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Override
    public Response toResponse(NoResultException exception)
    {
        int statusCode = 204;
        String msg = "Requested resource does not exist. No content";
        ErrorMessage err = new ErrorMessage(msg, statusCode, exception.getStackTrace());
        Response res = Response.status(statusCode).entity(gson.toJson(err)).type(MediaType.APPLICATION_JSON).build();
        return res;
    }
}
