package nyc.c4q.cafelocator.pojo;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by jervon.arnoldd on 2/13/19.
 */

public class CafeDetails {

    @SerializedName("image_url")
    @Expose
    public String imageUrl;
    @SerializedName("latitude")
    @Expose
    public Double latitude;
    @SerializedName("longitude")
    @Expose
    public Double longitude;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("sanitized_address")
    @Expose
    public String sanitizedAddress;
    @SerializedName("timezone")
    @Expose
    public String timezone;

}
