package Devices.Logopedie.webservices;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import org.json.*;

import Devices.Logopedie.model.Child;
import Devices.Logopedie.model.Logopedist;
import Devices.Logopedie.services.LogopedistService;
import Devices.Logopedie.services.ChildService;
import Devices.Logopedie.services.ServiceProvider;

@Path("logopedist")
public class LogopedistResource {
    @Path("/login")
    @POST
    //@RolesAllowed('logopedist')
    @Consumes({MediaType.APPLICATION_JSON,MediaType.TEXT_PLAIN})
    public String login(String data) {
        System.out.println(data);
        LogopedistService service = ServiceProvider.getLogopedistService();

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

    @Path("/children/{id}")
    @GET
    //@RolesAllowed('logopedist')
    @Produces("application/json")
    public String GetOwnChildren(@PathParam("id") int id) {
        ChildService service = ServiceProvider.getChildService();

        JsonArrayBuilder jab = Json.createArrayBuilder();

        System.out.println(service);
        for (Child l : service.getOwn(id)) {
            JsonObjectBuilder job = Json.createObjectBuilder();
            job.add("id", l.getId());
			job.add("logopedistid", l.getLogopedistid());
            job.add("username", l.getUsername());
			job.add("password", l.getPassword());
            job.add("email", l.getEmail());
            job.add("phonenumber", l.getPhonenumber());
            job.add("child_notifications", l.getChild_notifications());
            if (l.getChild_photo() != null) {
                job.add("child_photo", l.getChild_photo());
            }
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

    @Path("/{id}")
    @GET
    @Produces("application/json")
    public String GetLogopedist(@PathParam("id") int id) {
        LogopedistService service = ServiceProvider.getLogopedistService();
        System.out.println(service);
        JsonArrayBuilder jab = Json.createArrayBuilder();

        for (Logopedist l : service.getLogopedist(id)) {
            JsonObjectBuilder job = Json.createObjectBuilder();
            job.add("id", l.getId());
            job.add("praktijk_id", l.getPraktijk_id());
            job.add("email", l.getEmail());
            job.add("username", l.getUsername());
            job.add("phonenumber", l.getPhonenumber());
            jab.add(job);
        }

        JsonArray array = jab.build();
        return array.toString();
    }

}