package com.eyeieye.melody.demo.locale;

import java.util.Locale;

/**
 * 语言信息仓库
 * Created by bbt on 2019/07/19.
 */
public enum LangRepertory {

    lang_test("正在使用中文","日本語を使っている", "Using English.")
    ;

    /**
     * 语言项
     */
    private LangMessage langMessage;

    /**
     * 构造函数
     * @param zh 中文
     * @param en 英文
     */
    LangRepertory(String zh, String jp, String en) {
        LangItem zhItem = new LangItem(Locale.CHINESE, zh);
        LangItem jpItem = new LangItem(Locale.JAPANESE, jp);
        LangItem enItem = new LangItem(Locale.ENGLISH, en);
        this.langMessage = new LangMessage(zhItem, jpItem, enItem);
    }

    /**
     * 获取信息对象
     * @return
     */
    public LangMessage getLangMessage() {
        return this.langMessage;
    }

    /**
     * 获取当前语言环境信息
     * @return
     */
    public String getMessage() {
        return this.langMessage.getMessage();
    }

    /**
     * 获取当前语言环境信息
     * <p>
     *     消息示例：您正在执行"{0}"操作，验证码为"{1}"，请于"{2}"分钟内完成。
     * </p>
     * @param args 占位符参数
     * @return
     */
    public String getMessage(String... args) {
        return this.langMessage.getMessage(args);
    }

}
