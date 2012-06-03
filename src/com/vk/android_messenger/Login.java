package com.vk.android_messenger;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

public class Login extends Activity {
	
    EditText userPhone;
    EditText userPass;
    Button loginButton;
    ProgressBar progress;
    
    private Intent intent = null; 

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        
        //getting Intent
        intent = getIntent();
        //get extras (we send model to here)
        Bundle bundle = intent.getExtras();
		final Model model = bundle.getParcelable(VK_MessengerActivity.modelId);
        
    	userPhone = (EditText) findViewById(R.id.userPhone);
    	userPass = (EditText) findViewById(R.id.userPass);
    	loginButton = (Button) findViewById(R.id.loginButton);
    	progress = (ProgressBar) findViewById(R.id.loginProgress);
    	
    	progress.setVisibility(ProgressBar.INVISIBLE);
    	
    	//set login button listener
    	OnClickListener loginClick = new OnClickListener() {
    		@Override
    		public void onClick(View v) {
    	    	String login = userPhone.getText().toString();
    	    	String pass = userPass.getText().toString();
    	    	
    	    	//processing input data & check for correct
    	    	if (login.length() == 0 || pass.length() == 0) {
    	    		return;
    	    	}

    	    	progress.setVisibility(ProgressBar.VISIBLE);
    	    	
    	    	ModelAuth auth = new ModelAuth();
    	    	model.access_token = auth.login(login, pass);
    	    	if (model.access_token != null) {
    	    		//if OK set Extra params & finish this activity
    	    		Console.log("User is logged in...");
    	    		intent.putExtra(VK_MessengerActivity.modelId, model.access_token);
    	    		setResult(Activity.RESULT_OK, intent);
    	    		finish();
    	    		return;
    	    	}
    	    	progress.setVisibility(ProgressBar.INVISIBLE);
    	    	Console.log("Wrong password or login!");
	        }
    	};
    	loginButton.setOnClickListener(loginClick);
    }
    
    @Override
    public void onDestroy() {
    	super.onDestroy();
    	finish();
    }
}
