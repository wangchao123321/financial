package com.wangchao.util;

import org.junit.Test;

public class RsaUtilTest {

    private static final String publicKey = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQC4hnPcApbCnwGPzdgCApk39Kk2\n" +
            "wzXd2TTw5973v31XlRfcJRrVMBcbmb7mriy4xPRUMXuNCbjl9YgYCzRXS25EWs/Z\n" +
            "d1kl3JKP4EeVdpnki8Kc3EGJdHITk9DgmfAygT2AK8E8VDF7AjDG+XLfhtOCahYT\n" +
            "lLZyDxs0jMLiIV7SswIDAQAB";
    private static final String privateKey = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBALiGc9wClsKfAY/N\n" +
            "2AICmTf0qTbDNd3ZNPDn3ve/fVeVF9wlGtUwFxuZvuauLLjE9FQxe40JuOX1iBgL\n" +
            "NFdLbkRaz9l3WSXcko/gR5V2meSLwpzcQYl0chOT0OCZ8DKBPYArwTxUMXsCMMb5\n" +
            "ct+G04JqFhOUtnIPGzSMwuIhXtKzAgMBAAECgYAezLpr/7agV6AQG8CxXTOr5leU\n" +
            "P3MvS4ENsPZDjh/izY0E/uCCsxs4TSpTIMFwOde6ceP4f6XwJFKKCNy8sWd/EsPN\n" +
            "t3zL5AKLfHPulbNRWySzfVDoyNBPvV6j6xez69ISDhq7sOlEHRSEEVcWM3wKZQZP\n" +
            "BWGu8TLB9tajD0FDoQJBAN1e0VeOAx1DuZXY10QhUMWmVdcYBGqhwE2lGYqlNWa7\n" +
            "t2gjT/gC/ZH1PdD0m82PJBFwD5/Q23IRas5HCWlC1mMCQQDVZBovIi8nuED1nLRg\n" +
            "b5DicxX90+8y1gvDdYkYnbFcXJ+8RgE+Ui2gtTOyEquqDL5df/BCS7uRmfeTXL88\n" +
            "g1txAkAf3pWu0DAcl3JxYz1ifpVdTxwOklOYcl74DqvFrREqEB5QbL0HjI6q466Z\n" +
            "lY2Uo2Xi6MonDO961Y0V015EfC3XAkEAyY7/CIxCfEMW4Kg+3GQlNzA7koEwDBWQ\n" +
            "EPShJWY2FCbNHpoFLKeUv5rIlKf8C+hzm39wIj7EkfgbmM7fN7pfkQJAAVtRPWzx\n" +
            "7k6lrdA61vwSgKvKaXFslAY691byVUKNKTSjiJWmWzojwcTm0r/E8/bs+swYOlWk\n" +
            "nHxBrqi0c+DgQA==";


    @Test
    public void signTest(){
        String text = "imooc";
        String sign = RSAUtil.sign(text,privateKey);
        System.out.println(sign);
        System.out.println(RSAUtil.verify(text, sign, publicKey));
    }
}
