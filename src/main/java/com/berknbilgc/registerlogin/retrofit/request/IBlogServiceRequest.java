package com.berknbilgc.registerlogin.retrofit.request;

import com.google.gson.JsonElement;
import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface IBlogServiceRequest {
    // REST             REQUEST
    // ----------       -------
    // @GetMapping      @GET
    // @PostMapping     @POST
    // @DeleteMapping   @DELETE
    // @PutMapping      @PUT

    // @PathVariable    @Path
    // @RequestBody     @Body

    // ResponseEntity   Call
    // BlogDto         JsonElement

    //CREATE
    //blog project api bilgilerini yazıyoruz
    @POST("/api/v3/blog/create")
    Call<JsonElement> blogSave(@Body JsonElement jsonElement);

    //LIST
    //blog project api bilgilerini yazıyoruz
    @GET("/api/v3/blog/list")
    Call<List<JsonElement>> blogList();

    //FIND
    @GET("/api/v3/blog/find/{id}")
    Call<JsonElement> blogFind(@Path("id") Long id);


    //DELETE
    @DELETE("/api/v3/blog/delete/{id}")
    Call<JsonElement> blogDelete(@Path("id") Long id);


    //UPDATE
    @PUT("/api/v3/blog/update/{id}")
    Call<JsonElement> blogUpdate(@Path("id") Long id,@Body JsonElement jsonElement);


}
