package com.example.demo.controller;

import com.example.demo.dto.CurrencyInfo;
import com.example.demo.dto.RestResult;
import com.example.demo.entity.CurCurrencyNameSetting;
import com.example.demo.service.CurrencyService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/api/currency")
public class CurrencyController extends BaseController {

    @Autowired
    CurrencyService currencyService;

    @GetMapping("/info")
    public RestResult getCurrencyInfo(@RequestParam(required = false, defaultValue = "TW") String lang) throws ParseException {
        CurrencyInfo result = currencyService.getCurrencyInfo(lang);
        return buildSuccessResult(result);
    }

    @GetMapping("/setting")
    public RestResult getCurrencySetting(CurCurrencyNameSetting curCurrencyNameSetting) {
        List<CurCurrencyNameSetting> result = currencyService.getCurrencySetting(curCurrencyNameSetting);
        return buildSuccessResult(result);
    }

    @PostMapping("/setting")
    public RestResult insertCurrencySetting(@RequestBody CurCurrencyNameSetting curCurrencyNameSetting) {
        if(StringUtils.isBlank(curCurrencyNameSetting.getCurrencyCode())
            || StringUtils.isBlank(curCurrencyNameSetting.getLanguage())
            || StringUtils.isBlank(curCurrencyNameSetting.getName())) {
            return buildErrorResult("缺少必填欄位", null);
        }
        currencyService.insertCurrencySetting(curCurrencyNameSetting);
        return buildSuccessResult();
    }

    @PatchMapping("/setting/{id}")
    public RestResult updateCurrencySetting(@PathVariable("id") Long id, @RequestBody CurCurrencyNameSetting curCurrencyNameSetting) {
        if(id == null) {
            return buildErrorResult("缺少必填欄位", null);
        }
        curCurrencyNameSetting.setId(id);
        CurCurrencyNameSetting currentSetting = currencyService.updateCurrencySetting(curCurrencyNameSetting);
        return buildSuccessResult(currentSetting);
    }

    @DeleteMapping("/setting/{id}")
    public RestResult deleteCurrencySetting(@PathVariable("id") Long id) {
        if(id == null) {
            return buildErrorResult("缺少必填欄位", null);
        }
        currencyService.deleteCurrencySetting(id);
        return buildSuccessResult();
    }
}
