package Devices.Logopedie.webservices;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonArrayBuilder;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

import Devices.Logopedie.model.Country;
import Devices.Logopedie.services.CountryService;
import Devices.Logopedie.services.ServiceProvider;

@Path("countries")
public class TestResource {


	@GET
	//@RolesAllowed("user")
	@Produces("application/json")
	public String getAllCountries() {
//		WorldService serviceold = ServiceProvider.getWorldService();
		CountryService service = ServiceProvider.getCountryService();

		JsonArrayBuilder jab = Json.createArrayBuilder();
		//String[] countries= {"land1","land2"};
		
		System.out.println(service);
		for (Country c : service.getAll()) {
			JsonObjectBuilder job = Json.createObjectBuilder();
//			job.add("Code", c.getCode());
//			job.add("Name", c.getName());
			job.add("name", c.getName());
			job.add("id", c.getId());
			jab.add(job);
		}

		JsonArray array = jab.build();
		return array.toString();
	}
}
