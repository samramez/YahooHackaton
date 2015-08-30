package com.samramez.yahoohackaton;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebSettings;
import android.webkit.WebView;

public class WebviewActivity extends AppCompatActivity {

    private WebView myWebView;
    private WebSettings webSettings;
    private String url = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_webview);

        String data = getIntent().getExtras().getString("keyName");

        url = "https://search.yahoo.com/search;_ylt=AwrBT6PX49xVDtAAkpKl87UF;_ylc=X1MDOTU4MTA0NjkEX3IDMgRmcgNzZnAEZ3ByaWQDRlFNU0c3YXdRVlNxUGw3NkFwc041QQRuX3JzbHQDMARuX3N1Z2cDMTAEb3JpZ2luA3NlYXJjaC55YWhvby5jb20EcG9zAzAEcHFzdHIDBHBxc3RybAMEcXN0cmwDNARxdWVyeQNzYW0gBHRfc3RtcAMxNDQwNTM5NjE1?p=";
        url += data + "+&fr=sfp&fr2=sb-top-search&iscqry=";

        myWebView = (WebView) findViewById(R.id.webview);
        myWebView.loadUrl(url);

        //Enabling user to zoom in the page.
        webSettings = myWebView.getSettings();
        webSettings.setBuiltInZoomControls(true);
        webSettings.setSupportZoom(true);

        //enabling JavaScript in the app
        webSettings.setJavaScriptEnabled(true);

    }
/*

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_webview, menu);
        return true;
    }
*/
/*
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
    }*/
}
