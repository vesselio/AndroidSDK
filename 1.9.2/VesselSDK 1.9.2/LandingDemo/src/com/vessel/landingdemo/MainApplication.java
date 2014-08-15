package com.vessel.landingdemo;

import com.vessel.VesselSDK;

import android.app.Application;

public class MainApplication extends Application {
	
	// This is sample app key. Register your application.
	final String YOUR_SECRET_KEY = "TUhCR1FqN1VrbnJxQVk2bWxDZzNKYU9z";
	@Override
	public void onCreate() {
		super.onCreate();
			
		// Lets initialize VesselSDK using application secret key.
		VesselSDK.initialize(getApplicationContext(), YOUR_SECRET_KEY);
	}

}
