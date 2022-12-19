package com.berknbilgc.registerlogin.business.services;

import com.google.gson.JsonElement;

import java.util.List;

public interface IDailyService {
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
    JsonElement dailySave(JsonElement jsonElement);

    //LIST
    List<JsonElement> dailyList();

    //FIND
    JsonElement dailyFind(Long id);

    //DELETE
    void dailyDelete(Long id);

    //UPDATE
    JsonElement dailyUpdate(Long id,JsonElement jsonElement);
}
