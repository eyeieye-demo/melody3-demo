package com.eyeieye.melody.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.eyeieye.melos.util.StringUtil;
import com.eyeieye.melos.util.crypto.Crypto;

@Controller
@RequestMapping("crypto")
public class CryptoController {

    @Autowired
    @Qualifier("aesCrypto")
    Crypto aesCrypto;

    @Autowired
    @Qualifier("rsaCrypto")
    Crypto rsaCrypto;

    @Autowired
    @Qualifier("blowfishCrypto")
    Crypto blowfishCrypto;

    @RequestMapping("introduce")
    public void introducePage(ModelMap modelMap){}

    @RequestMapping(value="demo", method = RequestMethod.GET)
    public void demoPage(ModelMap modelMap){}

    @RequestMapping("encrypt")
    public String encrypt(String text, ModelMap modelMap) {
        if (StringUtil.isEmpty(text) == false) {
            modelMap.put("aesEncrypt", aesCrypto.encrypt(text));
            modelMap.put("rsaEncrypt", rsaCrypto.encrypt(text));
            modelMap.put("blowfishEncrypt", blowfishCrypto.encrypt(text));

            modelMap.put("text", text);
        }
        return "/crypto/demo";
    }

    @RequestMapping("decrypt")
    public String decrypt(String aesStr, String rsaStr, String blowfishStr, ModelMap modelMap) {
        String aesDecrypt = null;
        String rsaDecrypt = null;
        String blowfishDecrypt = null;
        if (StringUtil.isEmpty(aesStr) == false) {
            aesDecrypt = aesCrypto.dectypt(aesStr);
        }
        if (StringUtil.isEmpty(rsaStr) == false) {
            rsaDecrypt = rsaCrypto.dectypt(rsaStr);
        }
        if (StringUtil.isEmpty(blowfishStr) == false) {
            blowfishDecrypt = blowfishCrypto.dectypt(blowfishStr);
        }
        modelMap.put("aesDecrypt", aesDecrypt);
        modelMap.put("rsaDecrypt", rsaDecrypt);
        modelMap.put("blowfishDecrypt", blowfishDecrypt);

        modelMap.put("aesStr", aesStr);
        modelMap.put("rsaStr", rsaStr);
        modelMap.put("blowfishStr", blowfishStr);

        return "/crypto/demo";
    }
}
