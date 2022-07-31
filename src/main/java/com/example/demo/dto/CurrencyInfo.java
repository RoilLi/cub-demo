package com.example.demo.dto;

import java.util.Date;
import java.util.List;

public class CurrencyInfo {

    private String updateDate;
    private List<ExchangeRate> rates;

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public List<ExchangeRate> getRates() {
        return rates;
    }

    public void setRates(List<ExchangeRate> rates) {
        this.rates = rates;
    }
}
