package nyc.c4q.cafelocator;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import nyc.c4q.cafelocator.api.LocatorService;
import nyc.c4q.cafelocator.pojo.Cafes;
import nyc.c4q.cafelocator.pojo.CoordinatesDetails;
import nyc.c4q.cafelocator.pojo.Response;
import nyc.c4q.cafelocator.pojo.ResultsNear;

/**
 * Created by jervon.arnoldd on 2/20/19.
 */

public class MockApiService implements LocatorService {
    @Override
    public Observable<CoordinatesDetails> getCoordinatesWithKeyword(String location) {
        CoordinatesDetails coordinatesDetails = new CoordinatesDetails();
        CoordinatesDetails.Coords coords = new CoordinatesDetails.Coords();
        coords.setLatitude(40.6453531);
        coords.setLongitude(-74.0150373);
        coordinatesDetails.setCoords(coords);
        return Observable.just(coordinatesDetails);
    }

    @Override
    public Observable<Response> SearchByCoordinates(boolean coordinates, double latitude, double longitude) {
        Response testing = new Response();
        Cafes cafes = new Cafes();
        List<ResultsNear> resultsNears = new ArrayList<>();
        cafes.setResultsNear(resultsNears);
        testing.setCafes(cafes);
        return Observable.just(testing);
    }
}
