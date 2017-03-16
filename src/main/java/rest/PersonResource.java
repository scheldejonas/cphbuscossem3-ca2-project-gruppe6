package rest;

import com.google.gson.*;
import com.google.gson.graph.GraphAdapterBuilder;
import control.Facade;
import entity.CityInfo;
import entity.Hobby;
import entity.Info;
import entity.Person;

import javax.swing.*;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.ArrayList;
import java.util.List;

@Path("person")
public class PersonResource {

    private Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private Gson graphBuilder;
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
        Gson gson = getGraphBuilder();
        ArrayList<Person> people = facade.findPeopleFromZipcode(zipCode);
        String s = gson.toJson(people, ArrayList.class);
        String formatted = getFormattedJSON(s);
        return Response
                .status(200)
                .header("Content-Type", "application/json")
                .entity(formatted)
                .build();
    }

    //Testing Facade and DAO
    @GET
    @Path("/hobby/{hobby}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPeopleFromHobby(@PathParam("hobby") String hobby){
        Gson gson = getGraphBuilder();
        String s = gson.toJson(facade.findPeopleFromHobby(hobby), ArrayList.class);
        String formatted = getFormattedJSON(s);
        return Response
                .status(200)
                .header("Content-Type", "application/json")
                .entity(formatted)
                .build();
    }
    
    //Testing Facade and DAO
    @GET
    @Path("/phone/{phoneNo}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPeopleFromPhone(@PathParam("phoneNo") String phoneNo) {
        Gson gson = getGraphBuilder();
        String s = gson.toJson(facade.findPeopleFromPhone(phoneNo), ArrayList.class);
        String formatted = getFormattedJSON(s);
        
        return Response
                .status(200)
                .header("Content-Type", "application/json")
                .entity(formatted)
                .build();
    }

    //Testing Facade and DAO
    @GET
    @Path("/address/{address}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPeopleFromAddress(@PathParam("address") String address) {
        Gson gson = getGraphBuilder();
        String s = gson.toJson(facade.findPeopleFromAddress(address), ArrayList.class);
        String formatted = getFormattedJSON(s);

        return Response
                .status(200)
                .header("Content-Type", "application/json")
                .entity(formatted)
                .build();
    }

    //Testing Facade and DAO
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPersonFromID(@PathParam("id") String id) {
        Person p = facade.getPersonByID(id);
        System.out.println("[PersonResource]: " + p.toString());
        Gson gson = getGraphBuilder();
        String s = gson.toJson(p, Person.class);
        String formatted = formatSingleJSON(s);
        
        return Response
                .status(200)
                .header("Content-Type", "application/json")
                .entity(formatted)
                .build();
    }

    private Gson getGraphBuilder() {
        if (graphBuilder == null) {
            GsonBuilder builder = new GsonBuilder();
            new GraphAdapterBuilder().addType(Person.class).registerOn(builder);
            graphBuilder = builder.setPrettyPrinting().create();
        }
        return graphBuilder;
    }
    
    private String formatSingleJSON(String singleJSON) {
        JsonObject o = gson.fromJson(singleJSON, JsonObject.class);
        JsonElement ele = o.get("0x1");
        return gson.toJson(ele);
    }
    
    private String getFormattedJSON(String fullJSON) {
        JsonArray jsonArray = getGraphBuilder().fromJson(fullJSON, JsonElement.class).getAsJsonArray();
        ArrayList<JsonObject> objects = new ArrayList<>();
        for (int i = 0; i < jsonArray.size(); i++) {
            objects.add(jsonArray.get(i).getAsJsonObject());
        }
        String s = "";
        for (JsonObject o : objects) {
            JsonElement ele = o.get("0x1");
            s += gson.toJson(ele) + System.lineSeparator();
        }
        //System.out.println(ele);
        return s;
    }
    
}