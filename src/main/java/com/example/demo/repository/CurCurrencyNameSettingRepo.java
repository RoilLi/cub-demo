package com.example.demo.repository;

import com.example.demo.entity.CurCurrencyNameSetting;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;
import java.util.List;

public interface CurCurrencyNameSettingRepo extends JpaRepository<CurCurrencyNameSetting, Long> {

    List<CurCurrencyNameSetting> findByCurrencyCode(String currencyCode);

    List<CurCurrencyNameSetting> findByLanguageAndCurrencyCodeIn(String language, Collection<String> currencyCodes);
}
