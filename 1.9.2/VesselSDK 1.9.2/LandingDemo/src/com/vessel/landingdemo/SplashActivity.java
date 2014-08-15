package com.vessel.landingdemo;


import com.vessel.VesselAB;
import com.vessel.enums.VesselEnums.TestVariation;
import com.vessel.interfaces.ABListener;
import com.vessel.landingdemo.R;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
public class SplashActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.splash_main);

		// If on boarding test is available, show user guide , if not redirect users to home screen
		VesselAB.getVariationForTest("onboarding", new ABListener() {

			@Override
			public void testNotAvailable(TestVariation arg0) {
				// Go to home activity once test is loaded or not available 
				gotoHome();
			}

			@Override
			public void testAvailable(String s, TestVariation variation) {
				// Test is now available, make sure its activated before we use it.
				VesselAB.activateTest(s);
				
				// if we receive variation B then show onboarding on control show home.
				if(variation == TestVariation.B){
					goToGuide();
				}else{
					gotoHome();
				}
			}
		});

	}

	protected void goToGuide() {
		Intent intent = new Intent();
		intent.setClass(SplashActivity.this, GuideActivity.class);
		startActivity(intent);
		finish();

	}

	protected void gotoHome() {
		Intent intent = new Intent();
		intent.setClass(SplashActivity.this, HomeActivity.class);
		startActivity(intent);
		finish();
	}

}
