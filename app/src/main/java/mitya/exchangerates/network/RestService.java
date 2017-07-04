package mitya.exchangerates.network;

import mitya.exchangerates.domain.CurrencyRate;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Mitya on 04.07.2017.
 */

public class RestService {
    private RestService.FixerService fixerService;

    private static final String API_URL = "http://api.fixer.io/";

    public RestService() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
        fixerService = retrofit.create(FixerService.class);
    }

    public interface FixerService {
        @GET("latest?")
        Observable<CurrencyRate> getCurrencyRate(@Query("base") String base);
    }

    public RestService.FixerService getFixerService() {
        return fixerService;
    }

}
