package rest;

import com.google.gson.*;
import com.google.gson.graph.GraphAdapterBuilder;
import control.Facade;
import entity.Info;
import entity.Person;
import errorhandling.ServerException;

import javax.persistence.NoResultException;
import javax.ws.rs.*;
import javax.ws.rs.core.*;
import java.util.ArrayList;

@Path("person")
public class PersonResource {

    private Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private Gson graphBuilder;
    private Facade facade = Facade.getSingleton();

    @Context
    private UriInfo context;

    public PersonResource() {
    }

    @GET
    @Path("/zip/{cityZipCode}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPeopleFromZipCode(@PathParam("cityZipCode") String zipCode){
        Gson gson = getGraphBuilder();
        ArrayList<Person> people = facade.findPeopleFromZipcode(zipCode);
        String s = gson.toJson(people, ArrayList.class);
        String formatted = getFormattedJSON(s);
        if (formatted.isEmpty() || formatted.equals("")) {
            throw new NoResultException("No person from zip code: " + zipCode + " was found!");
        }
        return Response
                .status(200)
                .header("Content-Type", "application/json")
                .entity(formatted)
                .build();
    }


    @GET
    @Path("/hobby/{hobby}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPeopleFromHobby(@PathParam("hobby") String hobby){
        Gson gson = getGraphBuilder();
        String s = gson.toJson(facade.findPeopleFromHobby(hobby), ArrayList.class);
        String formatted = getFormattedJSON(s);
        if (formatted.isEmpty() || formatted.equals("")) {
            throw new NoResultException("No person with hobby: " + hobby + " was found!");
        }
        return Response
                .status(200)
                .header("Content-Type", "application/json")
                .entity(formatted)
                .build();
    }
    

    @GET
    @Path("/phone/{phoneNo}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPeopleFromPhone(@PathParam("phoneNo") String phoneNo) {
        Gson gson = getGraphBuilder();
        String s = gson.toJson(facade.findPeopleFromPhone(phoneNo), ArrayList.class);
        String formatted = getFormattedJSON(s);
        if (formatted.isEmpty() || formatted.equals("")) {
            throw new NoResultException("No person with number: " + phoneNo + " was found!");
        }
        return Response
                .status(200)
                .header("Content-Type", "application/json")
                .entity(formatted)
                .build();
    }

    @GET
    @Path("/address/{address}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPeopleFromAddress(@PathParam("address") String address) {
        Gson gson = getGraphBuilder();
        String s = gson.toJson(facade.findPeopleFromAddress(address), ArrayList.class);
        String formatted = getFormattedJSON(s);
        if (formatted.isEmpty() || formatted.equals("")) {
            throw new NoResultException("No person from address: " + address + " was found!");
        }
        return Response
                .status(200)
                .header("Content-Type", "application/json")
                .entity(formatted)
                .build();
    }
    
    @GET
    @Path("/name/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPeopleFromName(@PathParam("name") String name) {
        Gson gson = getGraphBuilder();
        String s = gson.toJson(facade.findPeopleFromName(name), ArrayList.class);
        String formatted = getFormattedJSON(s);
        if (formatted.isEmpty() || formatted.equals("")) {
            throw new NoResultException("No person with name: " + name + " was found!");
        }
        return Response
                .status(200)
                .header("Content-Type", "application/json")
                .entity(formatted)
                .build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPersonFromID(@PathParam("id") String id) {
        Person p = facade.getPersonByID(id);
        System.out.println("[PersonResource]: " + p.toString());
        Gson gson = getGraphBuilder();
        String s = gson.toJson(p, Person.class);
        String formatted = formatSingleJSON(s);
        if (formatted.isEmpty() || formatted.equals("")) {
            throw new NoResultException("No person with id: " + id + " was found!");
        }
        return Response
                .status(200)
                .header("Content-Type", "application/json")
                .entity(formatted)
                .build();
    }

    private Gson getGraphBuilder() {
        if (graphBuilder == null) {
            GsonBuilder builder = new GsonBuilder();
            new GraphAdapterBuilder()
                    .addType(Person.class)
                    .addType(Info.class)
                    .registerOn(builder);
            graphBuilder = builder.setPrettyPrinting().create();
        }
        return graphBuilder;
    }
    
    private String formatSingleJSON(String singleJSON) {
        JsonObject o = gson.fromJson(singleJSON, JsonObject.class);
        JsonElement ele = o.get("0x1");
        JsonArray arr = new JsonArray();
        arr.add(ele);
        return gson.toJson(arr);
    }
    
    private String getFormattedJSON(String fullJSON) {
        JsonArray jsonArray = getGraphBuilder().fromJson(fullJSON, JsonElement.class).getAsJsonArray();
        ArrayList<JsonObject> objects = new ArrayList<>();
        for (int i = 0; i < jsonArray.size(); i++) {
            objects.add(jsonArray.get(i).getAsJsonObject());
        }
        JsonArray jsonArrayNew = new JsonArray();
        for (JsonObject o : objects) {
            JsonElement ele = o.get("0x1");
            jsonArrayNew.add(ele);
        }
        return gson.toJson(jsonArrayNew);
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response recievePersonToPersist_AndReturnStatus(@QueryParam("email") String email, @QueryParam("firstName") String firstName, @QueryParam("lastName") String lastName) throws ServerException{
        Person person = new Person();
        person.setFirstName(firstName);
        person.setLastName(lastName);
        person.setEmail(email);
        facade.createPerson(person);
        return Response
                .status(200)
                .header("Content-Type", "application/json")
                .entity(gson.toJson((Person)person, Person.class))
                .build();
    }

    
}