package com.example.demo.service;

import com.example.demo.dto.CurrencyInfo;
import com.example.demo.entity.CurCurrencyNameSetting;

import java.text.ParseException;
import java.util.List;

public interface CurrencyService {

    /**
     * 取得幣別相關資訊
     * @param language 語言
     * @return 幣別相關資訊
     */
    CurrencyInfo getCurrencyInfo(String language) throws ParseException;

    /**
     * 查詢幣別名稱設定
     * @param curCurrencyNameSetting condition
     * @return 設定清單
     */
    List<CurCurrencyNameSetting> getCurrencySetting(CurCurrencyNameSetting curCurrencyNameSetting);

    /**
     * 新增幣別名稱設定
     * @param curCurrencyNameSetting data
     */
    void insertCurrencySetting(CurCurrencyNameSetting curCurrencyNameSetting);

    /**
     * 更新幣別名稱設定
     * @param curCurrencyNameSetting data
     * @return 更新後設定
     */
    CurCurrencyNameSetting updateCurrencySetting(CurCurrencyNameSetting curCurrencyNameSetting);

    /**
     * 刪除幣別名稱設定
     * @param id id
     */
    void deleteCurrencySetting(Long id);
}
