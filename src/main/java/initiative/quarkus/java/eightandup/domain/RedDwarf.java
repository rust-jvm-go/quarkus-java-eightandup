package initiative.quarkus.java.eightandup.domain;

import initiative.quarkus.java.eightandup.services.IStarService;

public record RedDwarf(double brightness) implements IStarService {

    @Override
    public double density() {
        return brightness * 2;
    }
}
