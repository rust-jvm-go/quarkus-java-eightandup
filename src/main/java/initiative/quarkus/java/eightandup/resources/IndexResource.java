package initiative.quarkus.java.eightandup.resources;

import initiative.quarkus.java.eightandup.domain.RedDwarf;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.jboss.resteasy.reactive.RestForm;

import java.util.logging.Logger;

@Path("/")
public class IndexResource {

    private final Logger logger;

    public IndexResource() {
        this.logger = Logger.getLogger(this.getClass().getName());
    }

    @Path("/hello")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        logger.info("IN hello...");
        return "Hello from RESTEasy Reactive";
    }

    @Path("/calculate_density")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.TEXT_PLAIN)
    @POST
    public String calculateDensity(@RestForm("brightness") double brightness) {

        logger.info("IN calculateDensity...");

        var redDwarf = new RedDwarf(brightness);
        var d = String.valueOf(redDwarf.density());
        logger.info(">>>>> density = " + d);

        logger.info("LEAVING calculateDensity...");
        return d;
    }
}