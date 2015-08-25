package com.samramez.yahoohackaton;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity implements OnClickListener {

	EditText editTextCurrentLocation;
	Button buttonDepart, buttonReturn, buttonDiscover;
	final int BUTTON_DEPART = 1;
	final int BUTTON_RETURN = 2;
	int buttonFlag;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		editTextCurrentLocation = (EditText) findViewById(R.id.editTextCurrentLocation);
		buttonDepart = (Button)findViewById(R.id.buttonDateDepart);
		buttonReturn = (Button)findViewById(R.id.buttonDateReturn);
		buttonDiscover = (Button)findViewById(R.id.buttonDiscover);
		
		buttonDepart.setOnClickListener(this);
		buttonReturn.setOnClickListener(this);
		buttonDiscover.setOnClickListener(this);
	}
/*
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}*/
/*

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
*/

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
			case R.id.buttonDateDepart:
				buttonFlag = BUTTON_DEPART;
				DialogFragment dialogFragmentDepart = new StartDatePicker();
				dialogFragmentDepart.show(MainActivity.this.getSupportFragmentManager(), "start_date_picker_depart");
				
				break;
			case R.id.buttonDateReturn:
				buttonFlag = BUTTON_RETURN;
				DialogFragment dialogFragmentReturn = new StartDatePicker();
				dialogFragmentReturn.show(MainActivity.this.getSupportFragmentManager(), "start_date_picker_return");
				
				break;
		}
	}	
		
	class StartDatePicker extends DialogFragment implements DatePickerDialog.OnDateSetListener{
			
		@Override
		public Dialog onCreateDialog(Bundle savedInstanceState) {
			// TODO Auto-generated method stub
		        
			Calendar c = Calendar.getInstance();
			int startYear = c.get(Calendar.YEAR);
			int startMonth = c.get(Calendar.MONTH);
			int startDay = c.get(Calendar.DAY_OF_MONTH);
				
				// Use the current date as the default date in the picker
			DatePickerDialog dialog = new DatePickerDialog(MainActivity.this, this, startYear, startMonth, startDay);
		        
			return dialog;

		}
		@Override
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			// TODO Auto-generated method stub
			updateDateField(year, monthOfYear, dayOfMonth);
		}
	}
	
	public void updateDateField(int startYear, int startMonth, int startDay) {
		
		try {
			SimpleDateFormat initial = new SimpleDateFormat("MM-dd-yyyy", Locale.US);
			Date date = initial.parse("" + startMonth + "-" + startDay + "-" + startYear +"");
			
			SimpleDateFormat finalFormat = new SimpleDateFormat("EEE, MMM dd", Locale.US);
			
			String dateStr = finalFormat.format(date); 
					
			Log.d("MainActivity", "date: " + dateStr);
			if(buttonFlag == BUTTON_DEPART){
				buttonDepart.setText(dateStr);
			}
			else if(buttonFlag == BUTTON_RETURN){
				buttonReturn.setText(dateStr);
			}
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
