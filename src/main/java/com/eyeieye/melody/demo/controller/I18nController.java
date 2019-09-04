package com.eyeieye.melody.demo.controller;

import com.eyeieye.melody.demo.domain.DemoMoney;
import com.eyeieye.melody.demo.locale.LangRepertory;
import com.eyeieye.melody.demo.service.I18nService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

import java.math.BigDecimal;
import java.util.Locale;

@Controller
@RequestMapping("i18n")
public class I18nController {

    Logger logger = LoggerFactory.getLogger(I18nController.class);

    @Autowired
    I18nService i18nService;
    @Value("${melody.web.locale.cookieName:_l_}")
    String localeCookieName;

    // 国际化货币显示的初始化金额，种类为CNY
    private BigDecimal amount1 = new BigDecimal(100);
    private BigDecimal amount2 = new BigDecimal(17.92);
    private BigDecimal amount3 = new BigDecimal(254775000.66);

    @RequestMapping("hasen.htm")
    private void hasen(ModelMap modelMap){
        modelMap.put("localtest", LangRepertory.lang_test.getMessage());
    }
    @RequestMapping("noen.htm")
    private void noen(){

    }
    @RequestMapping("tc.htm")
    private void tc(){

    }
    @GetMapping("currency.htm")
    private String currency(ModelMap modelMap, HttpServletRequest request){
        Locale locale = request.getLocale();

        DemoMoney amount1;
        DemoMoney amount2;
        DemoMoney amount3;

        try {
            amount1 = i18nService.getAmountByLocal(locale,this.amount1);
            amount2 = i18nService.getAmountByLocal(locale,this.amount2);
            amount3 = i18nService.getAmountByLocal(locale,this.amount3);
        } catch (Exception e) {
            logger.error("获取国际化金额错误",e);
            return "error";
        }
        modelMap.put("amount1",amount1);
        modelMap.put("amount2",amount2);
        modelMap.put("amount3",amount3);

        return "i18n/currency";


    }


}
