package com.berknbilgc.registerlogin.retrofit;

import lombok.extern.log4j.Log4j2;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

@Log4j2
public class RetrofitCommonGenerics {

    public static <T> T retrofitGenerics(Call<T> request){
        try {
            //retrofit
            Response<T> response = request.execute();
            if (!response.isSuccessful()) {
                log.error("Retrofit Product Failed statusCode:{} and reason{}", response.code(), response.errorBody().string());
            }
            return response.body();
        } catch (IOException ioException) {
            ioException.printStackTrace();
            throw  new RuntimeException(ioException.getCause());
        }
    }
}
