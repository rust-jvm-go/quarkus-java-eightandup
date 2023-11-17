package initiative.quarkus.java.eightandup.resources;

import initiative.quarkus.java.eightandup.services.IRxJava3Service;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Path("/lambda")
public class LambdaResource {

    private final Logger logger;

    @Inject
    IRxJava3Service rxJava3ServiceImpl;

    public LambdaResource() {
        this.logger = Logger.getLogger(this.getClass().getName());
    }

    @Path("/basic")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String doBasic() {

        logger.info("IN doBasic...");

        final List<String> friends = Arrays.asList("Brian", "Nate", "Neal", "Raju", "Sara", "Scott");
        var concat = friends
                .stream()
                .collect(Collectors.joining(" "));

        logger.info(">>>>> concat = " + concat);

        logger.info("LEAVING doBasic...");

        return concat;
    }

    @Path("/rxjava3")
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String doRxJava3WithoutRecursion() {

        logger.info("IN doRxJava3WithoutRecursion...");

        var f = rxJava3ServiceImpl
                .fetchItems(rxJava3ServiceImpl::pageFetcher)
                .subscribe(logger::info);

        logger.info("##### Disposing Flowable...");
        f.dispose();
        if (f.isDisposed()) {
            logger.info("##### Flowable was disposed.");
        } else {
            logger.info("##### Flowable was not disposed.");
        }

        logger.info("LEAVING doRxJava3WithoutRecursion...");

        return "OK";
    }
}
