package me.matero.epiclauncher;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class AppsListActivity extends Activity {
	private PackageManager manager;
	private List<AppDetail> apps;
	private ListView list;
	
	protected void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.apps_list_activity);
		
		loadApps();
		loadListView();
		addClickListener();
	}
	
	private void loadApps(){
		manager = getPackageManager();
		apps = new ArrayList<AppDetail>();
		
		//sets up Intent we are looking for
		Intent main = new Intent(Intent.ACTION_MAIN, null);
		main.addCategory(Intent.CATEGORY_LAUNCHER);
		
		//grabs all intents with launcher listed
		List<ResolveInfo> availableActivities = manager.queryIntentActivities(main,0);
		for(ResolveInfo ri : availableActivities){
			AppDetail app = new AppDetail();
			app.setLabel(ri.loadLabel(manager));
			app.setName(ri.activityInfo.packageName);
			app.setIcon(ri.activityInfo.loadIcon(manager));
			apps.add(app);
		}
	}
	
	private void loadListView(){
		list = (ListView)findViewById(R.id.apps_list);
		ArrayAdapter<AppDetail> adapter = new ArrayAdapter<AppDetail>(this,R.layout.list_item,apps){
			
			public View getView(int position, View convertView, ViewGroup parent){
				if(convertView == null){
					convertView = getLayoutInflater().inflate(R.layout.list_item, null);
				}
				
				//attaches app icon to layout view
				ImageView appIcon = (ImageView)convertView.findViewById(R.id.item_app_icon);
				appIcon.setImageDrawable(apps.get(position).getIcon());
				
				//attaches app label to layout view
				TextView appLabel = (TextView)convertView.findViewById(R.id.item_app_label);
				appLabel.setText(apps.get(position).getLabel());
				
				//attaches app name to layout view
				TextView appName = (TextView)convertView.findViewById(R.id.item_app_name);
				appName.setText(apps.get(position).getName());
				
				return convertView;
			}
		};
		list.setAdapter(adapter);
	}
	
	private void addClickListener(){
		list.setOnItemClickListener(new AdapterView.OnItemClickListener(){
			public void onItemClick(AdapterView<?> av, View v, int pos, long id){
				Intent i = manager.getLaunchIntentForPackage(apps.get(pos).getName().toString());
				AppsListActivity.this.startActivity(i);
			}
		});
	}
}
