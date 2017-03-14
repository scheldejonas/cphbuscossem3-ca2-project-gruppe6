package errorhandling;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.jersey.api.NotFoundException;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class NotFoundExceptionMapper implements ExceptionMapper<NotFoundException>
{
    static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Override
    public Response toResponse(NotFoundException exception)
    {
        int statusCode = 404;
        String msg = "Api address does not exist, uri does not exist";
        ErrorMessage err = new ErrorMessage(msg, statusCode, exception.getStackTrace());
        Response res = Response.status(statusCode).entity(gson.toJson(err)).type(MediaType.APPLICATION_JSON).build();
        return res;
    }
}
