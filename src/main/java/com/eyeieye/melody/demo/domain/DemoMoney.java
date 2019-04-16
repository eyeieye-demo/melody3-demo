package com.eyeieye.melody.demo.domain;

import com.eyeieye.melody.demo.enums.CurrencyTypeEnum;
import com.eyeieye.melos.util.Money;

public class DemoMoney {
    CurrencyTypeEnum type;
    Money money;

    public CurrencyTypeEnum getType() {
        return type;
    }

    public void setType(CurrencyTypeEnum type) {
        this.type = type;
    }

    public Money getMoney() {
        return money;
    }

    public void setMoney(Money money) {
        this.money = money;
    }
}
