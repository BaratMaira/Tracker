package com.example.tracker.pregnant

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import com.example.tracker.R
import com.example.tracker.weeks.FirstWeekActivity
import kotlinx.android.synthetic.main.activity_calendar.*

class CalendarActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar)

        val displayShow = supportActionBar?.setDisplayShowHomeEnabled(true)
        val displayHomeAsUpEnabled = supportActionBar?.setDisplayHomeAsUpEnabled(true)

            val calendarView = calendarView.setOnDateChangeListener { view, year, month, dayOfMonth ->
                val msg = "Due date: " + dayOfMonth + "/" + (month + 1) + "/" + year
                text.setText(msg)

                buttonSave.setOnClickListener{
                    val intent = Intent(this, FirstWeekActivity::class.java)
                    intent.putExtra("date", msg)
                    startActivity(intent)
                    finish()
                }



//                val cal = Calendar.getInstance()
//                val date = intent.getStringExtra("Date")
//                date_textView.setText(date)
//                cal.timeInMillis(date)
//
//                val calendar = Calendar.getInstance()
//                val simpleDateFormat = SimpleDateFormat("dd/MM/yyyy")
//                val currentDate = simpleDateFormat.format(calendar.getTime())
//                currentDate_textView.setText(currentDate)
//                calendar.timeInMillis(currentDate)
//
//                val difference = calendar.get(Calendar.YEAR) - cal.get(Calendar.YEAR)
//
//                weeks_textView.setText(Integer.toString((difference)))
            }

    }

    override fun onBackPressed() {
        super.onBackPressed()
        setResult(Activity.RESULT_CANCELED)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home){
            finish()
        }
        return super.onOptionsItemSelected(item)
    }
}
