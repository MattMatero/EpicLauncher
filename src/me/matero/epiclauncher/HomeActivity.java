package me.matero.epiclauncher;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class HomeActivity extends Activity{
	
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home_activity);
		
		showApps();
	}
	
	public void showApps(){
		
		Button showAppsButton = (Button)findViewById(R.id.apps_button);
		showAppsButton.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				Intent showIntent = new Intent(HomeActivity.this,AppsListActivity.class);
				startActivity(showIntent);
			}
			
		});
	}
}
