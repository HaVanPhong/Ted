package com.example.tedimagepicker;

import android.graphics.drawable.Drawable;

import com.google.gson.GsonBuilder;
import com.google.gson.internal.GsonBuildConfig;

import org.w3c.dom.Text;

import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Multipart;
import retrofit2.http.PATCH;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;

public interface NetWorkClient {

    //192.168.56.1:8080/api/accounts
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("https://accountservermanagement.herokuapp.com/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    NetWorkClient net= retrofit.create(NetWorkClient.class);



    @Multipart
    @POST("accounts")
    Call<String> createAccount(@Part String username, @Part String password ,
                               @Part MultipartBody.Part photo);

//    @POST("accounts")
//    Call<AccountBody> creatAccoutbyBody(@Body AccountBody accountBody);

//    @Multipart
//    @POST("/products")
//    Call<ResponseBody> upload(
//            @Part("description") RequestBody description,
//            @Part MultipartBody.Part file
//
}