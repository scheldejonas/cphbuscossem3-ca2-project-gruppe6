package rest;

import com.google.gson.*;
import com.google.gson.graph.GraphAdapterBuilder;
import control.Facade;
import entity.CityInfo;
import entity.Person;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.ArrayList;

@Path("company")
public class CompanyResource {

    private Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private Gson graphBuilder;
    private Facade facade;

    @Context
    private UriInfo context;

    public CompanyResource() {
        facade = new Facade();
    }

    /*
    //Testing Facade and DAO
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCityFromZipCode(@PathParam("id") String id){
        return Response
                .status(200)
                .header("Content-Type", "application/json")
                .entity(gson.toJson(facade.findSingleCity(zipCode), CityInfo.class))
                .build();
    }
    */
    
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