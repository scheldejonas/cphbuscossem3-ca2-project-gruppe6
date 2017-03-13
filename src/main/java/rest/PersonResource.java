package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import control.Facade;
import entity.Phone;
import dao.PersonDao;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;


@Path("persons")
public class PersonResource {
    private Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private Facade facade = Facade.getSingleton();

    @Context
    private UriInfo context;

    public PersonResource() {
    }

    @GET
    @Path("/{personId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOnePersonFromId(@PathParam("personId") String personIdString){
        Long phoneId = Long.parseLong(personIdString);
        return Response
                .status(200)
                .header("Content-Type", "application/json")
                .entity(gson.toJson(PersonDao.getSingleton().findById(phoneId), Phone.class))
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
                .entity(gson.toJson(PersonDao.getSingleton().findAll(), Phone.class))
                .build();
    }

}
