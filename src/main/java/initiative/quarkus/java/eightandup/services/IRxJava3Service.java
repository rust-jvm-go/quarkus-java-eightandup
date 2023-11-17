package initiative.quarkus.java.eightandup.services;

import initiative.quarkus.java.eightandup.domain.Page;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.functions.Function;

public interface IRxJava3Service {

    // Fetch all pages and return the items contained in those pages, using the provided page fetcher function
    public <T> Flowable<T> fetchItems(Function<Integer, Single<Page<T>>> fetchPage);

    // A function to fetch a page of our paged data
    public Single<Page<String>> pageFetcher(int index);
}
