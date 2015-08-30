package com.samramez.yahoohackaton;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

import org.json.JSONArray;
import org.json.JSONObject;

import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageLoadingListener;
import com.nostra13.universalimageloader.core.assist.ImageLoadingProgressListener;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.samramez.yahoohackaton.ResultActivity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

class Place{
	String name;
	String[] imageResource = new String[3];
}

public class MainActivity extends AppCompatActivity implements OnClickListener {

	ProgressDialog pd;
	EditText editTextCurrentLocation;
	TextView t1, t2, t3;
	Button buttonDepart, buttonReturn, buttonDiscover;
	int BUTTON_DEPART = 1;
	int BUTTON_RETURN = 2;
	int buttonFlag;
	Place place;
	ArrayList<Place> placesList;
	ListView listView;
	ImageLoader imgLoader;
	DisplayImageOptions options;
	public int currentimageindex = 0;
	ImageView logo;

	ImageView slidingImage1, slidingImage2, slidingImage3;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		editTextCurrentLocation = (EditText) findViewById(R.id.editTextCurrentLocation);
		buttonDepart = (Button)findViewById(R.id.buttonDateDepart);
		buttonReturn = (Button)findViewById(R.id.buttonDateReturn);
		buttonDiscover = (Button)findViewById(R.id.buttonDiscover);

		slidingImage1 = (ImageView) findViewById(R.id.imageViewPhotos1);
		slidingImage2 = (ImageView) findViewById(R.id.imageViewPhotos2);
		slidingImage3 = (ImageView) findViewById(R.id.imageViewPhotos3);
		logo = (ImageView) findViewById(R.id.imageViewLogo);

		logo.setVisibility(ImageView.VISIBLE);

		t1 = (TextView) findViewById(R.id.textViewCityName1);
		t2 = (TextView) findViewById(R.id.textViewCityName2);
		t3 = (TextView) findViewById(R.id.textViewCityName3);

		t1.setVisibility(TextView.INVISIBLE);
		t2.setVisibility(TextView.INVISIBLE);
		t3.setVisibility(TextView.INVISIBLE);

		slidingImage1.setOnClickListener(this);
		slidingImage2.setOnClickListener(this);
		slidingImage3.setOnClickListener(this);

		buttonDepart.setOnClickListener(this);
		buttonReturn.setOnClickListener(this);
		buttonDiscover.setOnClickListener(this);

		options = new DisplayImageOptions.Builder()
				.considerExifParams(true)
				.build();
		imgLoader = ImageLoader.getInstance();
		initImageLoader(getApplicationContext());

