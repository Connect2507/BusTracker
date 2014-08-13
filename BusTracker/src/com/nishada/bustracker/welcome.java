package com.nishada.bustracker;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class welcome extends Activity{

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.welcome);
		
		Thread timer = new Thread(){
			public void run(){
				try{
					sleep(1000);
				}
				catch(InterruptedException e){
					e.printStackTrace();
				}
				finally{
					Intent openOptions = new Intent("android.intent.action.OPTIONS");
					startActivity(openOptions);
				}
			}
		};
		
		timer.start();
		
	}

	
}
