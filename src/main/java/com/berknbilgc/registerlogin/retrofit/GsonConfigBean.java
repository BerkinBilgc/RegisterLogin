package com.berknbilgc.registerlogin.retrofit;

import com.google.gson.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.GsonHttpMessageConverter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Configuration
public class GsonConfigBean {

    //Date aytıştırmak
    //gsonBuilder.create
    @Bean
    public Gson gson(GsonBuilder gsonBuilder){
        return gsonBuilder.create();
    }

    //GsonHttpMessageConverter
    @Bean
    public GsonHttpMessageConverter gsonHttpMessageConverter(Gson gson){
        GsonHttpMessageConverter httpMessageConverter=new GsonHttpMessageConverter();
        httpMessageConverter.setGson(gson);
        return  httpMessageConverter;
    }

    //GsonBuilder
    @Bean
    public GsonBuilder gsonBuilder(){
        return new GsonBuilder().registerTypeAdapter(LocalDateTime.class,
                        (JsonSerializer<LocalDateTime>)(date, type, context)->
                                date!=null ? new JsonPrimitive(String.valueOf(DateTimeFormatter.ISO_LOCAL_DATE_TIME)):null)
                .registerTypeAdapter(LocalDateTime.class,
                        (JsonDeserializer<LocalDateTime>)(json, type, context)->
                                LocalDateTime.parse(json.getAsString(),DateTimeFormatter.ISO_LOCAL_DATE_TIME));
    }
}
