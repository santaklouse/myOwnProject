package com.vk.android_messenger;

import android.app.Activity;
import android.os.Bundle;

public class VK_MessengerActivity extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        Model http_get = new Model();
        Logger.log(http_get.access_token);
    }
}