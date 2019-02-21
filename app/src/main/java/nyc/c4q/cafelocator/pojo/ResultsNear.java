package nyc.c4q.cafelocator.pojo;

import com.google.gson.JsonObject;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by jervon.arnoldd on 2/13/19.
 */

public class ResultsNear {




    @SerializedName("coming_soon")
    @Expose
    public String isComingSoon;

    @SerializedName("address")
    @Expose
    public String address;
    @SerializedName("id")
    @Expose
    public Integer id;
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
    @SerializedName("hours")
    @Expose
    public
    JsonObject jsonObject;

    @Expose
    private
    List<StoreHours> storeHoursList;

    private String street_address;
    private String city_address;

    public void setStoreHoursList(List<StoreHours> storeHoursList) {
        this.storeHoursList = storeHoursList;
    }

    public List<StoreHours> getStoreHoursList() {
        return storeHoursList;
    }

    public String getStreet_address() {
        return street_address;
    }

    public String getCity_address() {
        return city_address;
    }

    public void setStreet_address(String street_address) {
        this.street_address = street_address;
    }

    public void setCity_address(String city_address) {
        this.city_address = city_address;
    }
}
