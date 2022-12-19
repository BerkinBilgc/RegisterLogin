package com.berknbilgc.registerlogin.ui.api;

import com.berknbilgc.registerlogin.error.ApiResult;
import com.google.gson.JsonElement;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

public interface IDailyApi {
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


    //SAVE
    ApiResult saveDaily(JsonElement jsonElement);

    //LIST
    ResponseEntity<List<?>> listDaily();

    //FIND
    ResponseEntity<?> findDaily(Long id);


    //DELETE
    ApiResult deleteDaily(Long id);

    //UPDATE
    ApiResult updateDaily(Long id,JsonElement jsonElement);
}