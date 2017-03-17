package errorhandling;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import javax.ws.rs.core.Response;

@Provider
public class GeneralExceptionMapper implements ExceptionMapper<Exception>
{
    static Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Override
    public Response toResponse(Exception exception)
    {
        int statusCode = 500;
        String msg = "Internal server error. We are sorry try at a later time!";

        ErrorMessage err = new ErrorMessage(msg, statusCode, exception.getStackTrace());
        Response res = Response.status(statusCode).entity(gson.toJson(err)).type(MediaType.APPLICATION_JSON).build();
        return res;
    }
}
