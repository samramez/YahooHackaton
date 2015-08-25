package com.samramez.yahoohackaton;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import android.widget.ListView;

class Place{
	String name;
	int[] imageResource;
}

public class MainActivity extends AppCompatActivity implements OnClickListener {

	EditText editTextCurrentLocation;
	Button buttonDepart, buttonReturn, buttonDiscover;
	final int BUTTON_DEPART = 1;
	final int BUTTON_RETURN = 2;
	int buttonFlag;
	Places places;
	ArrayList<Place> placesList;
	ListView listView;
	ImageLoader imgLoader;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		editTextCurrentLocation = (EditText) findViewById(R.id.editTextCurrentLocation);
		buttonDepart = (Button)findViewById(R.id.buttonDateDepart);
		buttonReturn = (Button)findViewById(R.id.buttonDateReturn);
		buttonDiscover = (Button)findViewById(R.id.buttonDiscover);
		listView = (ListView) findViewById(R.id.listViev);

		buttonDepart.setOnClickListener(this);
		buttonReturn.setOnClickListener(this);
		buttonDiscover.setOnClickListener(this);

		imgLoader = ImageLoader.getInstance();

		popultatePlaces();

		listView.setAdapter(new ImageAdapter());
	}

	public void popultatePlaces(){
		place = new Place();
		places.name = Miami;
		places.imageResource = {R.drawable.slider1,R.drawable.slider2, R.drawable.slider3, R.drawable.slider4};
		placesList.add(place);

		place = new Place();
		places.name = San Francisco;
		places.imageResource = {R.drawable.slider1,R.drawable.slider2, R.drawable.slider3, R.drawable.slider4};
		placesList.add(place);

		place = new Place();
		places.name = Chicago;
		places.imageResource = {R.drawable.slider1,R.drawable.slider2, R.drawable.slider3, R.drawable.slider4};
		placesList.add(place);

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

	private void AnimateandSlideShow() {
		try
		{
			final Animation rotateimage = AnimationUtils.loadAnimation(MainActivity.this, R.anim.fade_in);
			final Animation fadeOut = AnimationUtils.loadAnimation(MainActivity.this, R.anim.fade_out);

			fadeOut.setAnimationListener(new AnimationListener() {

				@Override
				public void onAnimationStart(Animation animation) {
					// TODO Auto-generated method stub

				}

				@Override
				public void onAnimationRepeat(Animation animation) {
					// TODO Auto-generated method stub

				}

				@Override
				public void onAnimationEnd(Animation animation) {

					AnimateandSlideShow();
				}
			});

			rotateimage.setAnimationListener(new AnimationListener() {

				@Override
				public void onAnimationStart(Animation animation) {


				}

				@Override
				public void onAnimationRepeat(Animation animation) {


				}

				@Override
				public void onAnimationEnd(Animation animation) {

					//slidingimage.setImageResource(IMAGE_IDS[currentimageindex%IMAGE_IDS.length]);

					new Timer().schedule(new TimerTask() {
						@Override
						public void run() {
							runOnUiThread(new Runnable() {

								@Override
								public void run() {
									// TODO Auto-generated method stub
									slidingimage.startAnimation(fadeOut);
								}
							});
						}
					}, 5000);
				}
			});

			currentimageindex++;
			imgLoader.displayImage(IMAGE_IDS.get(currentimageindex % IMAGE_IDS.size()), slidingimage, options, new ImageLoadingListener() {
				@Override
				public void onLoadingStarted(String imageUri, View view) {

				}

				@Override
				public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
				}

				@Override
				public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
					slidingimage.startAnimation(rotateimage);
				}

				@Override
				public void onLoadingCancelled(String imageUri, View view) {
				}

			}, new ImageLoadingProgressListener() {
				@Override
				public void onProgressUpdate(String imageUri, View view, int current, int total) {
				}
			});
			// slidingimage.setImageResource(IMAGE_IDS.get(currentimageindex%IMAGE_IDS.size()));


		}
		catch(Exception e)
		{
			e.printStackTrace();
		}

	}

	public class ImageAdapter extends BaseAdapter {
		@Override
		public int getCount() {
			return placesList.size();
		}

		@Override
		public Object getItem(int position) {
			return null;
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			final ViewHolder holder;
			View view = convertView;
			try {

				if (view == null) {
					view = getLayoutInflater().inflate(R.layout.list_item_layout, parent, false);
					holder = new ViewHolder();
					assert view != null;

					holder.imageView = (ImageView) view.findViewById(R.id.imageViewPhotos);
					holder.cityName = (TextView) view.findViewById(R.id.textViewCityName);

					view.setTag(holder);
				} else {
					holder = (ViewHolder) view.getTag();
				}

				ResponseClass res = responseClassArrayList.get(position);

				if(res != null){
					holder.blogName.setText(res.blog_name);
					if(res.photos.size() > 0){
						imgLoader.displayImage(res.photos.get(0), holder.imageView, options);
						holder.photos.setText(String.valueOf(res.photos.size()) + " photos");
					}
				}

			} catch (Exception e) {
				e.printStackTrace();

			return view;
		}

		class ViewHolder {
			ImageView imageView;
			TextView cityName;
		}
	}

}
