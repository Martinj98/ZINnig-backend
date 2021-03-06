package Devices.Logopedie.webservices;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import Devices.Logopedie.model.Logopedist;
import Devices.Logopedie.services.LogopedistService;
import Devices.Logopedie.services.ServiceProvider;

@Path("logopedist")
public class TestResource {


	@GET
	//@RolesAllowed("user")
	@Produces("application/json")
	public String getAllLogopedisten() {
		LogopedistService service = ServiceProvider.getLogopedistService();

		JsonArrayBuilder jab = Json.createArrayBuilder();
		
		System.out.println(service);
		for (Logopedist l : service.getAll()) {
			JsonObjectBuilder job = Json.createObjectBuilder();	
			job.add("id", l.getId());
			job.add("praktijk_id", l.getPraktijk_id());
			job.add("email", l.getEmail());
			job.add("password", l.getPassword());
			job.add("username", l.getUsername());
			job.add("phonenumber", l.getPhonenumber());
			jab.add(job);
		}

		JsonArray array = jab.build();
		return array.toString();
	}
}
