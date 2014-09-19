package com.nishada.bustracker;

import android.support.v7.app.ActionBarActivity;
import android.support.v4.app.Fragment;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {

	Button exit, complain, time, feedback, tracker; // for the buttons of option activity	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//each button is initialized
		exit = (Button) findViewById(R.id.bExit); 		
		complain = (Button) findViewById(R.id.bComplain);
		time = (Button) findViewById(R.id.bTime);
		feedback = (Button) findViewById(R.id.bFeedback);
		tracker = (Button) findViewById(R.id.bTrack);
		
		//function for the exit button
		exit.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				finish();  
			}
		});
		
		
		feedback.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent openFeedback = new Intent("android.intent.action.FEEDBACK");
				startActivity(openFeedback);
				
			}
		});
		
	tracker.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent openTracker = new Intent("android.intent.action.TRACKER");
				startActivity(openTracker);
			}
		});
		
	
		// function for the complain button
		complain.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent openComplain = new Intent("android.intent.action.COMPLAIN");
				startActivity(openComplain);
				
			}
		});
		
		time.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent openTimeView = new Intent("android.intent.action.TIME");
				startActivity(openTimeView);
			}
		});
		

		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
		
		if(isConnected()){ 
			TextView tv = (TextView) findViewById(R.id.connectionStatus);
			tv.setText("connected...");
			tv.setBackgroundColor(0xFF00CC00);
		}
		else{
			TextView tv = (TextView) findViewById(R.id.connectionStatus);
			tv.setText("not connected...");
			tv.setBackgroundColor(Color.RED);
			
			AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
			alert.setTitle("connection error");
			alert.setMessage("please enable your data connection to proceed");
		
			alert.show();
			
		}
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		if(isConnected()){ 
			TextView tv = (TextView) findViewById(R.id.connectionStatus);
			tv.setText("connected...");
			tv.setBackgroundColor(0xFF00CC00);
		}
		else{
			TextView tv = (TextView) findViewById(R.id.connectionStatus);
			tv.setText("not connected...");
			tv.setBackgroundColor(Color.RED);
			
			AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
			alert.setTitle("connection error");
			alert.setMessage("please enable your data connection to proceed ");
		
			alert.show();
			
		}
		
	}
	
	
	protected boolean isConnected(){  // used to check whether device capable of network access
		try{
				ConnectivityManager cm = (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
				NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
				boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();
		                      
				return isConnected;
		}
		catch(Exception e){
			Log.wtf("error found","error in the connection func");
			return false;
		}
		
				
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {		
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_main, container,
					false);
			return rootView;
		}
	}

	
}
