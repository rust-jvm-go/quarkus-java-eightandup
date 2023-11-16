package initiative.quarkus.java.eightandup.resources;

import initiative.quarkus.java.eightandup.domain.RedDwarf;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.jboss.resteasy.reactive.RestForm;

import java.math.BigDecimal;
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
    public String calculateDensity(@RestForm("brightness") @NotNull @Valid BigDecimal brightness) {

        logger.info("IN calculateDensity, with live reload!...");

        var redDwarf1 = new RedDwarf(brightness);
        var d = String.valueOf(redDwarf1.density().toPlainString());
        logger.info(">>>>> density (record) = " + d);

        var redDwarf2 = RedDwarf
                .builder()
                .brightness(brightness)
                .build();
        d = String.valueOf(redDwarf2.density().toPlainString());
        logger.info(">>>>> density (lombok) = " + d);

        logger.info("LEAVING calculateDensity...");
        return d;
    }
}
