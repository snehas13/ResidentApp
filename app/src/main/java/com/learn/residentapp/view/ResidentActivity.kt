package com.learn.residentapp.view

import android.app.DatePickerDialog
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.MotionEvent
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import com.learn.domain.entities.Resident
import com.learn.residentapp.R
import com.learn.residentapp.Utility
import com.learn.residentapp.Utility.DATA
import com.learn.residentapp.Utility.EDIT
import com.learn.residentapp.databinding.ResidentDetailsBinding
import com.learn.residentapp.viewmodel.ResidentViewModel
import kotlinx.android.synthetic.main.resident_details.*
import java.util.*

class ResidentActivity : AppCompatActivity() {

    lateinit var viewModel: ResidentViewModel
    lateinit var binding :  ResidentDetailsBinding
    var actionString: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.resident_details)
        viewModel = ViewModelProviders.of(this).get(ResidentViewModel::class.java)
        actionString = intent.action

        if(actionString == EDIT) {
            var resident = intent.getParcelableExtra<Resident>(DATA)
            viewModel.setResident(resident)
        }
        binding.residentViewModel = viewModel

        birthDateValue.setOnTouchListener(object : View.OnTouchListener {
            override fun onTouch(v: View?, event: MotionEvent?): Boolean {
                when (event?.action) {
                    MotionEvent.ACTION_DOWN -> pickDate()
                }

                return v?.onTouchEvent(event) ?: true
            }
        })
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_resident, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_save -> {
                viewModel.onSaveClicked(actionString)
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun pickDate() {
        var cal = getCalendar()
        val year = cal.get(Calendar.YEAR)
        val month = cal.get(Calendar.MONTH)
        val day = cal.get(Calendar.DAY_OF_MONTH)
        val datePickerDialog = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, yearSelected, monthSelected, daySelected ->
            // Display Selected date in TextViewDate Field
            cal.set(yearSelected,monthSelected,daySelected)
            Log.e("ResidentActivity"," date selected = $yearSelected $monthSelected $daySelected")
            viewModel.birthDate = Utility.df.format(cal.time)
            viewModel.age = Utility.calculateAge(cal)
            binding.invalidateAll()

        }, year, month, day)
        datePickerDialog.show()
    }

    fun getCalendar() : Calendar {
        var calender = Calendar.getInstance()
        if(viewModel.birthDate.isNotEmpty()) {
            val date = Utility.df.parse(viewModel.birthDate)
            calender.time = date
        }
        return calender
    }

}