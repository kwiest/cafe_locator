package nyc.c4q.cafelocator.presenter;

import android.util.Log;

import com.google.android.gms.maps.model.LatLng;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import dagger.Module;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import nyc.c4q.cafelocator.MainPresenterInterface;
import nyc.c4q.cafelocator.MainViewInterface;
import nyc.c4q.cafelocator.api.LocatorService;
import nyc.c4q.cafelocator.pojo.CoordinatesDetails;
import nyc.c4q.cafelocator.pojo.Response;
import nyc.c4q.cafelocator.pojo.ResultsNear;
import nyc.c4q.cafelocator.pojo.StoreHours;

/**
 * Created by jervon.arnoldd on 2/13/19.
 */
@Module
public class MainPresenter implements MainPresenterInterface {
    private LocatorService locatorService;

    private MainViewInterface mainViewInter;
    private List<ResultsNear> listOfStores;
    private CompositeDisposable disposable = new CompositeDisposable();
    private Gson gson = new Gson();
    String[] temp;

    @Inject
    public MainPresenter(LocatorService locatorService) {
        this.locatorService = locatorService;
    }

    //for testing
    public MainPresenter(LocatorService apiService, MainViewInterface viewInterface) {
        locatorService = apiService;
        mainViewInter = viewInterface;
    }

    @Override
    public void setmainViewInter(MainViewInterface mainViewInter) {
        this.mainViewInter = mainViewInter;
    }

    @Override
    public void onDestroy() {
        mainViewInter = null;
        disposable.dispose();
    }

    @Override
    public void findStoreByName(int position) {
        mainViewInter.sendStore(listOfStores.get(position));
    }

    @Override
    public void getStoreList(String keyword) {
        disposable.add(getCoordinatesWithKeyword(keyword)
                .flatMap(coordinatesDetails -> searchByCoordinates(coordinatesDetails.getCoords()))
                .map(response -> response.cafes.resultsNear)
                .map(resultsNears -> {
                    for (int i = 0; i < resultsNears.size(); i++) {
                        List<StoreHours> temp = conveter(resultsNears.get(i).jsonObject);
                        addressMapper(resultsNears.get(i));
                        resultsNears.get(i).setStoreHoursList(temp);
                    }
                    return resultsNears;
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(resultsNears -> {
                    loadMarkersOnMap(resultsNears);
                    if (resultsNears.isEmpty() ||resultsNears==null){
                        mainViewInter.nothingFound();
                    } else{
                        listOfStores = resultsNears;
                        mainViewInter.listOfCafesFound(resultsNears);
                    }

                }, throwable -> Log.e("Error Rx", throwable.getMessage())));
        //check for no results
    }

    @Override
    public void markerClicked(String title) {
        for (int i = 0; i < listOfStores.size(); i++) {
            if (listOfStores.get(i).name.equals(title)) {
                mainViewInter.setRecyclePointer(i);
            }
        }
    }

    public Observable<CoordinatesDetails> getCoordinatesWithKeyword(String keyword) {
        return locatorService.getCoordinatesWithKeyword(keyword)
                .observeOn(AndroidSchedulers.mainThread())
                .doOnNext(coordinatesDetails -> {
                    LatLng location = new LatLng(coordinatesDetails.getCoords().latitude, coordinatesDetails.getCoords().longitude);
                    mainViewInter.sendKeywordLocation(location);
                    Log.e("Lat", coordinatesDetails.getCoords().latitude.toString());
                    Log.e("Lon", coordinatesDetails.getCoords().longitude.toString());
                })
                .subscribeOn(Schedulers.io());
    }

    private Observable<Response> searchByCoordinates(CoordinatesDetails.Coords coordinatesDetails) {
        return locatorService.SearchByCoordinates(true, coordinatesDetails.latitude, coordinatesDetails.longitude);
    }


    private void addressMapper(ResultsNear resultsNear) {
        if (resultsNear.address != null) {
            if (resultsNear.address.contains("<br />")) {
                temp = resultsNear.address.split("<br />");
            } else if (resultsNear.address.contains("<br>")) {
                temp = resultsNear.address.split("<br>");
            }
            resultsNear.setStreet_address(temp[0].replaceAll("\r\n", ""));
            String spaceRemoved = temp[1].replaceAll("\r\n", "");
            resultsNear.setCity_address(spaceRemoved);
        } else {
            resultsNear.setCity_address("Coming Soon");
            resultsNear.setStreet_address("Coming Soon");
        }
    }

    private void loadMarkersOnMap(List<ResultsNear> resultsNear) {
        for (int i = 0; i < resultsNear.size(); i++) {
            LatLng temp = new LatLng(resultsNear.get(i).latitude, resultsNear.get(i).longitude);
            mainViewInter.loadCafesPosition(temp, resultsNear.get(i).name);
        }
    }

    private List<StoreHours> conveter(JsonObject hours) {
        final JsonObject hoursjsonObject = gson.toJsonTree(hours).getAsJsonObject();
        List<StoreHours> storeHoursList = new ArrayList<>();
        for (Map.Entry<String, JsonElement> entry : hoursjsonObject.entrySet()) {
            StoreHours storeHours = new StoreHours();
            Log.e("Day", entry.getKey());
            storeHours.setDayOfWeek(convertToDayOfWeek(entry.getKey()));
            if (entry.getValue().toString().length() <= 10) {
                storeHours.setOpen(false);
            } else {
                storeHours.setOpen(true);
                mapStoreOperationHours(storeHours, entry.getValue());
            }
            storeHoursList.add(storeHours);
        }
        storeHoursList.add(0, storeHoursList.get(storeHoursList.size() - 1));
        storeHoursList.remove(storeHoursList.size() - 1);
        return storeHoursList;
    }

    private String convertToDayOfWeek(String key) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat parsFormat = new SimpleDateFormat("EEEE");
        Date date = null;
        try {
            date = dateFormat.parse(key);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        String convertedDay = parsFormat.format(date);
        return convertedDay;
    }

    private void mapStoreOperationHours(StoreHours storeHours, JsonElement string) {
        final JsonObject jsonObject2 = gson.toJsonTree(string).getAsJsonObject();
        for (Map.Entry<String, JsonElement> entry2 : jsonObject2.entrySet()) {
            switch (entry2.getKey()) {
                case "open":
                    storeHours.setOpenTime(convertTo12Hour(entry2.getValue().toString()));
                    break;
                case "close":
                    storeHours.setCloseTime(convertTo12Hour(entry2.getValue().toString()));
                    break;
            }
        }
    }

    private String convertTo12Hour(String hour) {
        String[] foo = hour.substring(hour.indexOf('T') + 1, hour.length()).split("-");
        String time = "";
        try {
            String _24HourTime = foo[0];
            SimpleDateFormat _24HourDF = new SimpleDateFormat("HH:mm");
            SimpleDateFormat _12HourDF = new SimpleDateFormat("hh:mm a");
            Date _24HourDt = _24HourDF.parse(_24HourTime);
            time = _12HourDF.format(_24HourDt);
            if (time.charAt(0) == '0') {
                return time.replaceFirst("0", "");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return time;
    }
}



