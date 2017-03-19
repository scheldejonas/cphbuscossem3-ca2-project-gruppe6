package rest;

import com.google.gson.*;
import com.google.gson.graph.GraphAdapterBuilder;
import control.Facade;
import entity.Company;
import entity.Info;
import errorhandling.ServerException;

import javax.persistence.NoResultException;
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
    private Facade facade = Facade.getSingleton();

    @Context
    private UriInfo context;

    public CompanyResource() {
    }
    
    @GET
    @Path("/zip/{cityZipCode}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCompanyFromZipCode(@PathParam("cityZipCode") String zipCode){
        Gson gson = getGraphBuilder();
        ArrayList<Company> companies = facade.findCompaniesFromZip(zipCode);
        String s = gson.toJson(companies, ArrayList.class);
        String formatted = getFormattedJSON(s);
        if (formatted.isEmpty() || formatted.equals("")) {
            throw new NoResultException("No company from zip code: " + zipCode + " was found!");
        }
        return Response
                .status(Response.Status.OK)
                .entity(formatted)
                .build();
    }
    
    @GET
    @Path("/address/{address}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCompanyFromAddress(@PathParam("address") String address){
        Gson gson = getGraphBuilder();
        ArrayList<Company> companies = facade.findCompaniesFromAddress(address);
        String s = gson.toJson(companies, ArrayList.class);
        String formatted = getFormattedJSON(s);
        if (formatted.isEmpty() || formatted.equals("")) {
            throw new NoResultException("No company from address: " + address + " was found!");
        }
        return Response
                .status(Response.Status.OK)
                .entity(formatted)
                .build();
    }
    
    @GET
    @Path("/phone/{phoneNo}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCompanyFromPhone(@PathParam("phoneNo") String phoneNo){
        Gson gson = getGraphBuilder();
        Company company = facade.findCompanyFromPhone(phoneNo);
        String s = gson.toJson(company, Company.class);
        String formatted = formatSingleJSON(s);
        if (formatted.isEmpty() || formatted.equals("")) {
            throw new NoResultException("No company with phone: " + phoneNo + " was found!");
        }
        return Response
                .status(Response.Status.OK)
                .entity(formatted)
                .build();
    }
    
    @GET
    @Path("/cvr/{cvr}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCompanyFromCVR(@PathParam("cvr") String cvr){
        Gson gson = getGraphBuilder();
        Company company = facade.findCompanyFromCVR(cvr);
        String s = gson.toJson(company, Company.class);
        String formatted = formatSingleJSON(s);
        if (formatted.isEmpty() || formatted.equals("")) {
            throw new NoResultException("No company with CVR: " + cvr + " was found!");
        }
        return Response
                .status(Response.Status.OK)
                .entity(formatted)
                .build();
    }
    
    @GET
    @Path("/name/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCompanyFromName(@PathParam("name") String name) throws ServerException {
        Gson gson = getGraphBuilder();
        Company company = facade.findCompanyFromName(name);
        String s = gson.toJson(company, Company.class);
        String formatted = formatSingleJSON(s);
        if (formatted.isEmpty() || formatted.equals("")) {
//            throw new NoResultException("No company with name: " + name + " was found!");
            throw new ServerException("No company with name: " + name + " was found!");
        }
        return Response
                .status(Response.Status.OK)
                .entity(formatted)
                .build();
    }
    
    private Gson getGraphBuilder() {
        if (graphBuilder == null) {
            GsonBuilder builder = new GsonBuilder();
            new GraphAdapterBuilder()
                    .addType(Company.class)
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
    
}