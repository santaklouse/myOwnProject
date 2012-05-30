package com.vk.android_messenger;

import android.util.Log;

public final class Logger {

	private static final String TAG = "vkLogs";
	
	public static final void log(String message) {
		Log.d(TAG, message);
	}
}
