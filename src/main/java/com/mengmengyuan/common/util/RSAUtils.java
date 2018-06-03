/**    
 * 文件名：RSAUtils.java    
 *    
 * 版本信息：    
 * 日期：2018年6月1日    
 * Copyright 足下 Corporation 2018     
 * 版权所有    
 *    
 */
package com.mengmengyuan.common.util;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

/**
 * 
 * 项目名称：mengmengyuan 类名称：RSAUtils 类描述： 创建人：Administrator 创建时间：2018年6月1日
 * 下午5:31:21 修改人：Administrator 修改时间：2018年6月1日 下午5:31:21 修改备注：
 * 
 * @version
 * 
 */
public class RSAUtils {

    private static byte[] signSHA1(String data, PrivateKey privateKey) throws NoSuchAlgorithmException,
            InvalidKeySpecException, InvalidKeyException, SignatureException, UnsupportedEncodingException {
        Signature signature = Signature.getInstance("SHA1withRSA");
        signature.initSign(privateKey);
        signature.update(data.getBytes("UTF-8"));
        return signature.sign();
    }

    private static boolean verifySHA1(String data, byte[] sign, PublicKey publicKey) {
        if (data != null && sign != null && publicKey != null) {
            try {
                Signature signetcheck = Signature.getInstance("SHA1withRSA");
                signetcheck.initVerify(publicKey);
                signetcheck.update(data.getBytes("UTF-8"));
                return signetcheck.verify(sign);
            } catch (Exception var4) {
                return false;
            }
        } else {
            return false;
        }
    }

    public static String signSHA1(String data, String privateKey) {
        try {
            PrivateKey pkey = restorePrivateKey(privateKey);
            byte[] signDatas = signSHA1(data, pkey);
            return new String(Base64.getEncoder().encode(signDatas));
        } catch (Exception var4) {
            var4.printStackTrace();
            return null;
        }
    }

    public static boolean verify256(String data, String publicKeyStr, String sign) {
        byte[] signed = Base64.getDecoder().decode(sign.getBytes());
        PublicKey publicKey = restorePublicKey(publicKeyStr);
        return verifySHA1(data, signed, publicKey);
    }

    public static PublicKey restorePublicKey(String publickeyStr) {
        byte[] encodedKey = Base64.getDecoder().decode(publickeyStr.getBytes());
        X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(encodedKey);

        try {
            KeyFactory factory = KeyFactory.getInstance("RSA");
            PublicKey publicKey = factory.generatePublic(x509EncodedKeySpec);
            return publicKey;
        } catch (InvalidKeySpecException | NoSuchAlgorithmException var5) {
            var5.printStackTrace();
            return null;
        }
    }

    public static PrivateKey restorePrivateKey(String privateKey) {
        byte[] encodedKey = Base64.getDecoder().decode(privateKey.getBytes());
        PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(encodedKey);

        try {
            KeyFactory factory = KeyFactory.getInstance("RSA");
            return factory.generatePrivate(pkcs8EncodedKeySpec);
        } catch (InvalidKeySpecException | NoSuchAlgorithmException var4) {
            var4.printStackTrace();
            return null;
        }
    }
}
