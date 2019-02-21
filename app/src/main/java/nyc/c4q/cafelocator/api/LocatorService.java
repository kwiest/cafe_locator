package nyc.c4q.cafelocator.api;


import io.reactivex.Observable;
import nyc.c4q.cafelocator.pojo.CoordinatesDetails;
import nyc.c4q.cafelocator.pojo.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by jervon.arnoldd on 2/13/19.
 */

public interface LocatorService {
    @GET("find_cafes.json")
    Observable<CoordinatesDetails> getCoordinatesWithKeyword(@Query("location") String location);

    @GET("api/cafe_search/fetch.json?longitude=<longitude>")
    Observable<Response> SearchByCoordinates(@Query("coordinates") boolean coordinates,
                                             @Query("latitude") double latitude,
                                             @Query("longitude") double longitude);
}