		popultatePlaces();

//		animateandSlideShow(placesList.get(0).imageResource, slidingImage1);
//		animateandSlideShow(placesList.get(1).imageResource, slidingImage2);
//		animateandSlideShow(placesList.get(2).imageResource, slidingImage3);
	}

	public void initImageLoader(Context context) {

		options = new DisplayImageOptions.Builder()
				.cacheInMemory(true)
				.cacheOnDisc(true)
				.considerExifParams(true)
				.bitmapConfig(Bitmap.Config.RGB_565)
				.build();

		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
				.threadPriority(Thread.NORM_PRIORITY - 2)
				.denyCacheImageMultipleSizesInMemory()
				.discCacheFileNameGenerator(new Md5FileNameGenerator())
				.tasksProcessingOrder(QueueProcessingType.LIFO)
				.writeDebugLogs() // Remove for release app
				.build();
// 		Initialize ImageLoader with configuration.
		imgLoader.init(config);

	}


	public void popultatePlaces(){
		placesList = new ArrayList<Place>();
		place = new Place();
		place.name = "Monterrey";
		place.imageResource[0] = "http://culturacolectiva.com/wp-content/uploads/2013/03/paseo-santa-lucia-monterrey.jpg";
		place.imageResource[1] = "http://www.mudanzas.com.mx/mexico/img/mudanza/mudanzas-en-monterrey.jpg";
		place.imageResource[2] = "http://www.huilenviajes.com.ar/storage/images/CARIBE/05ee40f4020d99fb0b3b0ff5d84a4d2f.jpg";
		placesList.add(place);

		place = new Place();
		place.name = "Banff";
		place.imageResource[0] = "http://www.hdwallpapers.in/walls/moraine_lake_banff_national_park-wide.jpg";
		place.imageResource[1] = "http://wallpapers.ae/wp-content/uploads/2015/01/Banff-National-Park-Canada-Wallpaper.jpg";
		place.imageResource[2] = "http://images.trvl-media.com/media/content/shared/images/travelguides/destination/10550/Banff-Gondola-27699.jpg";
		placesList.add(place);

		place = new Place();
		place.name = "Nanaimo";
		place.imageResource[0] = "http://movetonanaimo.com/wp-content/uploads/2012/02/nanaimo-real-estate.jpg";
		place.imageResource[1] = "http://canadaalive.files.wordpress.com/2014/05/city_of_nanaimo.jpg";
		place.imageResource[2] = "http://www.crisland.com/uploads/nanaimo_img4.jpg";
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

			case R.id.buttonDiscover:

				/*String url = "https://query.yahooapis.com/v1/public/yql?q=select%20*%20from%20boss.search%20where%20secret%3D%22269ff00174172abdc899cf4e2bce677744580ded%22%20and%20ck%3D%22dj0yJmk9MlpnTXcwV0E3cGFuJmQ9WVdrOU1rRnNjbTl0TkRnbWNHbzlNQS0tJnM9Y29uc3VtZXJzZWNyZXQmeD1mZQ--%22%20and%20q%3D%22monterrey%22%20and%20service%3D%22images%22%3B&format=json&diagnostics=true&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys&callback=";
				new BackgroundTask().execute(url);*/

				logo.setVisibility(ImageView.GONE);

				pd = ProgressDialog.show(MainActivity.this,
						"Loading...", "Please wait...", false, false);

				pd.show();

				long delayInMillis = 4000;
				Timer timer = new Timer();
				timer.schedule(new TimerTask() {
					@Override
					public void run() {
						if(pd!=null)
							pd.dismiss();
					}
				}, delayInMillis);

				animateandSlideShow(placesList.get(0).imageResource, slidingImage1);
				animateandSlideShow(placesList.get(1).imageResource, slidingImage2);
				animateandSlideShow(placesList.get(2).imageResource, slidingImage3);

				break;

			case R.id.imageViewPhotos1:
				Intent i = new Intent(MainActivity.this, ResultActivity.class);
				i.putExtra("name", "Monterrey");
				startActivity(i);
				break;
			case R.id.imageViewPhotos2:
				Intent j = new Intent(MainActivity.this, ResultActivity.class);
				j.putExtra("name", "Banff");
				startActivity(j);
				break;
			case R.id.imageViewPhotos3:
				Intent k = new Intent(MainActivity.this, ResultActivity.class);
				k.putExtra("name", "Nanaimo");
				startActivity(k);
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

	public class BackgroundTask extends AsyncTask<String, Integer, String>{

		@Override
		protected void onPreExecute() {
			// TODO Auto-generated method stub
			super.onPreExecute();

			pd = ProgressDialog.show(MainActivity.this,
					"Loading...", "Please wait...", false, false);
			pd.show();
		}

		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub

			JSONParser jParser = new JSONParser();

			String urlResponse = jParser.getJSONFromUrl(params[0]);

			return urlResponse;
		}

		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);

			if(result != null){
				System.out.print(result);
				parseJson(result);
			}
		}
	}

	public void parseJson(String result) {
		try {
			JSONObject jObj = new JSONObject(result);
			JSONObject query = jObj.getJSONObject("query");

			System.out.println(query.toString());
			
			/*for (int i = 0; i < response.length(); i++) {

				JSONObject obj = response.getJSONObject(i);

				ResponseClass res = new ResponseClass();

				res.id = obj.getInt("id");
				System.out.println(obj.getInt("id"));

				try {
					res.blog_name = obj.getString("blog_name");
					System.out.println(obj.getString("blog_name"));
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					res.date = obj.getString("date");
					System.out.println(obj.getString("date"));
				} catch (Exception e) {
					e.printStackTrace();
				}
				try {
					res.caption = obj.getString("caption");
					System.out.println(obj.getString("caption"));
				} catch (Exception e) {
					e.printStackTrace();
				}

				try {
					JSONArray photos = obj.getJSONArray("photos");
					for (int j = 0; j < photos.length(); j++) {
						String urlStr = photos.getJSONObject(j).getJSONArray("alt_sizes").getJSONObject(0).getString("url");
						res.photos.add(urlStr);
						System.out.println(urlStr);
					}

				} catch (Exception e) {
					e.printStackTrace();
				}

				responseClassArrayList.add(res);
			}

			listView.setAdapter(new ImageAdapter());
*/
			if(pd != null){
				pd.dismiss();
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
	}


	public void animateandSlideShow(final String[] imageResource, final ImageView imageView) {
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

					animateandSlideShow( imageResource, imageView);
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
									imageView.startAnimation(fadeOut);
								}
							});
						}
					}, 5000);
				}
			});

			currentimageindex++;
			imgLoader.displayImage(imageResource[currentimageindex % imageResource.length], imageView, options, new ImageLoadingListener() {
				@Override
				public void onLoadingStarted(String imageUri, View view) {

				}

				@Override
				public void onLoadingFailed(String imageUri, View view, FailReason failReason) {
				}

				@Override
				public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
					imageView.startAnimation(rotateimage);

						t1.setVisibility(TextView.VISIBLE);
						t2.setVisibility(TextView.VISIBLE);
						t3.setVisibility(TextView.VISIBLE);
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

}
