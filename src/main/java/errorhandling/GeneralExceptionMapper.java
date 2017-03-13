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
        if(exception.getMessage().equalsIgnoreCase("HTTP 404 Not Found"))
        {
            String msg = "The page/service you requested does not exist";
            ErrorMessage err = new ErrorMessage(msg,404);
            Response res = Response.status(404).entity(gson.toJson(err)).type(MediaType.APPLICATION_JSON).build();
            return res;
        }
        int statusCode = 500;
        String msg = "Internal server errorhandling, we are sorry";
        //String msg = exception.getMessage();

        ErrorMessage err = new ErrorMessage(msg,statusCode);
        Response res = Response.status(statusCode).entity(gson.toJson(err)).type(MediaType.APPLICATION_JSON).build();
        return res;
    }
}
