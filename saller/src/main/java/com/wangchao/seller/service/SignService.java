package com.wangchao.seller.service;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 签名服务
 */
@Service
public class SignService {

    static Map<String,String> PUBLIC_KEYS = new HashMap<>();
    static {
        PUBLIC_KEYS.put("1000","MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC4hnPcApbCnwGPz" +
                "dgCApk39Kk2wzXd2TTw5973v31XlRfcJRrVMBcbmb7mriy4xPRUMXuNCbjl9YgYCzRXS25E" +
                "Ws/Zd1kl3JKP4EeVdpnki8Kc3EGJdHITk9DgmfAygT2AK8E8VDF7AjDG+XLfhtOCahYTlLZyDxs0jMLiIV7SswIDAQAB");
    }

    /**
     * 根据授权编号获取公钥
     * @param authId
     * @return
     */
    public String getPublicKey(String authId){
        return PUBLIC_KEYS.get(authId);
    }

}
