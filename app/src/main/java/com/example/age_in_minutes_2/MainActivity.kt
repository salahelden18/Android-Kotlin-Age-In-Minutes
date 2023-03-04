package com.example.age_in_minutes_2

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

//import androidx.compose.foundation.Text
//import androidx.compose.runtime.Composable
//import androidx.compose.ui.platform.setContent

class MainActivity() : AppCompatActivity() {
    private var tvSelectedDate: TextView? = null
    private var tvAgeInMinutes: TextView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvSelectedDate = findViewById(R.id.tvSelectedDate);
        tvAgeInMinutes = findViewById(R.id.tvAgeInMinutes)

        val btnDatePicker: Button = findViewById(R.id.btnDatePicker);

        btnDatePicker.setOnClickListener {
            clickDatePicker()
        }
    }

    private fun clickDatePicker (){
        val myCalender = Calendar.getInstance()
        val year = myCalender.get(Calendar.YEAR)
        val month = myCalender.get(Calendar.MONTH)
        val day = myCalender.get(Calendar.DAY_OF_MONTH)

      val dpd = DatePickerDialog(this, {
            view, selectedYear, selectedMonth, selectedDayOfMonth ->
            Toast.makeText(this,
            "Year was $selectedYear, month was ${selectedMonth + 1 } day of month was $selectedDayOfMonth", Toast.LENGTH_LONG).show()
            val selectedDate = "$selectedDayOfMonth/${selectedMonth + 1 }/$selectedYear"

            tvSelectedDate?.text = selectedDate

            val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)

            val theDate = sdf.parse(selectedDate)

          theDate?.let {
              val selectedDateInMinutes = theDate.time / 60000

              val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))

              currentDate?.let {
                  val currentDateInMinutes = currentDate.time / 60000

                  val differenceInMinutes = currentDateInMinutes - selectedDateInMinutes

                  tvAgeInMinutes?.text = differenceInMinutes.toString()
              }
          }
            },
            year, month, day
        )

        dpd.datePicker.maxDate = System.currentTimeMillis() - 86400000
        dpd.show()

    }
}