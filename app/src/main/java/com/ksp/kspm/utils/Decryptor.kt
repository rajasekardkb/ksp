package com.ksp.kspm.utils

import java.math.BigInteger
import java.nio.charset.StandardCharsets
import java.security.InvalidAlgorithmParameterException
import java.security.InvalidKeyException
import java.security.MessageDigest
import java.security.NoSuchAlgorithmException
import javax.crypto.BadPaddingException
import javax.crypto.Cipher
import javax.crypto.IllegalBlockSizeException
import javax.crypto.NoSuchPaddingException
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

object Decryptor {
    private const val initialVector = "0123456789012345"
    private const val sKey = "GoWhere#35cc2be91\$"

    @Throws(
        NoSuchAlgorithmException::class,
        NoSuchPaddingException::class,
        InvalidKeyException::class,
        InvalidAlgorithmParameterException::class,
        IllegalBlockSizeException::class,
        BadPaddingException::class
    )
    fun decrypt(encrypted: String): String {

//Algorithm type
        val cipher = Cipher.getInstance("AES/CTR/NoPadding")

//Initial vector to bytes
        val ivBytes = initialVector.toByteArray(StandardCharsets.UTF_8)
        val ivSpec = IvParameterSpec(ivBytes)

//Secret key conversion
        /*Removed after the accepted answer
       MessageDigest md = MessageDigest.getInstance("MD5");
       byte[] thedigest = md.digest(secretBytes);*/
//        val md = MessageDigest.getInstance("MD5")
//        val sKeyByteArray = md.digest(sKey.toByteArray())
        val secretKeySpec = SecretKeySpec(getMd5(sKey).toByteArray(), "AES")

//Cipher initialisation with decryption mode
        cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivSpec)
        return String(cipher.doFinal(hexStringToByteArray(encrypted)))
    }

    fun hexStringToByteArray(s: String): ByteArray {
        val len = s.length
        val data = ByteArray(len / 2)
        var i = 0
        while (i < len) {
            data[i / 2] = ((Character.digit(s[i], 16) shl 4)
                    + Character.digit(s[i + 1], 16)).toByte()
            i += 2
        }
        return data
    }

    //    public static byte[] hexStringToByteArray1(String s) {
    //        byte[] b = new byte[s.length() / 2];
    //        for (int i = 0; i < b.length; i++) {
    //            int index = i * 2;
    //            int v = Integer.parseInt(s.substring(index, index + 2), 16);
    //            b[i] = (byte) v;
    //        }
    //        return b;
    //        }
    private fun getMd5(input: String): String {
        return try {

            // Static getInstance method is called with hashing MD5
            val md = MessageDigest.getInstance("MD5")

            // digest() method is called to calculate message digest
            // of an input digest() return array of byte
            val messageDigest = md.digest(input.toByteArray())

            // Convert byte array into signum representation
            val no = BigInteger(1, messageDigest)

            // Convert message digest into hex value
            var hashtext = no.toString(16)
            while (hashtext.length < 32) {
                hashtext = "0$hashtext"
            }

            hashtext
        } // For specifying wrong message digest algorithms
        catch (e: NoSuchAlgorithmException) {
            throw RuntimeException(e)
        }
    }
}