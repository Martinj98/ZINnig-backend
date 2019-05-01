package Devices.Logopedie.webservices;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import Devices.Logopedie.model.Child;
import Devices.Logopedie.services.ChildService;
import Devices.Logopedie.services.ServiceProvider;

@Path("children")
public class AllChildrenResource {


	@GET
	//@RolesAllowed('logopedist')
	@Produces("application/json")
	public String getAllChildren() {
		ChildService service = ServiceProvider.getChildService();

		JsonArrayBuilder jab = Json.createArrayBuilder();
		
		System.out.println(service);
		for (Child l : service.getAll()) {
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
}
