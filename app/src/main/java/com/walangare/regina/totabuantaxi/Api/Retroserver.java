package com.walangare.regina.totabuantaxi.Api;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by REGINA on 3/4/2018.
 */

public class Retroserver {
    private static final String base_url = "http://192.168.43.127/REST_TotabuanTaxi/";

//    private  static Retrofit retrofit;
//
//    public  static  Retrofit getClient()
//    {
//        if (retrofit == null){
//            retrofit = new Retrofit.Builder()
//                    .baseUrl(base_url)
//                    .addConverterFactory(GsonConverterFactory.create())
//                    .build();
//        }
//        return retrofit;
//    }
//    public  static ApiRequestKM getApiService()
//    {
//        return getClient().create(ApiRequestKM.class);
//    }

    private OkHttpClient provideOkhttpClient(){
        return new OkHttpClient.Builder()
                .addInterceptor(new HttpLoggingInterceptor()
                .setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();
    }
    private Retrofit provideRetrofit() {

        return new Retrofit.Builder()
                .baseUrl(base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .client(provideOkhttpClient())
                .build();
    }
    public ApiRequestKM ApiService() {
        return provideRetrofit()
                .create(ApiRequestKM.class);
    }



}
