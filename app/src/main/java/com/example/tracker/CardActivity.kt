package com.example.tracker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.craftman.cardform.Card
import com.craftman.cardform.CardForm
import com.craftman.cardform.OnPayBtnClickListner
import com.example.tracker.pregnant.DoctorDetailsActivity
import androidx.core.app.ComponentActivity.ExtraData
import androidx.core.content.ContextCompat.getSystemService
import android.icu.lang.UCharacter.GraphemeClusterBreak.T
import com.example.tracker.pregnant.DoctorDetailsCardActivity


class CardActivity : AppCompatActivity() {

    private lateinit var cardForm: CardForm
    private lateinit var txtDes:TextView
    private lateinit var btnPay: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card)

        cardForm = findViewById(R.id.card_form)
        txtDes = findViewById(R.id.payment_amount)
        btnPay = findViewById(R.id.btn_pay)


        txtDes.setText("$10")
        btnPay.setText(String.format("Payer %s", txtDes.text))


//        cardForm.setPayBtnClickListner(OnPayBtnClickListner() {
//            fun onClick(card: Card) {
//                startActivity(Intent(this, DoctorDetailsActivity::class.java))
//                Toast.makeText(this, "Name : " + card.name + "Last 4 digits: " + card.last4, Toast.LENGTH_SHORT).show()
//            }
//        })
//        cardForm.setOnClickListener {
//            startActivity(Intent(this, DoctorDetailsActivity::class.java))
//        }

//        cardForm.setOnClickListener(object : View.OnClickListener{
////            override fun onClick(v: View?) {
////                startActivity(Intent(this@CardActivity, DoctorDetailsActivity::class.java))
////            }})

        cardForm.setPayBtnClickListner { card ->
            Toast.makeText(
                applicationContext,
                "Success",
                Toast.LENGTH_SHORT
            ).show()
            startActivity(Intent(this@CardActivity, DoctorDetailsCardActivity::class.java))
            finish()

        }
    }
}
