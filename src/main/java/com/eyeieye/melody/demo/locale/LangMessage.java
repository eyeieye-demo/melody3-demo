package com.eyeieye.melody.demo.locale;

import com.eyeieye.melos.web.locale.VisitorLocale;

import java.text.MessageFormat;
import java.util.Locale;

/**
 * 各语言信息
 * Created by bbt on 2019/07/19.
 */
public class LangMessage {

    /**
     * 语言项
     */
    private LangItem[] items;
    /**
     * 构造函数
     * @param items
     */
    public LangMessage(LangItem... items) {
        this.items = items;
    }

    /**
     * 获取当前语言环境信息
     * @return
     */
    public String getMessage(){
        return getMessage(getCurrentLocale(), items[0].getMessage());
    }

    /**
     * 获取指定语言环境信息
     * @param locale 语言环境
     * @return
     */
    public String getMessage(Locale locale) {
        //默认中文
        locale = locale == null ? getCurrentLocale() : locale;
        //获取指定的语言和地域
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
        //没有最符合的，找出地域符合的
        for (LangItem item : items) {
            if (item.isSameCountry(lang)) {
                return item.getMessage();
            }
        }
        //仍没有符合的默认第一项
        return items[0].getMessage();
    }

    /**
     * 获取当前语言环境信息
     * <p>
     *     消息示例：您正在执行"{0}"操作，验证码为"{1}"，请于"{2}"分钟内完成。
     * </p>
     * @param args 占位符参数
     * @return
     */
    public String getMessage(String... args){
        return getMessage(getCurrentLocale(), args);
    }

    /**
     * 获取指定语言环境信息
     * <p>
     *     消息示例：您正在执行"{0}"操作，验证码为"{1}"，请于"{2}"分钟内完成。
     * </p>
     * @param locale 语言环境
     * @param args 占位符参数
     * @return
     */
    public String getMessage(Locale locale, String... args) {
        String message = getMessage(locale);
        return message == null ? null : new MessageFormat(message, locale).format(args);
    }

    /**
     * 获得当前语言环境
     * @return
     */
    public Locale getCurrentLocale() {
        Locale now = VisitorLocale.getCurrent();
        return now == null ? VisitorLocale.getDefault() : now;
    }

}
