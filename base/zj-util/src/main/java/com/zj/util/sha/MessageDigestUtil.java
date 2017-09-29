/**
 * $Id$
 */
package com.zj.util.sha;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MessageDigestUtil {

    private static char hexdigits[]={'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

    public static String getMessageDigest(byte[] buffer, String key) {
        MessageDigest digest;
        try {
            digest=MessageDigest.getInstance(key);
            digest.update(buffer);
            return bytes2Hex(digest.digest());
        } catch(NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String bytes2Hex(byte[] bts) {
        String des="";
        String tmp=null;
        for(int i=0; i < bts.length; i++) {
            tmp=(Integer.toHexString(bts[i] & 0xFF));
            if(tmp.length() == 1) {
                des+="0";
            }
            des+=tmp;
        }
        return des;
    }

    public static String getMD5(byte[] buffer) {
        return getMessageDigest(buffer, "MD5");
    }

    public static String getMD5(String str) {
        try {
            return getMD5(str.getBytes("ISO-8859-1"));
        } catch(UnsupportedEncodingException e) {
        }
        return null;
    }

    /**
     * 获得keystore文件MD5
     * @param file
     * @return
     */
    public static String getFileMD5(File file) {
        FileInputStream fis=null;
        try {
            MessageDigest md=MessageDigest.getInstance("MD5");
            fis=new FileInputStream(file);
            byte[] buffer=new byte[2048];
            int length=-1;
            while((length=fis.read(buffer)) != -1) {
                md.update(buffer, 0, length);
            }

            byte[] b=md.digest();
            return byteToHexString(b);
        } catch(Exception ex) {
            ex.printStackTrace();
            return null;
        } finally {
            try {
                fis.close();
            } catch(IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public static String getSHA1(byte[] buffer) {
        return getMessageDigest(buffer, "SHA-1");
    }

    public static String getSHA1(String str) {
        try {
            return getSHA1(str.getBytes("ISO-8859-1"));
        } catch(UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String getSHA256(byte[] buffer) {
        return getMessageDigest(buffer, "SHA-256");
    }

    public static String getSHA256(String str) {
        try {
            return getSHA256(str.getBytes("ISO-8859-1"));
        } catch(UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String byteToHexString(byte[] tmp) {
        String s;
        char str[]=new char[16 * 2];
        int k=0;
        for(int i=0; i < 16; i++) {
            byte byte0=tmp[i];
            str[k++]=hexdigits[byte0 >>> 4 & 0xf];
            str[k++]=hexdigits[byte0 & 0xf];
        }
        s=new String(str);
        return s;
    }
}
