package com.eyeieye.melody.demo.service;

import com.eyeieye.melody.demo.domain.DemoMoney;
import com.eyeieye.melody.demo.enums.CurrencyTypeEnum;

import java.math.BigDecimal;
import java.util.Locale;

public interface I18nService {

    /**
     * 将金额从一种货币类型转换成另一种货币的金额
      */
    DemoMoney getAmountByType(String beforeType, String afterType , BigDecimal amount) throws Exception;

    /**
    **将金额从默认货币类型转换成指定货币类型的金额
    * */
    DemoMoney getAmountByType(String newType, BigDecimal amount) throws Exception;

    /**
    ** 根据地域将金额从一种货币类型转换成另一种货币的金额
     * */
    DemoMoney getAmountByLocal(Locale beforeLocale, Locale afterLocale , BigDecimal amount) throws Exception;

    /**
    ** 根据地域将金额从默认货币类型转换成指定货币类型的金额
    */
    DemoMoney getAmountByLocal( Locale newLocale , BigDecimal amount) throws Exception;

    /**
    * 获得默认的货币类型
     * */
    CurrencyTypeEnum getDefaultType() throws Exception;

}
