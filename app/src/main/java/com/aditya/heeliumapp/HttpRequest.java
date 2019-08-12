package com.aditya.heeliumapp;


import com.aditya.heeliumapp.Pojos.DocumentUploadRequest;
import com.aditya.heeliumapp.Pojos.DocumentUploadResponse;
import com.aditya.heeliumapp.Pojos.ImageUploadResponse;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;

/**
 * Created by aditya on 12/29/17.
 */

public class HttpRequest {
    public static final String API_URL = "http://heelium.in/";
    public static Retrofit retrofit= new Retrofit.Builder()
            .baseUrl(API_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    interface RetrofitInterface {
        @Multipart
        @POST("/mobile/upload")
        Call<ImageUploadResponse> uploadImage(@Part MultipartBody.Part image);

        @POST("/mobile/document")
        Call<DocumentUploadResponse> uploadDocument(
                @Body DocumentUploadRequest request);
    }
}
