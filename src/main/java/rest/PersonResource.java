package rest;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.graph.GraphAdapterBuilder;
import control.Facade;
import entity.CityInfo;
import entity.Hobby;
import entity.Info;
import entity.Person;

import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.ArrayList;
import java.util.List;

@Path("person")
public class PersonResource {

    private Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private Facade facade;

    @Context
    private UriInfo context;

    public PersonResource() {
        facade = new Facade();
    }

    @GET
    @Path("/zip/{cityZipCode}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPeopleFromZipCode(@PathParam("cityZipCode") String zipCode){
        GsonBuilder gsonBuilder = new GsonBuilder();
        new GraphAdapterBuilder()
                .addType(Person.class)
                .registerOn(gsonBuilder);
        Gson gson = gsonBuilder
                .setPrettyPrinting()
                .create();
        ArrayList<Person> people = facade.findPeopleFromZipcode(zipCode);
        String s = gson.toJson(people, ArrayList.class);
        System.out.println(s);

        return Response
                .status(200)
                .header("Content-Type", "application/json")
                .entity(s)
                .build();
    }

    //Testing Facade and DAO
    @GET
    @Path("/hobby/{hobby}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPeopleFromHobby(@PathParam("hobby") String hobby){
        GsonBuilder gsonBuilder = new GsonBuilder();
        new GraphAdapterBuilder().addType(Person.class).registerOn(gsonBuilder);
        Gson gson = gsonBuilder.setPrettyPrinting().create();

        String s = gson.toJson(facade.findPeopleFromHobby(hobby), ArrayList.class);
        //System.out.println(s);

        return Response
                .status(200)
                .header("Content-Type", "application/json")
                .entity(s)
                .build();
    }

    //Testing Facade and DAO
    @GET
    @Path("/phone/{phoneNo}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPeopleFromPhone(@PathParam("phoneNo") String phoneNo) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        new GraphAdapterBuilder().addType(Person.class).registerOn(gsonBuilder);
        Gson gson = gsonBuilder.setPrettyPrinting().create();

        String s = gson.toJson(facade.findPeopleFromPhone(phoneNo), ArrayList.class);

        return Response
                .status(200)
                .header("Content-Type", "application/json")
                .entity(s)
                .build();
    }

    //Testing Facade and DAO
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPersonFromID(@PathParam("id") String id) {

        GsonBuilder gsonBuilder = new GsonBuilder();
        new GraphAdapterBuilder().addType(Person.class).registerOn(gsonBuilder);
        Gson gson = gsonBuilder.setPrettyPrinting().create();

        Person p = facade.getPersonByID(id);
        System.out.println("[PersonResource]: " + p.toString());
        String s = gson.toJson(p, Person.class);

        return Response
                .status(200)
                .header("Content-Type", "application/json")
                .entity(s)
                .build();
    }

}