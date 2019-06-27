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
import Devices.Logopedie.model.Level;
import Devices.Logopedie.model.Score;
import Devices.Logopedie.model.Woord;
import Devices.Logopedie.services.ChildService;
import Devices.Logopedie.services.ServiceProvider;
import Devices.Logopedie.services.WoordService;

@Path("woorden")
public class WoordenResource {
    @Path("/get-all")
    @GET
    @Produces("application/json")
    public String getWoorden() throws SQLException {
        WoordService service = ServiceProvider.getWoordService();
        System.out.println(service);

        JsonArrayBuilder jab = Json.createArrayBuilder();

        for (Woord l : service.findAll()) {
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
}