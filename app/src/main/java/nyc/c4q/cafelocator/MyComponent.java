package nyc.c4q.cafelocator;

import javax.inject.Singleton;

import dagger.Component;
import nyc.c4q.cafelocator.api.ApiModule;
import nyc.c4q.cafelocator.ui.ListFragment;

/**
 * Created by jervon.arnoldd on 2/13/19.
 */
@Singleton
@Component(modules = ApiModule.class)
public interface MyComponent {

    void inject(MapsActivity mapsActivity);
    void inject(ListFragment listFragment);
}
