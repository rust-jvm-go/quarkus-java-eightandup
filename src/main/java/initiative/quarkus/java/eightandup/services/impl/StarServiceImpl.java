package initiative.quarkus.java.eightandup.services.impl;

import initiative.quarkus.java.eightandup.services.IStarService;
import jakarta.enterprise.context.ApplicationScoped;
import lombok.Builder;
import lombok.Setter;

import java.math.BigDecimal;

@ApplicationScoped
@Builder
@Setter
public class StarServiceImpl implements IStarService {

    private BigDecimal brightness;

    public StarServiceImpl() {
        this.brightness = BigDecimal.valueOf(1);
    }

    public StarServiceImpl(BigDecimal brightness) {
        this.brightness = brightness;
    }

    /**
     * @return Brightness
     */
    @Override
    public BigDecimal brightness() {
        return this.brightness;
    }

    /**
     * @return Density = Brightness * 2
     */
    @Override
    public BigDecimal density() {
        return this.brightness().multiply(BigDecimal.valueOf(2));
    }

    /**
     * @param brightness Brightness
     */
    @Override
    public void setBrightness(BigDecimal brightness) {
        this.brightness = brightness;
    }
}
