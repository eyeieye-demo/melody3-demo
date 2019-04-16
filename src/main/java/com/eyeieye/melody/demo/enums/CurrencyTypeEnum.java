package com.eyeieye.melody.demo.enums;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import com.eyeieye.melos.util.StringUtil;

public enum CurrencyTypeEnum {

    CNY("人民币",new BigDecimal(1),"￥",Locale.CHINA,Locale.CHINESE,Locale.SIMPLIFIED_CHINESE),
    USD("US Dollar",new BigDecimal(0.1439),"$",Locale.US,Locale.ENGLISH);

    String type; //货币类型名
    BigDecimal exchangeRate; //较默认货币的汇率
    String symbol; //货币符号
    List<Locale> locales; // 对应地域


    CurrencyTypeEnum(String type, BigDecimal exchangeRate, String symbol, Locale...locales){
        this.type = type;
        this.exchangeRate = exchangeRate;
        this.symbol = symbol;
        this.locales = new ArrayList();
        if(locales != null){
            for(Locale locale : locales){
                this.locales.add(locale);
            }
        }
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getExchangeRate() {
        return exchangeRate;
    }

    public void setExchangeRate(BigDecimal exchangeRate) {
        this.exchangeRate = exchangeRate;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        symbol = symbol;
    }

    public List<Locale> getLocales() {
        return locales;
    }

    public void setLocales(List<Locale> locales) {
        this.locales = locales;
    }

    public static boolean hasType(String typeName){
        for(CurrencyTypeEnum type : CurrencyTypeEnum.values()){
            if(StringUtil.equals(typeName,type.name())==true){
                return true;
            }
        }
        return false;
    }

    public static CurrencyTypeEnum getType(String typeName){
        for(CurrencyTypeEnum type : CurrencyTypeEnum.values()){
            if(StringUtil.equals(typeName,type.name())==true){
                return type;
            }
        }
        return null;
    }

    public static CurrencyTypeEnum getByLocale(Locale locale){
        for(CurrencyTypeEnum type : CurrencyTypeEnum.values()){
            for(Locale thatLocale : type.getLocales()){
                if(thatLocale.equals(locale)){
                    return type;
                }
            }
        }
        return  null;
    }

    public static CurrencyTypeEnum getByLocale(String language){
        Locale locale = Locale.forLanguageTag(language);
        return getByLocale(locale);
    }
}
