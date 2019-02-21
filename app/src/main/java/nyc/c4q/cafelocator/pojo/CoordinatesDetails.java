package nyc.c4q.cafelocator.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by jervon.arnoldd on 2/13/19.
 */

public class CoordinatesDetails {
    public Coords coords;

    public Coords getCoords() {
        return coords;
    }

    public void setCoords(Coords coords) {
        this.coords = coords;
    }

    public static class Coords {
        @SerializedName("latitude")
        @Expose
        public Double latitude;
        @SerializedName("longitude")
        @Expose
        public Double longitude;


        public void setLatitude(Double latitude) {
            this.latitude = latitude;
        }

        public void setLongitude(Double longitude) {
            this.longitude = longitude;
        }
    }
}
