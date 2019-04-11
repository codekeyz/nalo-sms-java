package com.nalo.nalosms;

import retrofit2.Call;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

interface NaloSMSService {

    @Headers("Content-Type: text/html")
    @POST("bulksms")
    Call<String> sendSMS(
            @Query("username") String username,
            @Query("password") String password,
            @Query("source") String source,
            @Query("message") String message,
            @Query("destination") String destination,
            @Query("type") int type,
            @Query("dlr") String dlr);

}
