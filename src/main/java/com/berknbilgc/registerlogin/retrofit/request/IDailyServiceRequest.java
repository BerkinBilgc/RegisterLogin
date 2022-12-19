package com.berknbilgc.registerlogin.retrofit.request;

import com.google.gson.JsonElement;
import org.springframework.web.bind.annotation.RequestBody;
import retrofit2.Call;
import retrofit2.http.*;
import java.util.List;

public interface IDailyServiceRequest {
    // REST             REQUEST
    // ----------       -------
    // @GetMapping      @GET
    // @PostMapping     @POST
    // @DeleteMapping   @DELETE
    // @PutMapping      @PUT

    // @PathVariable    @Path
    // @RequestBody     @Body

    // ResponseEntity   Call
    // DailyDto         JsonElement

    //CREATE
    //daily project api bilgilerini yazıyoruz
    @POST("/api/v1/daily/create")
    Call<JsonElement> dailySave( @Body JsonElement jsonElement);

    //LIST
    //daily project api bilgilerini yazıyoruz
    @GET("/api/v1/daily/list")
    Call<List<JsonElement>> dailyList();

    //FIND
    @GET("/api/v1/daily/find/{id}")
    Call<JsonElement> dailyFind(@Path("id") Long id);


    //DELETE
    @DELETE("/api/v1/daily/delete/{id}")
    Call<JsonElement> dailyDelete(@Path("id") Long id);


    //UPDATE
    @PUT("/api/v1/daily/update/{id}")
    Call<JsonElement> dailyUpdate(@Path("id") Long id,@Body JsonElement jsonElement);

}
