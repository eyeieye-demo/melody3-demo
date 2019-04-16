package com.eyeieye.melody.demo.conf;


import java.text.MessageFormat;
import java.util.Locale;

import com.eyeieye.melos.web.locale.VisitorLocale;

/**
 * 语言信息仓库
 * Created by bbt on 2018/7/6.
 */
public enum LangRepertory {

    langtest(
            l(Locale.CHINESE, "正在使用中文"),
            l(Locale.JAPANESE, "日本語を使っている"),
            l(Locale.ENGLISH, "Using English"));
    /**
     * 语言项
     */
    private LangItem[] items;
    /**
     * 构造函数
     * @param items
     */
    LangRepertory(LangItem... items) {
        this.items = items;
    }

    /**
     * 获取当前语言环境信息
     * @return
     */
    public String getMessage() {
        return getMessage(getCurrentLocale(), items[0].getMessage());
    }

    public Locale getCurrentLocale() {
        Locale now = VisitorLocale.getCurrent();
        return now == null ? VisitorLocale.getDefault() : now;
    }
    /**
     * 获取当前语言环境信息
     * @param locale 语言环境
     * @return
     */
    public String getMessage(Locale locale) {
        return getMessage(locale, items[0].getMessage());
    }
    /**
     * 获取指定语言环境信息
     * @param locale 语言环境
     * @param defaultMessage 默认信息
     * @return
     */
    public String getMessage(Locale locale, String defaultMessage) {
        if (locale == null) {
            //第一项为默认项
            return defaultMessage;
        }
        String lang = locale.getLanguage();
        String country = locale.getCountry();
        //找出最符合的
        for (LangItem item : items) {
            if (item.isSameLanguage(lang) && item.isSameCountry(country)) {
                return item.getMessage();
            }
        }
        //没有最符合的，找出语言符合的
        for (LangItem item : items) {
            if (item.isSameLanguage(lang)) {
                return item.getMessage();
            }
        }
        //都不符合，默认第一项
        return defaultMessage;
    }
    /**
     * 获取指定语言环境信息
     * @param locale 语言环境
     * @param args 占位符参数
     * @param defaultMessage 默认信息
     * @return
     */
    public String getMessage(Locale locale, String[] args, String defaultMessage) {
        String message = getMessage(locale, defaultMessage);
        return message == null ? null : new MessageFormat(message, locale).format(args);
    }


    /**
     * 语言项
     */
    private static class LangItem {
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
    /**
     * 可以少写几个字
     */
    private static LangItem l(Locale locale, String message) {
        return new LangItem(locale, message);
    }
}
