package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entity.Phone;
import dao.PhoneDao;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;


@Path("phones")
public class PhoneResource {
    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Context
    private UriInfo context;

    public PhoneResource() {
    }

    @GET
    @Path("/{phoneId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBingoBango(@PathParam("phoneId") String phoneIdString){
        Long phoneId = Long.parseLong(phoneIdString);
        return Response
                .status(200)
                .header("Content-Type", "application/json")
                .entity(gson.toJson(PhoneDao.getSingleton().findById(phoneId), Phone.class))
                .build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPhonesAsJson(@QueryParam("arguments") String arguments) {
        System.out.println("Arguments for getting all phones was: " + arguments);
        return Response
                .status(200)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
                .header("Access-Control-Allow-Credentials", "true")
                .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
                .header("Access-Control-Max-Age", "1209600")
                .entity(gson.toJson(PhoneDao.getSingleton().findAll(), Phone.class))
                .build();
    }

}
