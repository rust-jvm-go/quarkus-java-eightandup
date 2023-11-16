package initiative.quarkus.java.eightandup.domain;

import initiative.quarkus.java.eightandup.services.IStarService;
import lombok.Builder;

import java.math.BigDecimal;

@Builder
public record RedDwarf(BigDecimal brightness) implements IStarService {

    @Override
    public BigDecimal density() {
        return brightness.multiply(BigDecimal.valueOf(2));
    }
}
