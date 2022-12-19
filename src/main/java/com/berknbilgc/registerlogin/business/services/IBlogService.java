package com.berknbilgc.registerlogin.business.services;
import com.google.gson.JsonElement;

import java.util.List;

public interface IBlogService {
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


    //SAVE
    JsonElement blogSave(JsonElement jsonElement);

    //LIST
    List<JsonElement> blogList();

    //FIND
    JsonElement blogFind(Long id);

    //DELETE
    void blogDelete(Long id);

    //UPDATE
    JsonElement blogUpdate(Long id,JsonElement jsonElement);
}
