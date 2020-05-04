package com.erkankrcr.mylibraryproject.repostory;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import okhttp3.OkHttpClient;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * For The Glory Of Machine
 * ╔════════════════════════════╗
 * ║  Created by Erkan Karacar  ║
 * ╠════════════════════════════╣
 * ║ erkankrcr@outlook.com.tr   ║
 * ╠════════════════════════════╣
 * ║     30/04/2020 - 23:57     ║
 * ╚════════════════════════════╝
 */
public class Retrofit {
    private final String base_url = "http://192.168.1.73:7006/";

    public retrofit2.Retrofit getRetrofit(){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();
        return new retrofit2
                .Retrofit.Builder()
                .client(new OkHttpClient())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(base_url)
                .build();
    }
}
