package com.interopx.fhir.parser.util;

import java.security.spec.KeySpec;
import java.util.Base64;

import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;

public class AES256 {
	   private static final String SECRET_KEY = "eCW_super_secret_key";
	   private static final String SALT = "neverAddTooMuchSalt!!!";

	   public static String encrypt(byte[] strToEncrypt) {
	      try {
	         System.out.println("Call to encrypt");
	         byte[] iv = new byte[16];
	         IvParameterSpec ivspec = new IvParameterSpec(iv);
	         SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
	         KeySpec spec = new PBEKeySpec("eCW_super_secret_key".toCharArray(), "neverAddTooMuchSalt!!!".getBytes(), 65536, 256);
	         SecretKey tmp = factory.generateSecret(spec);
	         SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");
	         Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
	         cipher.init(1, secretKey, ivspec);
	         return Base64.getEncoder().encodeToString(cipher.doFinal(strToEncrypt));
	      } catch (Exception var8) {
	         System.out.println("Error while encrypting: " + var8.toString());
	         return null;
	      }
	   }

	   public static String decrypt(String strToDecrypt) {
	      try {
	         byte[] iv = new byte[16];
	         IvParameterSpec ivspec = new IvParameterSpec(iv);
	         SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256");
	         KeySpec spec = new PBEKeySpec("eCW_super_secret_key".toCharArray(), "neverAddTooMuchSalt!!!".getBytes(), 65536, 256);
	         SecretKey tmp = factory.generateSecret(spec);
	         SecretKeySpec secretKey = new SecretKeySpec(tmp.getEncoded(), "AES");
	         Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
	         cipher.init(2, secretKey, ivspec);
	         return new String(cipher.doFinal(Base64.getDecoder().decode(strToDecrypt)));
	      } catch (Exception var8) {
	         System.out.println("Error while decrypting: " + var8.toString());
	         return "Error while decrypting: " + var8.toString();
	      }
	   }
	}
