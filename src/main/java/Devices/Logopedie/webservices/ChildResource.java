package Devices.Logopedie.webservices;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.*;

import Devices.Logopedie.model.Child;
import Devices.Logopedie.model.GameType;
import Devices.Logopedie.model.Land;
import Devices.Logopedie.model.Score;
import Devices.Logopedie.model.Woord;
import Devices.Logopedie.services.ChildService;
import Devices.Logopedie.services.ServiceProvider;

@Path("children")
public class ChildResource {
    @Path("/login")
    @POST
    //@RolesAllowed('logopedist')
    @Consumes({MediaType.APPLICATION_JSON,MediaType.TEXT_PLAIN})
    public String login(String data) {
        System.out.println(data);
        ChildService service = ServiceProvider.getChildService();

        JsonArrayBuilder jab = Json.createArrayBuilder();

        System.out.println(service);
        JSONObject dataObj = new JSONObject(data);
        String username = (String) dataObj.get("username");
        String password = (String) dataObj.get("password");

        String result = service.login(username, password);

        int id = service.getId(username);

        JsonObjectBuilder job = Json.createObjectBuilder();
        job.add("username", username);
        job.add("id", id);
        job.add("token", result);
        jab.add(job);

        if (result.equals("Authorisation failed")){
            return null;
        } else {
            JsonArray array = jab.build();
            System.out.println(array.toString());
            return array.toString();
        }
    }

    @Path("/{id}")
    @GET
    // @RolesAllowed('logopedist')
    @Produces("application/json")
    public String getChild(@PathParam("id") int id) {
        ChildService service = ServiceProvider.getChildService();

        JsonArrayBuilder jab = Json.createArrayBuilder();

        System.out.println(service);
        for (Child l : service.selectSpecificChild(id)) {
            JsonObjectBuilder job = Json.createObjectBuilder();
            job.add("id", l.getId());
            job.add("logopedistid", l.getLogopedistid());
            job.add("username", l.getUsername());
            job.add("password", l.getPassword());
            job.add("email", l.getEmail());
            job.add("phonenumber", l.getPhonenumber());
            job.add("child_notifications", l.getChild_notifications());
            job.add("munten", l.getMunten());
            if (l.getHuiswerk() != null) {
                job.add("huiswerk", l.getHuiswerk());
            } else {
                job.add("huiswerk", "Geen Huiswerk");
            }
            jab.add(job);
        }

        JsonArray array = jab.build();
        return array.toString();
    }

    @Path("/voortgang/{id}")
    @GET
    // @RolesAllowed('logopedist')
    @Produces("application/json")
    public String getVoortgang(@PathParam("id") int id) {
        int levelid;
        int landid;
        ChildService service = ServiceProvider.getChildService();

        JsonArrayBuilder jab = Json.createArrayBuilder();

        System.out.println(service);
        for (Score l : service.selectVoortgang(id)) {
            JsonObjectBuilder job = Json.createObjectBuilder();
            job.add("levelid", l.getLevelid());
            levelid = l.getLevelid();
            job.add("childid", l.getChildid());
            job.add("landid", l.getLandid());
            landid = l.getLandid();
            System.out.println(landid);
            job.add("score_goed", l.getScore_goed());
            job.add("score_fout", l.getScore_fout());
            job.add("progress", l.getProgress());

            for (GameType g : service.selectGametype(levelid)) {
                job.add("levelname", g.getName());
            }

            for (Land e : service.selectLevelLand(landid)) {
                job.add("landid", e.getId());
                job.add("landcategory", e.getCategory());
                job.add("landname", e.getName());
                job.add("landdescription", e.getDescription());
            }

            jab.add(job);
        }

        JsonArray array = jab.build();
        return array.toString();
    }

