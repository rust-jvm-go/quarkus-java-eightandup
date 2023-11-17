package initiative.quarkus.java.eightandup.domain;

import lombok.Getter;

import java.util.List;
import java.util.Optional;

public class Page<T> {

    @Getter
    private List<T> elements;

    private final Optional<Integer> nextPageIndex;

    public Page(List<T> elements, Optional<Integer> nextPageIndex) {
        this.elements = elements;
        this.nextPageIndex = nextPageIndex;
    }

    public int getNextPageIndex() {
        return nextPageIndex.orElse(-1);
    }

    public boolean hasNext() {
        return nextPageIndex.isPresent();
    }
}
