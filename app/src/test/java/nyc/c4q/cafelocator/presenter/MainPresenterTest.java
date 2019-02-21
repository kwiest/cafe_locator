package nyc.c4q.cafelocator.presenter;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import nyc.c4q.cafelocator.MainViewInterface;
import nyc.c4q.cafelocator.MockApiService;
import nyc.c4q.cafelocator.api.LocatorService;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 * Created by jervon.arnoldd on 2/20/19.
 */
@RunWith(MockitoJUnitRunner.class)
public class MainPresenterTest {

    @Mock
    private MainViewInterface mainViewInter;
    LocatorService apiService;
    MainPresenter presenter;




    @Before
    public void setuo() {
        apiService = new MockApiService();
        presenter = new MainPresenter(apiService, mainViewInter);
    }


    @Test
    public void noStoreswereFoundTest() throws Exception {
    }

    @Test
    public void setmainViewInter() throws Exception {
    }

    @Test
    public void onDestroy() throws Exception {
    }

    @Test
    public void findStoreByName() throws Exception {
    }

    @Test
    public void getStoreList() throws Exception {
    }

    @Test
    public void markerClicked() throws Exception {
    }

    @Test
    public void getCoordinatesWithKeyword() throws Exception {
    }

}