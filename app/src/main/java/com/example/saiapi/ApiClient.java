package com.example.saiapi;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {

    private static Retrofit retrofit=null;
    static Retrofit getClient(){
         retrofit=new Retrofit.Builder()
                 .baseUrl(Api.BASE_URL)
                 .addConverterFactory(GsonConverterFactory.create())
                 .build();
         return retrofit;
    }
}
