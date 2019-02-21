package nyc.c4q.cafelocator.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by jervon.arnoldd on 2/13/19.
 */

public class Cafes {
    @SerializedName("Results near ")
    @Expose
    public List<ResultsNear> resultsNear;

    public void setResultsNear(List<ResultsNear> resultsNear) {
        this.resultsNear = resultsNear;
    }
}
