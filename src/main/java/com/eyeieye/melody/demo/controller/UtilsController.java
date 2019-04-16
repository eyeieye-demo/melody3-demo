package com.eyeieye.melody.demo.controller;

import com.eyeieye.melody.demo.web.action.utils.DemoBean;
import com.eyeieye.melos.util.ArrayUtil;
import com.eyeieye.melos.util.DateUtil;
import com.eyeieye.melos.util.HostUtil;
import com.eyeieye.melos.util.Money;
import com.eyeieye.melos.util.StringUtil;
import com.eyeieye.melos.util.uuid.UUIDGenerator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import java.math.BigDecimal;
import java.text.ParseException;
import java.util.Currency;
import java.util.Date;
import java.util.List;
import java.util.Random;

@Controller
@RequestMapping("utils")
public class UtilsController {

    @Autowired
    private DemoBean demoBean;

    private static final String CHINA_CURRENCY_CODE = "CNY";
    private static final String AMERICA_CURRENCY_CODE = "USD";

    @RequestMapping("dateutil")
    public void dateutil(){

    }
    @RequestMapping("arrayutil")
    public void arrayutil(){

    }

    @RequestMapping("hostutil")
    public void hostutil(){

    }

    @RequestMapping("moneyutil")
    public void moneyutil(){

    }


    @RequestMapping("stringutil")
    public void stringutil(){

    }

    @RequestMapping("demo/dateutil")
    public void dateUtilPage(Integer afterDays, Integer beforeDays, ModelMap modelMap) {
        //获取当前日期时间
        String nowDateTime = DateUtil.getDateTime(DateUtil.getDatePattern(), new Date());
        //获取今天是星期几：
        int weeks = DateUtil.getDay(new Date());
        //获取几天后的日期
        Date nextDate = null;
        if ((afterDays == null || afterDays == 0) == false) {
            nextDate = DateUtil.getRelativeDate(new Date(), afterDays);
            modelMap.put("nextWeeks", getWeeks(DateUtil.getDay(nextDate)));
            modelMap.put("afterDays", afterDays);
        }
        //获取几天前的日期
        Date lastDate = null;
        if (!(beforeDays == null || beforeDays == 0)) {
            lastDate = DateUtil.getRelativeDate(new Date(), beforeDays * -1);
            modelMap.put("lastWeeks", getWeeks(DateUtil.getDay(lastDate)));
            modelMap.put("beforeDays", beforeDays);
        }

        modelMap.put("dateNow", nowDateTime);
        modelMap.put("weeks", getWeeks(weeks));


    }

    @RequestMapping("demo/stringutil")
    public void stringUtilPage(String str1, String str2, ModelMap modelMap) {
        modelMap.put("str1", str1);
        modelMap.put("str2", str2);
        modelMap.put("isEmpty", StringUtil.isEmpty(str1));
        modelMap.put("default", StringUtil.defaultIfBlank(str1, "_"));
        modelMap.put("trim", StringUtil.trim(str1));
        modelMap.put("isNumber", StringUtil.isNumber(str1));
        modelMap.put("toUpper", StringUtil.toUpperCase(str1));
        modelMap.put("toLower", StringUtil.toLowerCase(str1));
        modelMap.put("subString", StringUtil.substring(str1, 0, 2));
        modelMap.put("alignLeft", StringUtil.alignLeft(str1, 10, "abc"));
        modelMap.put("camelCase", StringUtil.toCamelCase(str1));
        modelMap.put("equal", StringUtil.equals(str1, str2));
        String[] array = {str1, str2};
        modelMap.put("join", StringUtil.join(array));
        modelMap.put("indexOf", StringUtil.indexOf(str1, str2));
        modelMap.put("replace", StringUtil.replace(str1, str2, "jojo"));
        modelMap.put("difference", StringUtil.difference(str1, str2));
    }

