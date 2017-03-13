package phone;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 * Created by scheldejonas on 13/03/2017.
 */
@Path("phones")
public class PhoneResource {
    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Context
    private UriInfo context;

    public PhoneResource() {
    }

//    @GET
//    @Path("/{addressAmount}")
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response getRandomAddress(@PathParam("addressAmount") String addressAmount, @QueryParam("arguments") String arguments) {
//        System.out.println("Testing amount recieved through pathparam: " + addressAmount);
//        System.out.println("Testing arguments recieved through p athparam: " + arguments);
//        return Response
//                .status(200)
//                .header("Access-Control-Allow-Origin", "*")
//                .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
//                .header("Access-Control-Allow-Credentials", "true")
//                .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
//                .header("Access-Control-Max-Age", "1209600")
//                .entity(
//                        // TODO: ælasdkfj
//                )
//                .build();
//
//    }
}
