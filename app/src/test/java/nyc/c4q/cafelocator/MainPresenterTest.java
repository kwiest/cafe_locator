package nyc.c4q.cafelocator;

import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Scheduler;
import io.reactivex.android.plugins.RxAndroidPlugins;
import io.reactivex.disposables.Disposable;
import io.reactivex.internal.schedulers.ExecutorScheduler;
import io.reactivex.plugins.RxJavaPlugins;
import nyc.c4q.cafelocator.api.LocatorService;
import nyc.c4q.cafelocator.pojo.ResultsNear;
import nyc.c4q.cafelocator.presenter.MainPresenter;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;

/**
 * Created by jervon.arnoldd on 2/20/19.
 */
@RunWith(JUnit4.class)
public class MainPresenterTest {
    @Mock
    private MainViewInterface mainViewInter;


    @BeforeClass
    public static void setupRxSchedulers() {
        Scheduler immediate = new Scheduler() {

            @Override
            public Disposable scheduleDirect(Runnable run, long delay, TimeUnit unit) {
                return super.scheduleDirect(run, 0, unit);
            }

            @Override
            public Worker createWorker() {
                return new ExecutorScheduler.ExecutorWorker(Runnable::run);
            }
        };

        RxJavaPlugins.setInitIoSchedulerHandler(scheduler -> immediate);
        RxJavaPlugins.setInitComputationSchedulerHandler(scheduler -> immediate);
        RxJavaPlugins.setInitNewThreadSchedulerHandler(scheduler -> immediate);
        RxJavaPlugins.setInitSingleSchedulerHandler(scheduler -> immediate);
        RxAndroidPlugins.setInitMainThreadSchedulerHandler(scheduler -> immediate);
    }

    @Before
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @After
    public void validate() {
        Mockito.validateMockitoUsage();
    }


    @Test
    public void validDataReturned() {

        LocatorService apiService = new MockApiService();
        MainPresenter presenter  = new MainPresenter(apiService,mainViewInter);

        presenter.getCoordinatesWithKeyword("Brooklyn");

    }
}
