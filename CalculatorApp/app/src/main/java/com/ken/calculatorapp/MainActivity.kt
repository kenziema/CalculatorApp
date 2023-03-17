package com.ken.calculatorapp

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.DecimalFormat
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*
import java.util.Calendar


class MainActivity : AppCompatActivity() {

    private var tvSelectedDate : TextView? = null
    private var tvAgeInMinutes : TextView? = null
    private var tvAgeInHours : TextView? = null
    private var tvAgeInDays : TextView? = null
    private var tvAgeInWeeks : TextView? = null
    private var tvAgeInMonths : TextView? = null
    private var tvAgeInYears : TextView? = null


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnDatePicker : Button = findViewById(R.id.btnDatePicker)
        tvSelectedDate = findViewById(R.id.tvSelectedDate)
        tvAgeInMinutes = findViewById(R.id.tvAgeInMinutes)
        tvAgeInHours = findViewById(R.id.tvAgeInHours)
        tvAgeInDays = findViewById(R.id.tvAgeInDays)
        tvAgeInWeeks = findViewById(R.id.tvAgeInWeeks)
        tvAgeInMonths = findViewById(R.id.tvAgeInMonths)
        tvAgeInYears = findViewById(R.id.tvAgeInYears)


        btnDatePicker.setOnClickListener{

            clickDatePicker()
        }

    }

    private fun clickDatePicker(){

        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)
        val dpd = DatePickerDialog(this,
            { _, selectedYear, selectedMonth, selectedDayOfMonth ->

                Toast.makeText(this, "Date picked $selectedDayOfMonth/${selectedMonth+1}/$selectedYear",
                    Toast.LENGTH_LONG).show()


                val selectedDate = "$selectedDayOfMonth/${selectedMonth+1}/$selectedYear"

                val dateBener = SimpleDateFormat("EEEE, MMM d, yyyy", Locale.ENGLISH)
                dateBener.timeZone = TimeZone.getTimeZone("UTC")


                tvSelectedDate?.text = selectedDate

                val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)
//                val sdfFormatter = sdf.parse()
                var sdf2 = SimpleDateFormat("dd/MMM/yyyy", Locale.ENGLISH)

                val theDate: Date = sdf.parse(selectedDate)
                theDate?.let{
                    val selectedDateInMinutes = theDate.time/60000

                    val selectedDateInHours = theDate.time/3600000

                    val selectedDateInDays = theDate.time/86400000

                    val selectedDateInWeeks = theDate.time/604800016.56

                    val selectedDateInMonths = theDate.time/2629800000

                    val selectedDateInYears = theDate.time/31557600000

                    val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))
                    currentDate?.let {
                        val currentDateInMinutes = currentDate.time/60000

                        val currentDateInHours = currentDate.time/3600000

                        val currentDateInDays = currentDate.time/86400000

                        val currentDateInWeeks = currentDate.time/604800000

                        val currentDateInMonths = currentDate.time/2629743833

                        val currentDateInYears = currentDate.time/31556926000


                        val differenceInMinutes = currentDateInMinutes - selectedDateInMinutes

                        val differenceInHours = currentDateInHours - selectedDateInHours

                        val differenceInDays = currentDateInDays - selectedDateInDays

                        val differenceInWeeks = currentDateInWeeks - selectedDateInWeeks

                        val differenceInMonths = currentDateInMonths - selectedDateInMonths

                        val differenceInYears = currentDateInYears - selectedDateInYears

                        var mf = NumberFormat.getNumberInstance(Locale.US)
                        var dotInMinutes = mf.format(differenceInMinutes)
                        var dotInHours = mf.format(differenceInHours)
                        var dotInDays = mf.format(differenceInDays)
//                        var dotInWeeks = mf.format(differenceInWeeks)

                        var dotInMonths = mf.format(differenceInMonths)


                        var df = DecimalFormat("#,###.##")
                        var twoDigit = df.format(differenceInWeeks)
//                        var twoDigitMonths = df.format(differenceInMonths)

//                        var twoDigitTrial = "%,1f".format(Locale.ENGLISH, differenceInWeeks)



                        tvAgeInMinutes?.text = dotInMinutes
                        tvAgeInHours?.text = dotInHours
                        tvAgeInDays?.text = dotInDays
                        tvAgeInWeeks?.text = twoDigit
                        tvAgeInMonths?.text = dotInMonths
                        tvAgeInYears?.text = differenceInYears.toString()

                    }

                }

            },
            year,
            month,
            day
        )

        dpd.datePicker.maxDate = System.currentTimeMillis() - 86400000
        dpd.show()

    }





}