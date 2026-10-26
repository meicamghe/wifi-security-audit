package com.example.wifiaudit

import android.content.Context
import android.net.wifi.WifiManager
import java.net.InetAddress

object GatewayHelper {

    /**
     * دریافت آدرس IP گیت‌وی (روتر) شبکه متصل فعلی
     */
    fun getGatewayIp(context: Context): String? {
        val wifiManager = context.applicationContext
            .getSystemService(Context.WIFI_SERVICE) as WifiManager
        val dhcpInfo = wifiManager.dhcpInfo ?: return null
        val gateway = dhcpInfo.gateway
        if (gateway == 0) return null

        return try {
            val bytes = byteArrayOf(
                (gateway and 0xFF).toByte(),
                (gateway shr 8 and 0xFF).toByte(),
                (gateway shr 16 and 0xFF).toByte(),
                (gateway shr 24 and 0xFF).toByte()
            )
            InetAddress.getByAddress(bytes).hostAddress
        } catch (e: Exception) {
            null
        }
    }
}
