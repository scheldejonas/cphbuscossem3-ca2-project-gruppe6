package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import control.Facade;
import entity.CityInfo;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

@Path("company")
public class CompanyResource {

    private Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private Facade facade;

    @Context
    private UriInfo context;

    public CompanyResource() {
        facade = new Facade();
    }

    /*
    //Saved for "Schematic" purposes
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
                .entity(gson.toJson(PersonDao.getSingleton().findAll(), Person.class))
                .build();
    }
    */

    //Testing Facade and DAO
    @GET
    @Path("/{cityZipCode}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCityFromZipCode(@PathParam("cityZipCode") String zipCode){

        return Response
                .status(200)
                .header("Content-Type", "application/json")
                .entity(gson.toJson(facade.findSingleCity(zipCode), CityInfo.class))
                .build();
    }

}
