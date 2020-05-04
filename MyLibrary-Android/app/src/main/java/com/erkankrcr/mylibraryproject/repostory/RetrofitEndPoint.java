package com.erkankrcr.mylibraryproject.repostory;

import com.erkankrcr.mylibraryproject.repostory.model.Author;
import com.erkankrcr.mylibraryproject.repostory.model.Library;
import com.erkankrcr.mylibraryproject.repostory.model.LibraryRequest;
import com.erkankrcr.mylibraryproject.repostory.model.Message;
import com.erkankrcr.mylibraryproject.repostory.model.SignUser;
import com.erkankrcr.mylibraryproject.repostory.model.User;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Query;

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
public interface RetrofitEndPoint {
    @GET("user/login")
    Call<User> login(@Query("username") String username , @Query("password") String password);

    @POST("user/sign")
    Call<User> sign(@Body SignUser user);

    @GET("library/search")
    Call<List<Library>> getSearchLibrary(@Query("query") String query , @Header("x-user-key") String apiKey);

    @GET("library/book")
    Call<Message> setChangeLibrary(@Query("libraryId")String libraryId , @Header("x-user-key") String apiKey);

    @GET("library/author")
    Call<List<Author>> getAuthors(@Header("x-user-key")String apiKey);

    @POST("library/book")
    Call<Message> setBook(@Body LibraryRequest request, @Header("x-user-key")String apiKey);
}
