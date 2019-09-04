package com.eyeieye.melody.demo.locale;

import java.util.Locale;

/**
 * 语言项
 * Created by bbt on 2019/07/19.
 */
public class LangItem {

    /**
     * 语言环境
     */
    private Locale locale;
    /**
     * 信息
     */
    private String message;

    public LangItem(Locale locale, String message) {
        this.locale = locale;
        this.message = message;
    }

    public boolean isSameLanguage(String lang) {
        return this.getLang().equalsIgnoreCase(lang);
    }

    public boolean isSameCountry(String country) {
        return this.getCountry().equalsIgnoreCase(country);
    }

    public String getLang(){
        return locale.getLanguage();
    }

    public String getCountry(){
        return locale.getCountry();
    }

    public Locale getLocale() {
        return locale;
    }

    public String getMessage() {
        return message;
    }

}
