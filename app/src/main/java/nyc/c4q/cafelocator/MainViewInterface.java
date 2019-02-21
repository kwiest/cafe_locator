package nyc.c4q.cafelocator;

import com.google.android.gms.maps.model.LatLng;

import java.util.List;

import nyc.c4q.cafelocator.pojo.ResultsNear;


/**
 * Created by jervon.arnoldd on 2/13/19.
 */

public interface MainViewInterface {
    void listOfCafesFound(List<ResultsNear> resultsNear);

    void loadCafesPosition(LatLng temp, String name);

    void sendKeywordLocation(LatLng location);

    void setRecyclePointer(int position);

    void sendStore(ResultsNear resultsNear);

    void nothingFound();
}
