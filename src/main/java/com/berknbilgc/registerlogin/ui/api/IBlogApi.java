package com.berknbilgc.registerlogin.ui.api;
import com.berknbilgc.registerlogin.error.ApiResult;
import com.google.gson.JsonElement;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IBlogApi {
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
    ApiResult saveBlog(JsonElement jsonElement);

    //LIST
    ResponseEntity<List<?>> listBlog();

    //FIND
    ResponseEntity<?> findBlog(Long id);


    //DELETE
    ApiResult deleteBlog(Long id);

    //UPDATE
    ApiResult updateBlog(Long id,JsonElement jsonElement);
}
