package com.example.demo.service.impl;

import com.example.demo.dto.CurrencyInfo;
import com.example.demo.dto.ExchangeRate;
import com.example.demo.entity.CurCurrencyNameSetting;
import com.example.demo.exception.DemoException;
import com.example.demo.mware.coin_desk.client.CoinDeskClient;
import com.example.demo.mware.coin_desk.model.CurrentPrice.CurrentPrice;
import com.example.demo.mware.coin_desk.model.CurrentPrice.RateInfo;
import com.example.demo.repository.CurCurrencyNameSettingRepo;
import com.example.demo.service.CurrencyService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.beans.PropertyDescriptor;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CurrencyServiceImpl implements CurrencyService {

    @Autowired
    CurCurrencyNameSettingRepo curCurrencyNameSettingRepo;
    @Autowired
    CoinDeskClient coinDeskClient;

    @Override
    public CurrencyInfo getCurrencyInfo(String language) throws ParseException {
        CurrentPrice apiResult = coinDeskClient.getCurrentPrice();

        CurrencyInfo currencyInfo = new CurrencyInfo();
        String updateDate = apiResult.getTime().getUpdated();
        if(StringUtils.isNotBlank(updateDate)) {
            LocalDateTime localDateTime = LocalDateTime.parse(updateDate, DateTimeFormatter.ofPattern("MMM dd, yyyy HH:mm:ss 'UTC'"));
            // utc+0 to utc+8
            localDateTime = localDateTime.atZone(ZoneId.from(ZoneOffset.UTC)).withZoneSameInstant(ZoneOffset.ofHours(8)).toLocalDateTime();
            currencyInfo.setUpdateDate(localDateTime.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")));
        }

        Map<String, RateInfo> rateInfoMap = apiResult.getBpi();
        if(!rateInfoMap.isEmpty()) {
            List<CurCurrencyNameSetting> currencyNameSettings = curCurrencyNameSettingRepo.findByLanguageAndCurrencyCodeIn(language, rateInfoMap.keySet());
            List<ExchangeRate> exchangeRates = rateInfoMap.entrySet().stream().map(entry -> {
                String currencyCode = entry.getKey();
                RateInfo rateInfo = entry.getValue();
                ExchangeRate exchangeRate = new ExchangeRate();
                exchangeRate.setCurrencyCode(currencyCode);
                String currencyName = currencyNameSettings.stream()
                        .filter(curCurrencyNameSetting -> StringUtils.equals(currencyCode, curCurrencyNameSetting.getCurrencyCode()))
                        .findFirst().map(CurCurrencyNameSetting::getName).orElse("");
                exchangeRate.setCurrencyName(currencyName);
                exchangeRate.setRate(rateInfo.getRate());
                return exchangeRate;
            }).collect(Collectors.toList());
            currencyInfo.setRates(exchangeRates);
        }

        return currencyInfo;
    }

    @Override
    public List<CurCurrencyNameSetting> getCurrencySetting(CurCurrencyNameSetting curCurrencyNameSetting) {
        return curCurrencyNameSettingRepo.findAll(Example.of(curCurrencyNameSetting));
    }

    @Override
    public void insertCurrencySetting(CurCurrencyNameSetting curCurrencyNameSetting) {
        List<CurCurrencyNameSetting> currentSettings = curCurrencyNameSettingRepo.findByCurrencyCodeAndLanguage(curCurrencyNameSetting.getCurrencyCode(), curCurrencyNameSetting.getLanguage());
        if(!currentSettings.isEmpty()) {
            throw new DemoException("設定已存在");
        }
        curCurrencyNameSetting.setCreateDate(Calendar.getInstance().getTime());
        curCurrencyNameSettingRepo.save(curCurrencyNameSetting);
    }

    @Override
    public CurCurrencyNameSetting updateCurrencySetting(CurCurrencyNameSetting curCurrencyNameSetting) {
        Optional<CurCurrencyNameSetting> queryResult = curCurrencyNameSettingRepo.findById(curCurrencyNameSetting.getId());
        if(queryResult.isPresent()) {
            CurCurrencyNameSetting currencyNameSetting = queryResult.get();
            BeanUtils.copyProperties(curCurrencyNameSetting, currencyNameSetting, getNullPropertyNames(curCurrencyNameSetting));
            currencyNameSetting.setUpdateDate(Calendar.getInstance().getTime());
            return curCurrencyNameSettingRepo.save(currencyNameSetting);
        } else {
            throw new DemoException("設定不存在");
        }
    }

    @Override
    public void deleteCurrencySetting(Long id) {
        Optional<CurCurrencyNameSetting> queryResult = curCurrencyNameSettingRepo.findById(id);
        if(queryResult.isPresent()) {
            curCurrencyNameSettingRepo.deleteById(id);
        } else {
            throw new DemoException("設定不存在");
        }
    }

    private String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        PropertyDescriptor[] pds = src.getPropertyDescriptors();
        Set<String> emptyNames = new HashSet<>();
        for(PropertyDescriptor pd: pds) {
            Object srvValue = src.getPropertyValue(pd.getName());
            if(srvValue == null) {
                emptyNames.add(pd.getName());
            }
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }
}