    @Path("/add-child")
    @POST
    // @RolesAllowed('logopedist')
    // @Produces("application/json")
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN })
    public Response createChild(String child) {
        ChildService service = ServiceProvider.getChildService();
        System.out.println(service);

        JSONObject childObj = new JSONObject(child);
        int logopedistid = (int) childObj.get("logopedistid");
        String username = (String) childObj.get("username");
        String password = (String) childObj.get("password");
        String email = (String) childObj.get("email");
        int phonenumber = (int) childObj.get("phonenumber");

        Child realChild = new Child(0, logopedistid, username, password, email, phonenumber, 1, null, 0, null);
        service.createChild(realChild);

        return Response.ok().build();

    }

    @Path("/update-photo/{id}")
    @POST
    // @RolesAllowed('logopedist')
    @Consumes("text/plain")
    public Response updatePhoto(@PathParam("id") int id, String photo) {
        ChildService service = ServiceProvider.getChildService();
        System.out.println(service);

        service.updatePhoto(id, photo);

        return Response.ok().build();
    }

    @Path("/get-photo/{id}")
    @GET
    @Produces("application/json")
    public String getPhoto(@PathParam("id") int id) throws SQLException {
        ChildService service = ServiceProvider.getChildService();
        System.out.println(service);

        String photo = service.getPhoto(id);

        JsonArrayBuilder jab = Json.createArrayBuilder();
        JsonObjectBuilder job = Json.createObjectBuilder();
        job.add("photo",photo);
        jab.add(job);
        JsonArray array = jab.build();
        return array.toString();
    

    }

    @Path("/upload-score")
    @POST
    @Consumes({ MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN })
    public Response uploadScore(final String score) throws SQLException {
        ChildService service = ServiceProvider.getChildService();
        System.out.println("Someone uploaded their score");
        
        JSONObject scoreObj = new JSONObject(score);
        int Levelid = (int) scoreObj.get("Levelid");
        int Childid = (int) scoreObj.get("Childid");
        int Landid = (int) scoreObj.get("Landid");
        String score_goed = (String) scoreObj.get("score_goed");
        String score_fout = (String) scoreObj.get("score_fout");
        int progress = (int) scoreObj.get("progress");

        Score realScore = new Score(Levelid, Childid, Landid, score_goed, score_fout, progress);
        service.uploadScore(realScore);

        return Response.ok().build();
    }

    @Path("/goodwords")
    @POST
    @Produces("application/json")
    @Consumes(MediaType.APPLICATION_JSON)
    public String goodWords(final String list) throws SQLException {
        ChildService service = ServiceProvider.getChildService();
        List<Integer> intlist = new ArrayList<Integer>();
        System.out.println("Someone is checking their good words");

        JSONArray idlist = new JSONArray(list);

        for (Object json : idlist) {
            String test = json.toString();
            JSONObject jsonObj = new JSONObject(test);
            intlist.add(jsonObj.getInt("id"));
        }

        JsonArrayBuilder jab = Json.createArrayBuilder();

        for (Woord l : service.selectGoodWords(intlist)) {
            JsonObjectBuilder job = Json.createObjectBuilder();
            job.add("id", l.getId());
            job.add("woord", l.getWoord());
            job.add("type", l.getType());
            job.add("moeilijkheidsgraad", l.getMoeilijkheidsgraad());
            jab.add(job);
        }
        JsonArray array = jab.build();
        return array.toString();
    }

    @Path("/badwords")
    @POST
    @Produces("application/json")
    @Consumes(MediaType.APPLICATION_JSON)
    public String badWords(final String list) throws SQLException {
        ChildService service = ServiceProvider.getChildService();
        List<Integer> intlist = new ArrayList<Integer>();
        System.out.println("Someone is checking their good words");

        JSONArray idlist = new JSONArray(list);

        for (Object json : idlist) {
            String test = json.toString();
            JSONObject jsonObj = new JSONObject(test);
            intlist.add(jsonObj.getInt("id"));
        }

        JsonArrayBuilder jab = Json.createArrayBuilder();

        for (Woord l : service.selectGoodWords(intlist)) {
            JsonObjectBuilder job = Json.createObjectBuilder();
            job.add("id", l.getId());
            job.add("woord", l.getWoord());
            job.add("type", l.getType());
            job.add("moeilijkheidsgraad", l.getMoeilijkheidsgraad());
            jab.add(job);
        }
        JsonArray array = jab.build();
        return array.toString();
    }

    @Path("/updatemunten/{id}")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public boolean updateMunten(@PathParam("id") int id, final String count) throws SQLException {
        ChildService service = ServiceProvider.getChildService();
        System.out.println("User is updating their coins");

        JSONObject jsonObj = new JSONObject(count);
        int munten = jsonObj.getInt("count");

        if(service.updateMunten(munten, id)) {
            return true;
        } else {
            return false;
        }
    }



}