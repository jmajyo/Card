package com.jmajyo.cards;

import android.app.Application;
import android.util.Log;

import com.squareup.picasso.Picasso;

public class CardApplication extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d("Application", "Creating application");

        Picasso.with(getApplicationContext()).setLoggingEnabled(true);
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        Log.d("Application", "Low memory detected");
    }
}
