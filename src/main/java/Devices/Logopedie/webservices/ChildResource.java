package Devices.Logopedie.webservices;

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
import Devices.Logopedie.services.ChildService;
import Devices.Logopedie.services.ServiceProvider;

@Path("children")
public class ChildResource {
  

    @Path("/{id}")
    @GET
    //@RolesAllowed('logopedist')
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
			jab.add(job);
        }

        JsonArray array = jab.build();
        return array.toString();
    }


    @Path("/add-child")
    @POST
    //@RolesAllowed('logopedist')
    //@Produces("application/json")
    @Consumes({MediaType.APPLICATION_JSON,MediaType.TEXT_PLAIN})
    public Response createChild(String child) {
        ChildService service = ServiceProvider.getChildService();
        System.out.println(service);

        JSONObject childObj = new JSONObject(child);
        int logopedistid = (int) childObj.get("logopedistid");
        String username = (String) childObj.get("username"); 
        String password = (String) childObj.get("password");
        String email = (String) childObj.get("email");
        int phonenumber = (int) childObj.get("phonenumber");


        Child realChild = new Child(0, logopedistid, username, password, email, phonenumber, 1);
        service.createChild(realChild);

        return Response.ok().build();

    }

}