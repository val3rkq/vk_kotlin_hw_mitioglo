package com.example.gif_vault_app.services

import android.content.Context
import android.security.keystore.KeyGenParameterSpec
import android.security.keystore.KeyProperties
import android.util.Base64
import java.security.KeyStore
import javax.crypto.Cipher
import javax.crypto.KeyGenerator
import javax.crypto.SecretKey
import javax.crypto.spec.GCMParameterSpec

class KeystoreService(context: Context) {

    private val keyStore: KeyStore = KeyStore.getInstance("AndroidKeyStore").apply { load(null) }
    private val sharedPreferences = context.getSharedPreferences("secure_prefs", Context.MODE_PRIVATE)

    // create key
    private fun createKey() {
        val keyGenerator = KeyGenerator.getInstance(KeyProperties.KEY_ALGORITHM_AES, "AndroidKeyStore")
        keyGenerator.apply {
            init(
                KeyGenParameterSpec.Builder("apiKeyAlias", KeyProperties.PURPOSE_ENCRYPT or KeyProperties.PURPOSE_DECRYPT)
                    .setBlockModes(KeyProperties.BLOCK_MODE_GCM)
                    .setEncryptionPaddings(KeyProperties.ENCRYPTION_PADDING_NONE)
                    .build()
            )
        }
        keyGenerator.generateKey()
    }

    // get key
    private fun getKey(): SecretKey {
        return keyStore.getKey("apiKeyAlias", null) as SecretKey
    }

    // encrypt
    fun encryptData(plainText: String): String {
        val secretKey = getKey()
        val cipher = Cipher.getInstance("AES/GCM/NoPadding")
        cipher.init(Cipher.ENCRYPT_MODE, secretKey)

        val iv = cipher.iv
        val encryption = cipher.doFinal(plainText.toByteArray())

        return Base64.encodeToString(iv + encryption, Base64.DEFAULT)
    }

    // decrypt
    fun decryptData(encryptedData: String): String {
        val secretKey = getKey()
        val decodedData = Base64.decode(encryptedData, Base64.DEFAULT)
        val iv = decodedData.copyOfRange(0, 12)
        val encryptedBytes = decodedData.copyOfRange(12, decodedData.size)

        val cipher = Cipher.getInstance("AES/GCM/NoPadding")
        val gcmParameterSpec = GCMParameterSpec(128, iv)
        cipher.init(Cipher.DECRYPT_MODE, secretKey, gcmParameterSpec)

        val decryptedData = cipher.doFinal(encryptedBytes)
        return String(decryptedData)
    }

    // save into SharedPreferences
    fun saveEncryptedApiKey(apiKey: String) {
        val encryptedApiKey = encryptData(apiKey)
        sharedPreferences.edit().putString("encrypted_api_key", encryptedApiKey).apply()
    }

    // get from SharedPreferences
    fun getDecryptedApiKey(): String? {
        val encryptedApiKey = sharedPreferences.getString("encrypted_api_key", null)
        return encryptedApiKey?.let { decryptData(it) }
    }
}
