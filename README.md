<strong>AndroidSDK(Beta) for Vessel Visual Studio</strong>

Vessel Android SDK 2.0b1.

Installation instructions

Installation instructions are the same as our standard library, except for points 4 & 5.

1.Copy vesselsdk.jar to your libs folder <br>
2.Add "INTERNET" and "ACCESS_NETWORK_STATE" permissions to your manifest.<br>
3.Inside your Application's onCreate() method add this line :- VesselSDK.initialize(getApplicationContext(), secretKey);<br>
4. In your Activities, add the following calls inside onPause() and onCreate(). We recommend that you put this code in a base class, and to extend activities from this base class. <br>

Alternatively, you can extend all activities from com.vessel.activities.VesselActivity which implements the above code.
        @Override
        protected void onPause() {
          VesselAB.onPause(this);
          super.onPause();
        }

        @Override
        protected void onResume() {
          VesselAB.onResume(this);
          super.onResume();
        }
5 To design a test, you need to enable the debug flag in your manifest file. Inside AndroidManifest.xml

<application
        android:icon="@drawable/ic_launcher"
        android:label="@string/app_name"
        android:theme="@style/AppTheme"
        android:name="name.app"
        android:debuggable="true">
...
...
</application>

Note that this is only for designing an test. Once you are done with design, you can remove this flag for production use.

<strongUsing Vessel Studio</strong>

1.Start the application on your mobile phone.<br>
2.Create a new test<br>
3.Select Vessel Studio.<br>
4.You should see your device listed. Click on the device.<br>
5.You app screen should be mirrored on the web page. You can now add a variation and make changes by selecting an element.
