package initiative.quarkus.java.eightandup.services.impl;

import initiative.quarkus.java.eightandup.domain.Page;
import initiative.quarkus.java.eightandup.services.IRxJava3Service;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.processors.BehaviorProcessor;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class RxJava3ServiceImpl implements IRxJava3Service {

    // Create some paged data
    private final ArrayList<Page<String>> pages = new ArrayList<>(3);

    public RxJava3ServiceImpl() {
        this.pages.add(new Page<>(List.of("one", "two"), Optional.of(1)));
        this.pages.add(new Page<>(List.of("three", "four"), Optional.of(2)));
        this.pages.add(new Page<>(List.of("five"), Optional.empty()));
    }

    // Fetch all pages and return the items contained in those pages, using the provided page fetcher function
    public <T> Flowable<T> fetchItems(Function<Integer, Single<Page<T>>> fetchPage) {
        // Processor issues page indices
        BehaviorProcessor<Integer> processor = BehaviorProcessor.createDefault(0);
        // When an index number is issued, fetch the corresponding page
        return processor.concatMap(index -> fetchPage.apply(index).toFlowable())
                // when returning the page, update the processor to get the next page (or stop)
                .doOnNext(page -> {
                    if (page.hasNext()) {
                        processor.onNext(page.getNextPageIndex());
                    } else {
                        processor.onComplete();
                    }
                })
                .concatMapIterable(Page::getElements);
    }

    // A function to fetch a page of our paged data
    public Single<Page<String>> pageFetcher(int index) {
        return Single.just(pages.get(index));
    }

    /*public static void main(String[] args) {
        fetchItems(StarServiceImpl::examplePageFetcher).subscribe(System.out::println);
    }*/
}
