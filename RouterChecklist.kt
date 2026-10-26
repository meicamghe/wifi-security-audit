package com.example.wifiaudit

data class ChecklistItem(
    val title: String,
    val description: String,
    var isChecked: Boolean = false
)

object RouterChecklist {
    fun defaultItems(): List<ChecklistItem> = listOf(
        ChecklistItem(
            "خاموش کردن WPS",
            "در تنظیمات روتر به بخش Wireless > WPS بروید و آن را Disable کنید. " +
            "WPS دارای آسیب‌پذیری شناخته‌شده Brute-force PIN است."
        ),
        ChecklistItem(
            "تغییر پسورد پیش‌فرض ادمین",
            "پسورد ورود به پنل روتر (admin/admin معمول) را تغییر دهید."
        ),
        ChecklistItem(
            "استفاده از WPA2/WPA3",
            "اطمینان حاصل کنید رمزنگاری Wi-Fi روی WPA2-AES یا WPA3 است، نه WEP یا Open."
        ),
        ChecklistItem(
            "به‌روزرسانی فریمور",
            "آخرین نسخه فریمور رسمی سازنده را نصب کنید تا باگ‌های امنیتی شناخته‌شده رفع شوند."
        ),
        ChecklistItem(
            "غیرفعال کردن مدیریت از راه دور",
            "گزینه Remote Management / Remote Access را در تنظیمات روتر خاموش کنید."
        ),
        ChecklistItem(
            "تغییر SSID پیش‌فرض",
            "نام شبکه پیش‌فرض (مثل مدل روتر) را تغییر دهید تا مهاجم نتواند مدل دقیق را حدس بزند."
        )
    )
}
