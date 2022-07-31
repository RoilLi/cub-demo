package com.example.demo;

import com.example.demo.entity.CurCurrencyNameSetting;
import com.example.demo.enums.ApiStatusEnum;
import com.example.demo.mware.coin_desk.client.CoinDeskClient;
import com.example.demo.mware.coin_desk.model.current_price.CurrentPrice;
import com.google.gson.Gson;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.nio.charset.StandardCharsets;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class CurrencyControllerTest {

    @Autowired
    private WebApplicationContext context;
    @Autowired
    private CoinDeskClient coinDeskClient;
    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    @Order(3)
    public void testGetCurrencySetting() throws Exception {
        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.get("/api/currency/setting"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("returnCode").value("00"))
                .andReturn().getResponse();
        System.out.println(response.getContentAsString(StandardCharsets.UTF_8));
    }

    @Test
    @Order(1)
    public void testInsertCurrencySettingSuccess() throws Exception {
        CurCurrencyNameSetting curCurrencyNameSetting = new CurCurrencyNameSetting();
        curCurrencyNameSetting.setCurrencyCode("NTD");
        curCurrencyNameSetting.setName("新台幣");
        curCurrencyNameSetting.setLanguage("TW");
        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.post("/api/currency/setting")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .characterEncoding(StandardCharsets.UTF_8)
                        .content(new Gson().toJson(curCurrencyNameSetting)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("returnCode").value(ApiStatusEnum.SUCCESS.getCode()))
                .andReturn().getResponse();
        System.out.println(response.getContentAsString(StandardCharsets.UTF_8));
    }
    @Test
    @Order(2)
    public void testInsertCurrencySettingFail() throws Exception {
        CurCurrencyNameSetting curCurrencyNameSetting = new CurCurrencyNameSetting();
        curCurrencyNameSetting.setCurrencyCode("NTD");
        curCurrencyNameSetting.setName("新台幣");
        curCurrencyNameSetting.setLanguage("TW");
        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.post("/api/currency/setting")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .characterEncoding(StandardCharsets.UTF_8)
                        .content(new Gson().toJson(curCurrencyNameSetting)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("returnCode").value(ApiStatusEnum.FAIL.getCode()))
                .andReturn().getResponse();
        System.out.println(response.getContentAsString(StandardCharsets.UTF_8));
    }

    @Test
    @Order(4)
    public void testUpdateCurrencySettingSuccess() throws Exception {
        CurCurrencyNameSetting curCurrencyNameSetting = new CurCurrencyNameSetting();
        curCurrencyNameSetting.setName("新台幣更新");
        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.patch("/api/currency/setting/4")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .characterEncoding(StandardCharsets.UTF_8)
                        .content(new Gson().toJson(curCurrencyNameSetting)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("returnCode").value(ApiStatusEnum.SUCCESS.getCode()))
                .andReturn().getResponse();
        System.out.println(response.getContentAsString(StandardCharsets.UTF_8));
    }

    @Test
    @Order(5)
    public void testUpdateCurrencySettingFail() throws Exception {
        CurCurrencyNameSetting curCurrencyNameSetting = new CurCurrencyNameSetting();
        curCurrencyNameSetting.setName("新台幣更新");
        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.patch("/api/currency/setting/99")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .characterEncoding(StandardCharsets.UTF_8)
                        .content(new Gson().toJson(curCurrencyNameSetting)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("returnCode").value(ApiStatusEnum.FAIL.getCode()))
                .andReturn().getResponse();
        System.out.println(response.getContentAsString(StandardCharsets.UTF_8));
    }

    @Test
    @Order(6)
    public void testDeleteCurrencySettingSuccess() throws Exception {
        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.delete("/api/currency/setting/4"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("returnCode").value(ApiStatusEnum.SUCCESS.getCode()))
                .andReturn().getResponse();
        System.out.println(response.getContentAsString(StandardCharsets.UTF_8));
    }

    @Test
    @Order(7)
    public void testDeleteCurrencySettingFail() throws Exception {
        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.delete("/api/currency/setting/99"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("returnCode").value(ApiStatusEnum.FAIL.getCode()))
                .andReturn().getResponse();
        System.out.println(response.getContentAsString(StandardCharsets.UTF_8));
    }

    @Test
    @Order(8)
    public void testCoinDeskApi() throws Exception {
        CurrentPrice currentPrice = coinDeskClient.getCurrentPrice();
        System.out.println(new Gson().toJson(currentPrice));
    }

    @Test
    @Order(9)
    public void testGetCurrencyInfo() throws Exception {
        MockHttpServletResponse response = mockMvc.perform(MockMvcRequestBuilders.get("/api/currency/info"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("returnCode").value(ApiStatusEnum.SUCCESS.getCode()))
                .andReturn().getResponse();
        System.out.println(response.getContentAsString(StandardCharsets.UTF_8));
    }
}
