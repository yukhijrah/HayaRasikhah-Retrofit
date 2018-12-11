package app.hayarasikhah.portalti2016.Network;

import java.net.HttpRetryException;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.HTTP;

/**
 * Created by Haya Rasikhah on 12/9/2018.
 */

public class Network {
    public static Retrofit request(){

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();

        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .build();




        return new Retrofit.Builder()
                .baseUrl("http://35.186.145.167:1337/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())

                .build();


    }
}
