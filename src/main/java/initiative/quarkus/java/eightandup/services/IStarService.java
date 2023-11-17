package initiative.quarkus.java.eightandup.services;

import java.math.BigDecimal;

public interface IStarService {

    BigDecimal brightness();

    BigDecimal density();

    void setBrightness(BigDecimal brightness);
}
