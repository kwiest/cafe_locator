package nyc.c4q.cafelocator.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by jervon.arnoldd on 2/13/19.
 */

public class Response {
    @SerializedName("cafes")
    @Expose
    public Cafes cafes;
    @SerializedName("no_results")
    @Expose
    public Boolean noResults;


    public void setCafes(Cafes cafes) {
        this.cafes = cafes;
    }
}
