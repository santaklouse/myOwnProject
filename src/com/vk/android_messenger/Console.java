package com.vk.android_messenger;

import android.util.Log;

public final class Console {


	private static final String TAG = "vkLogs";


	public static final void log(String message) {
		Log.d(TAG, message);
	}
	
	public static final void log(int message) {
		Log.d(TAG, String.valueOf(message));
	}
	
	public static final void log(char message) {
		Log.d(TAG, String.valueOf(message));
	}
	
	public static final void log(char[] message) {
		Log.d(TAG, String.valueOf(message));
	}
	
	public static final void log(Object message) {
		Log.d(TAG, String.valueOf(message));
	}
	
	public static final void log(boolean message) {
		Log.d(TAG, String.valueOf(message));
	}
	
	public static final void log(float message) {
		Log.d(TAG, String.valueOf(message));
	}

}
