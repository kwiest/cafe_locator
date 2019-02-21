package nyc.c4q.cafelocator;

import android.app.Application;


/**
 * Created by jervon.arnoldd on 2/13/19.
 */

public class CafeLocatorApp extends Application {
    public MyComponent myComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        myComponent = DaggerMyComponent.create();
    }

    public MyComponent  getMyComponent() {
        return myComponent;
    }
}
