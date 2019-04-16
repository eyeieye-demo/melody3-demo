package com.eyeieye.melody.demo.service.impl;

import com.eyeieye.melody.demo.domain.DemoMoney;
import com.eyeieye.melody.demo.enums.CurrencyTypeEnum;
import com.eyeieye.melody.demo.service.I18nService;
import com.eyeieye.melos.util.Money;
import com.eyeieye.melos.util.StringUtil;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Locale;

@Service
public class I18nServiceImpl implements I18nService {

    Logger logger = LoggerFactory.getLogger(I18nService.class);

    @Value("${melody.currency.defaultType:CNY}")
    String defaultType = "CNY";
    @Value("${melody.web.locale.defatul:zh_CN}")
    String defaultLocale;

    @Override
    public DemoMoney getAmountByType(String beforeType, String afterType, BigDecimal amount) throws Exception {
        if (StringUtil.isBlank(beforeType) || StringUtil.isBlank(afterType)) {
            throw new Exception("传入的货币类型为空");
        }
        if (CurrencyTypeEnum.hasType(beforeType) == false) {
            logger.warn("传入的货币类型不存在，type=[" + beforeType + "]");
            beforeType = defaultType;
        }
        if (CurrencyTypeEnum.hasType(afterType) == false) {
            logger.warn("传入的货币类型不存在，type=[" + afterType + "]");
            afterType = defaultType;
        }

        DemoMoney money = new DemoMoney();
        BigDecimal result = null;

        CurrencyTypeEnum orgType = CurrencyTypeEnum.getType(beforeType);
        CurrencyTypeEnum newType = CurrencyTypeEnum.getType(afterType);

        money.setType(newType);

        if (amount == null) {
            money.getMoney().setAmount(new BigDecimal(0));
        } else {
            money.getMoney().setAmount(amount);

            BigDecimal rate = newType.getExchangeRate().divide(orgType.getExchangeRate());
            money.setMoney(money.getMoney().multiply(rate));
        }
        return money;
    }

    @Override
    public DemoMoney getAmountByType(String newType, BigDecimal amount) throws Exception {
        return getAmountByType(newType, defaultType, amount);
    }


    @Override
    public DemoMoney getAmountByLocal(Locale beforeLocale, Locale afterLocale, BigDecimal amount) throws Exception {
        if (beforeLocale == null || afterLocale == null) {
            throw new Exception("传入地域为空");
        }
        if (CurrencyTypeEnum.getByLocale(beforeLocale) == null) {
            logger.warn("传入的货币类型不存在，type=[" + beforeLocale + "]");
            beforeLocale = getDefaultLocale();
        }
        if (CurrencyTypeEnum.getByLocale(afterLocale) == null) {
            logger.warn("传入的货币类型不存在，type=[" + afterLocale + "]");
            afterLocale = getDefaultLocale();
        }

        DemoMoney money = new DemoMoney();
        BigDecimal result = null;

        CurrencyTypeEnum orgType = CurrencyTypeEnum.getByLocale(beforeLocale);
        CurrencyTypeEnum newType = CurrencyTypeEnum.getByLocale(afterLocale);

        money.setType(newType);
        money.setMoney(new Money());

        if (amount == null) {
            money.getMoney().setAmount(new BigDecimal(0));
        } else {
            money.getMoney().setAmount(amount);
            BigDecimal rate = newType.getExchangeRate().divide(orgType.getExchangeRate());
            money.setMoney(money.getMoney().multiply(rate));
        }
        return money;
    }

    @Override
    public DemoMoney getAmountByLocal(Locale newLocale, BigDecimal amount) throws Exception {

        return getAmountByLocal(getDefaultLocale(), newLocale, amount);
    }

    @Override
    public CurrencyTypeEnum getDefaultType() throws Exception {
        if (CurrencyTypeEnum.hasType(defaultType)) {
            return CurrencyTypeEnum.getType(defaultType);
        }
        throw new Exception("默认货币类型不存在，defaultType = [" + defaultType + "]");
    }

    private Locale getDefaultLocale() {
        String[] tmp = StringUtil.split(defaultLocale, "_");
        return new Locale(tmp[0], tmp[1]);
    }
}
