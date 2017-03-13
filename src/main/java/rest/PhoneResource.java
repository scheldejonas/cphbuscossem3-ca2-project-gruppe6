package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entity.Phone;
import phone.PhoneDao;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;


@Path("phone")
public class PhoneResource {
    Gson gson = new GsonBuilder().setPrettyPrinting().create();

    @Context
    private UriInfo context;

    public PhoneResource() {
    }

    @GET
    @Path("/bingo")
    @Produces(MediaType.APPLICATION_JSON)
    public String getBingoBango(){
        Phone pho = new Phone();
        pho.setId(3);
        pho.setNumber("343434343");
        return gson.toJson(pho, Phone.class);
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getRandomAddress(@QueryParam("arguments") String arguments) {
        System.out.println("Testing amount recieved through Header or Path Arguments: " + arguments);
        return Response
                .status(200)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Headers", "origin, content-type, accept, authorization")
                .header("Access-Control-Allow-Credentials", "true")
                .header("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS, HEAD")
                .header("Access-Control-Max-Age", "1209600")
                .entity(PhoneDao.getSingleton().findAll())
                .build();
    }

}