    @RequestMapping("demo/hostutil")
    public void hostUtilPage(ModelMap modelMap) {
        HostUtil.HostInfo hostInfo = HostUtil.getHostInfo();
        HostUtil.Ipv4Info ipv4Info = HostUtil.getIpv4Info();
        modelMap.put("hostName", hostInfo.getName());
        modelMap.put("hostAddress", hostInfo.getAddress());
        modelMap.put("ipv4Addresses", ipv4Info.getAddress());
        modelMap.put("firstAddress", ipv4Info.getFristAddress());
    }

    @RequestMapping("demo/moneyutil")
    public void moneyUtilPage(BigDecimal amount, Integer currencyCodeIndex, ModelMap modelMap) {
        String[] currencyCodes = {CHINA_CURRENCY_CODE, AMERICA_CURRENCY_CODE};
        modelMap.put("currencyCodes", currencyCodes);
        modelMap.put("amount", amount);
        modelMap.put("currencyCodeIndex", currencyCodeIndex);

        if(currencyCodeIndex == null || amount == null){
            return;
        }

        Currency currency = null;
        if (currencyCodeIndex >= 1 && currencyCodeIndex <= currencyCodes.length) {
            currency = Currency.getInstance(currencyCodes[currencyCodeIndex-1]);
        }

        Money money = new Money(amount,currency);

        modelMap.put("compare", money.compareTo(new Money(100,currency)));

        Money afterAdd = money.add(new Money(10,currency));
        modelMap.put("add", afterAdd);

        Money afterMultiply = money.multiply(2);
        modelMap.put("multiply",afterMultiply);

        modelMap.put("allocate1",money.allocate(5));

        long[] targets = {1L, 2L, 3L};
        modelMap.put("allocate2",money.allocate(targets));

    }

    public void arrayUtil(){
        Double[] array1 = new Double[5];
        Double[] array2 = null;
        Random ra = new Random(10000);
        for(int i =0; i<5 ; i++){
            array1[i] =ra.nextDouble();
        }

        //判断数组是否为空
        System.out.println("判断数组是否为空：array1:"+ ArrayUtil.isEmpty(array1)+" | array2:"+ArrayUtil.isEmpty(array2));

        //数组转字符串
        System.out.println("数组转字符串：array1:"+ArrayUtil.toString(array1));

        //反转数组
        ArrayUtil.reverse(array1);
        System.out.println("反转数组"+ArrayUtil.toString(array1));

        //toFixedList和toList
        List toFixedList = ArrayUtil.toFixedList(array1);
        List toList = ArrayUtil.toList(array1);
        Double[] clone = (Double[]) ArrayUtil.clone(array1);
        System.out.println("\n----------toFixedList和toList----------");
        System.out.println("toFixedList的第一个值："+toFixedList.get(0));
        System.out.println("toList的第一个值："+toList.get(0));
        System.out.println("**********\n将array1中第一个元素修改为10.0后");
        array1[0] = 10.0D;
        System.out.println("toFixedList的第一个值："+toFixedList.get(0));
        System.out.println("toList的第一个值："+toList.get(0));

    }

    @RequestMapping("demo/arrayutil")
    public void arrayUtilPage(ModelMap modelMap){}


    @RequestMapping("demo/objectfactory")
    public void objectFactoryDemo(){
       demoBean.ObjectFactoryDemo();
    }

    @RequestMapping("objectfactory")
    public void objectFactory(){
    }

    @Autowired
    UUIDGenerator uuidGenerator;

    @RequestMapping("uuidgenerator")
    public void uuid(ModelMap modelMap){
        modelMap.put("UUID", uuidGenerator.gain());
    }


    public static void main(String[] args) throws ParseException, InterruptedException {
        new UtilsController().arrayUtil();

    }

    private String getWeeks(int weeks) {
        switch (weeks) {
            case 0:
                return "星期日";
            case 1:
                return "星期一";
            case 2:
                return "星期二";
            case 3:
                return "星期三";
            case 4:
                return "星期四";
            case 5:
                return "星期五";
            case 6:
                return "星期六";
            default:
                return "未知";
        }
    }

    @RequestMapping("introduce")
    public void introducePage(ModelMap modelMap){
        modelMap.put("selected","utils");
    }


}
