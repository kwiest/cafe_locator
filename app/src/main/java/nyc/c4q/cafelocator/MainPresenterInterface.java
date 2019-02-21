package nyc.c4q.cafelocator;

/**
 * Created by jervon.arnoldd on 2/13/19.
 */

public interface MainPresenterInterface {
    void getStoreList(String keyword);

    void markerClicked(String title);

    void setmainViewInter(MainViewInterface mainViewInter);

    void onDestroy();

    void findStoreByName(int name);
}
