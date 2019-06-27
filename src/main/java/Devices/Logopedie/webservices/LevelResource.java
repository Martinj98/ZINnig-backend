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

@Path("level")
public class LevelResource {
    @Path("/get-woorden/{id}")
    @GET
    @Produces("application/json")
    public String getWoorden(@PathParam("id") int id) throws SQLException {
        ChildService service = ServiceProvider.getChildService();
        System.out.println(service);

        JsonArrayBuilder jab = Json.createArrayBuilder();

        List<Integer> intlist = service.selectLevelWords(id);
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

    @Path("/{id}")
    @GET
    @Produces("application/json")
    public String getLevel(@PathParam("id") int id) throws SQLException {
        ChildService service = ServiceProvider.getChildService();
        System.out.println(service);

        
        JsonArrayBuilder jab = Json.createArrayBuilder();

        System.out.println(service);
        for (Level l : service.selectLevel(id)) {
            int gametypeid;
            JsonObjectBuilder job = Json.createObjectBuilder();
            job.add("levelid", l.getId());
            job.add("Game_typeid", l.getGame_typeid());
            gametypeid = l.getGame_typeid();
            job.add("niveau", l.getNiveau());

            for (GameType g : service.selectGametype(gametypeid)) {
                job.add("levelnaam", g.getName());
            }
            jab.add(job);
        }

        JsonArray array = jab.build();
        return array.toString();
    }
}