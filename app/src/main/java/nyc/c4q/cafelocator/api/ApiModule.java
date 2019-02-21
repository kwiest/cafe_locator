package nyc.c4q.cafelocator.api;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.schedulers.Schedulers;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by jervon.arnoldd on 2/13/19.
 */
@Module
public class ApiModule {
    private static final String COORDINATES_WITH_KEYWORD_BASE_URL="https://bluebottlecoffee.com/";

    @Provides
    @Singleton
    OkHttpClient provideHttpClient() {
        return new OkHttpClient();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofit(OkHttpClient client) {
        return new Retrofit.Builder()
                .baseUrl(COORDINATES_WITH_KEYWORD_BASE_URL)
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .build();
    }
    @Provides
    @Singleton
    LocatorService provideMovieService(Retrofit retrofit) {
        return retrofit.create(LocatorService.class);
    }
}
