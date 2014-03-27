package com.vessel.landingdemo;

import org.json.JSONException;
import org.json.JSONObject;

import com.vessel.VesselAB;
import com.vessel.enums.VesselEnums.TestVariation;
import com.vessel.interfaces.ABListener;
import com.vessel.landingdemo.R;
import com.vessel.ui.FragmentBase;
import com.vessel.ui.FragmentSocialEmail;
import com.vessel.ui.FragmentNormalEmail;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class HomeActivity extends FragmentActivity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home_activity);

		if (savedInstanceState != null) {
			//if we're being restored from a previous state,
			// then we don't need to do anything and should return or else
			// we could end up with overlapping fragments.
			return;
		}else{
			// Or you can load specific test and make decision 
			loadUi();
		}
	}


	protected void loadUi() {
	
		// We are running another test to decide between Social Sign up vs Email Sign up
		VesselAB.getVariationForTest("socialemail", new ABListener() {

			@Override
			public void testNotAvailable(TestVariation arg0) {
				showNormalSignUpFlow();
			}


			@Override
			public void testAvailable(String testName, TestVariation variation) {
				VesselAB.activateTest(testName);
				if(variation == TestVariation.B){
					showSocialEmailFlow();
				}else{
					showNormalSignUpFlow();
				}
				Log.d("VesselSDK", "HomeActivity - test loaded " + testName);
			}
		});
	}


	protected void showSocialEmailFlow() {
		runOnUiThread(new Runnable() {

			@Override
			public void run() {
				FragmentBase newFrgment = new FragmentSocialEmail();

				getSupportFragmentManager().beginTransaction()
				.add(R.id.fragment_container, newFrgment).commit();
				Toast.makeText(getApplicationContext(), "Currently we have variation "+ VesselAB.whichVariation("socialemail"), Toast.LENGTH_LONG).show();


			}
		});
	}


	protected void showNormalSignUpFlow() {
		runOnUiThread(new Runnable() {

			@Override
			public void run() {
				FragmentBase newFrgment = new FragmentNormalEmail();

				getSupportFragmentManager().beginTransaction()
				.add(R.id.fragment_container, newFrgment).commit();
				Toast.makeText(getApplicationContext(), "Currently we have variation "+ VesselAB.whichVariation("socialemail"), Toast.LENGTH_LONG).show();
			}
		});
	}


	public void signUpWithFacebook(View v) {
		Toast.makeText(getApplicationContext(), "signUpWithFacebook checkpoint", Toast.LENGTH_LONG).show();
		// Report Checkpoint to track funnel
		VesselAB.reportCheckpoint("signUpWithFacebook");
	}

	public void signUpWithGplus(View v){
		Toast.makeText(getApplicationContext(), "signUpWithGplus checkpoint", Toast.LENGTH_LONG).show();
		
		// You can optionally report meta data at checkpoint e.g.
		try {
			JSONObject metaData = new JSONObject();
			metaData.put("paidUser", true);
			metaData.put("itemPurchased", "benz");
			metaData.put("adCampaign", "bannerAds");
			VesselAB.reportCheckpoint("signUpWithGPlus", metaData);
		} catch (JSONException e) {
		}
	}


	public void normalSignUp(View v){
		Toast.makeText(getApplicationContext(), "normalSignUp checkpoint", Toast.LENGTH_LONG).show();
		VesselAB.reportCheckpoint("normalEmailSignUp");
	}

	@Override
	protected void onResume() {
		VesselAB.onResume(HomeActivity.this);
		super.onResume();
	}

	@Override
	protected void onPause() {
		VesselAB.onPause(HomeActivity.this);
		super.onPause();
	}

}
