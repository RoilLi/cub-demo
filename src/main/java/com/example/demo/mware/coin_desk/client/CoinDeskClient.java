package com.example.demo.mware.coin_desk.client;

import com.example.demo.mware.coin_desk.model.CurrentPrice.CurrentPrice;
import com.fasterxml.jackson.databind.ser.std.DateSerializer;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.text.DateFormat;

@Component
public class CoinDeskClient {

    public CurrentPrice getCurrentPrice() {
        ResponseEntity<String> responseEntity = new RestTemplate().getForEntity("https://api.coindesk.com/v1/bpi/currentprice.json", String.class);
        if( responseEntity.getStatusCode() == HttpStatus.OK) {
            Gson gson = new GsonBuilder().create();
            return gson.fromJson(responseEntity.getBody(), CurrentPrice.class);
        }
        return null; // TODO 錯誤處理待實作
    }
}
