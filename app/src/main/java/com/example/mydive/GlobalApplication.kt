package com.example.mydive

import android.app.Application
import com.kakao.sdk.common.KakaoSdk

class GlobalApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        //카카오 sdk초기화
        KakaoSdk.init(this, "523260ee8e1315eb832da8d2763af21e")
    }
}