package com.vk.android_messenger;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class VK_MessengerActivity extends Activity {
	
	public final static Model model = new Model();
	
	public final static String modelId = "model";
	public final static Integer intentFor = 1;
	
	TextView textView;
	Button logOutButton;
	
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        Console.log("Start main Activity!!!");
        Console.log("model.access_token: " + model.access_token);
        if (model.access_token == null) {

        	//if we not logged in start Login Activity
        	Intent intent = new Intent(this, Login.class);
        	//put model to actividy
        	intent.putExtra(modelId, model);
        	//we wait for result
        	startActivityForResult(intent, intentFor);
        	return;
        }
        
        setContentView(R.layout.main);

        Console.log("model.access_token: " + model.access_token);
        
        logOutButton = (Button) findViewById(R.id.logOutButton);
        //set logged in text 
        textView = (TextView) findViewById(R.id.LargeTestTextView);
        textView.setText("You logged in!");
        textView = (TextView) findViewById(R.id.testTextView);
        textView.setText("Your access_token: " + model.access_token);
        
        Console.log("End main Activity!!!");

        //set onClick Listener (Logout button)
    	OnClickListener logOutClick = new OnClickListener() {
    		@Override
    		public void onClick(View v) {
    			model.access_token = null;
            	finish();
    			
	        }
    	};
    	logOutButton.setOnClickListener(logOutClick);
    }
    
    // Result from activity
    @Override 
    public void onActivityResult(int requestCode, int resultCode, Intent data) {     
      super.onActivityResult(requestCode, resultCode, data);

      if (requestCode == intentFor) {
    	  if (data != null && data.getStringExtra(modelId) != "" && data.getStringExtra(modelId) != null) {
    		  model.access_token = data.getStringExtra(modelId);
    		  this.onCreate(null);
    	  } else {
    		  finish();
    	  }
      }
    }
    
    @Override
    public void onPause() {
    	super.onPause();
    	Console.log("pause");
    }
    
    @Override
    public void onDestroy() {
    	super.onDestroy();
    	Console.log("destroyed");
    }
    
    
}