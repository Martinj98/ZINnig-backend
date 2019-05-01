package Devices.Logopedie.webservices;

import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.*;

import Devices.Logopedie.model.Parent;
import Devices.Logopedie.services.ParentService;
import Devices.Logopedie.services.ServiceProvider;

@Path("parents")
public class ParentResource {
    @Path("/{childid}")
    @GET
    //@RolesAllowed('logopedist')
    @Produces("application/json")
    public String getChildParents(@PathParam("childid") int Childid){
        ParentService service = ServiceProvider.getParentService();

        JsonArrayBuilder jab = Json.createArrayBuilder();

        System.out.println(service);
        for (Parent p : service.getChildParents(Childid)) {
            JsonObjectBuilder job = Json.createObjectBuilder();
            job.add("id", p.getId());
            job.add("Childid", p.getChildid());
            job.add("email", p.getEmail());
            job.add("username", p.getUsername());
            job.add("password", p.getPassword());
            job.add("phonenumber", p.getPhonenumber());
            job.add("parent_notifications", p.getParent_notifications());
            jab.add(job);
        }

        JsonArray array = jab.build();
        return array.toString();
    }

    @Path("/add-parent/{childid}")
    @POST
    //@RolesAllowed('logopedist')
    @Consumes({MediaType.APPLICATION_JSON,MediaType.TEXT_PLAIN})
    public Response createParent(String parent, @PathParam("childid") int Childid){
        ParentService service = ServiceProvider.getParentService();

        System.out.println(service);
        JSONObject parentObj = new JSONObject(parent);
        String username = (String) parentObj.get("username");
        String password = (String) parentObj.get("password");
        String email = (String) parentObj.get("email");
        int phonenumber = (int) parentObj.get("phonenumber");
        int parent_notifications = (int) parentObj.get("parent_notifications");

        Parent realParent = new Parent(0, Childid, email, username, password, phonenumber, parent_notifications);
        service.createParent(realParent);

        return Response.ok().build();
    }
}