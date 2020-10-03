package com.example.tracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.InterstitialAd
import com.google.android.gms.ads.MobileAds

class MenuActivity : AppCompatActivity() {

    private lateinit var btShow:Button
    private lateinit var adView1: AdView
    private lateinit var adView2: AdView
    private lateinit var interstitialAd: InterstitialAd
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu)

//        btShow = findViewById(R.id.bt_show)
//        adView1 = findViewById(R.id.ad_view1)
//        adView2 = findViewById(R.id.ad_view2)
//
//        MobileAds.initialize(this,"ca-app-pub-6273282658515300~1329562880")
//
//        val adRequest = AdRequest.Builder().build()
//        adView1.loadAd(adRequest)
//        adView2.loadAd(adRequest)
//
//        interstitialAd.adUnitId = "ca-app-pub-6273282658515300/7818594399"
//        interstitialAd.loadAd(AdRequest.Builder().build())
//
//        btShow.setOnClickListener{
//            interstitialAd.show()
//        }
    }
}
