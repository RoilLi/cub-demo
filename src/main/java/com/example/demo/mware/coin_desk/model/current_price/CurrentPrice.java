package com.example.demo.mware.coin_desk.model.current_price;

import java.util.Map;

public class CurrentPrice {

    private Time time;
    private String disclaimer;
    private String chartName;
    private Map<String, RateInfo> bpi;

    public Time getTime() {
        return time;
    }

    public void setTime(Time time) {
        this.time = time;
    }

    public String getDisclaimer() {
        return disclaimer;
    }

    public void setDisclaimer(String disclaimer) {
        this.disclaimer = disclaimer;
    }

    public String getChartName() {
        return chartName;
    }

    public void setChartName(String chartName) {
        this.chartName = chartName;
    }

    public Map<String, RateInfo> getBpi() {
        return bpi;
    }

    public void setBpi(Map<String, RateInfo> bpi) {
        this.bpi = bpi;
    }
}
