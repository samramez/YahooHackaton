package com.samramez.yahoohackaton;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Timer;
import java.util.TimerTask;

public class ResultActivity extends AppCompatActivity  implements View.OnClickListener {

    String cityName;

    TextView cityNameTextView, todo, rest, places, arr1, arr2;
    ImageView im1, im2, im3;
    Button book1,book2;
    ProgressDialog pd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        Intent i = getIntent();
        cityName = i.getStringExtra("name");

        im1 = (ImageView) findViewById(R.id.placesImageView);
        im2 = (ImageView) findViewById(R.id.restaurantsImageView);
        im3 = (ImageView) findViewById(R.id.toDoImageView);
        cityNameTextView = (TextView) findViewById(R.id.cityNameTextView);
        arr1 = (TextView) findViewById(R.id.arriveTextView1);
        arr2 = (TextView) findViewById(R.id.arriveTextView2);
        book1 = (Button) findViewById(R.id.book1);
        book2 = (Button) findViewById(R.id.book2);

        book1.setOnClickListener(ResultActivity.this);
        book2.setOnClickListener(ResultActivity.this);



        if(cityName.equals("Monterrey")){

            arr1.setText("MON");
            arr2.setText("MON");
        }
        if(cityName.equals("Banff")){

            arr1.setText("BAN");
            arr2.setText("BAN");
        }
        if(cityName.equals("Nanaimo")){

            arr1.setText("NMO");
            arr2.setText("NMO");
        }


        setImages(cityName);


    }

    @Override
    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.book1:
    showpd();
                break;
            case R.id.book2:
showpd();
                break;

        }

    }

    public void showpd(){
        pd = ProgressDialog.show(ResultActivity.this,
                "Loading...", "Please wait...", false, false);
        pd.show();

        long delayInMillis = 3000;
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                pd.dismiss();

                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        // TODO Auto-generated method stub
                        showDialog();
                    }
                });

            }
        }, delayInMillis);
    }

        public void showDialog(){

        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
                ResultActivity.this);

        // set title
        alertDialogBuilder.setTitle("Discovered!");

        // set dialog message
        alertDialogBuilder
                .setMessage("Redirect to booking page now?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                    }
                })
            .setNegativeButton("No", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {

                }
            });

        // create alert dialog
        AlertDialog alertDialog = alertDialogBuilder.create();

        // show it
        alertDialog.show();
    }

    public void setImages(String name){

        if(cityName.equals("Monterrey")){
            cityNameTextView.setText("Monterrey");
            im1.setImageResource(R.drawable.monterey_places);
            im2.setImageResource(R.drawable.monterrey_rest);
            im3.setImageResource(R.drawable.monterrey_hotel);
        }


        if(cityName.equals("Banff")){
            cityNameTextView.setText("Banff");
            im1.setImageResource(R.drawable.banff_places);
            im2.setImageResource(R.drawable.banff_rest);
            im3.setImageResource(R.drawable.banff_hotel);
        }

        if(cityName.equals("Nanaimo")){
            cityNameTextView.setText("Nainamo");
            im1.setImageResource(R.drawable.nai_places);
            im2.setImageResource(R.drawable.nai_rest);
            im3.setImageResource(R.drawable.nai_hotel);
        }
    }
/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_result, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
*/

    public void openHotelsSearch(View view) {
        Intent intent = new Intent(this, WebviewActivity.class);
        if(cityName.equals("Monterrey")){
            intent.putExtra("keyName","Monterrey Hotels");
        }
        else if(cityName.equals("Banff")){
            intent.putExtra("keyName","Banff Hotels");
        }
        else if(cityName.equals("Nainamo")){
            intent.putExtra("keyName","Nainamo Hotels");
        }
        startActivity(intent);

    }

    public void openPlacesSearch(View view) {

        Intent intent = new Intent(this, WebviewActivity.class);
        if(cityName.equals("Monterrey")){
            intent.putExtra("keyName","Monterrey Places");
        }
        else if(cityName.equals("Banff")){
            intent.putExtra("keyName","Banff Places");
        }
        else if(cityName.equals("Nainamo")){
            intent.putExtra("keyName","Nainamo Places");
        }
        startActivity(intent);

    }

    public void openRestaurantsSearch(View view) {

        Intent intent = new Intent(this, WebviewActivity.class);
        if(cityName.equals("Monterrey")){
            intent.putExtra("keyName","Monterrey Restaurants");
        }
        else if(cityName.equals("Banff")){
            intent.putExtra("keyName","Banff Restaurants");
        }
        else if(cityName.equals("Nainamo")){
            intent.putExtra("keyName","Nainamo Restaurants");
        }
        startActivity(intent);
    }

    public void openAd(View view) {


    }
}
