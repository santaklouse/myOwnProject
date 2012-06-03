package com.vk.android_messenger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.os.Parcel;
import android.os.Parcelable;

public class Model implements Parcelable {
	
	
	///for HTTP connections
	public class HttpKlass {

		public String executeHttpGet(String URI) throws Exception {

			BufferedReader in = null;

			try {
				
				//setting connection timeout
				int timeoutConnection = 3000;
				HttpParams httpParameters = new BasicHttpParams();
				HttpConnectionParams.setConnectionTimeout(httpParameters, timeoutConnection);
				int timeoutSocket = 5000;
				HttpConnectionParams.setSoTimeout(httpParameters, timeoutSocket);
				
				HttpClient client = new DefaultHttpClient(httpParameters);

				HttpGet request = new HttpGet();
				request.setURI(new URI(URI));
				
				//execure request
				HttpResponse response = client.execute(request);
				in = new BufferedReader(new InputStreamReader(response
						.getEntity().getContent()));
				StringBuffer sb = new StringBuffer("");
				String line = "";
				String NL = System.getProperty("line.separator");
				while ((line = in.readLine()) != null) {
					sb.append(line + NL);
				}
				in.close();
				String page = sb.toString();
				return page;
			} finally {
				if (in != null) {
					try {
						in.close();
					} catch (IOException e) {
						Console.log("Http_klass executeHttpGet exception: "
								+ e.getMessage().toString());
					}
				}
			}
		}
		
	}

	/* Model code start*/
	
	public String access_token = null;

	protected final String client_id = "2971269";
	protected final String client_secret = "sqlQ4OSnIdoo2j9NnKiw";


	//API url
	private final static String apiUrl = "https://api.vk.com/oauth/token?";
	
	private String temporaryApiUrl = apiUrl;

	//request params array
	private Map <String, String> requestParams = new HashMap <String, String>();

	//response data array
	private Map <String, String> data = new HashMap <String, String>();

	//errors array
	private Map <String, String> errors = new HashMap <String, String>();


//	public Model() {
//		Logger.log("model constructor");
//		//take out to child classes
////		this.set("grant_type", "password");
////		this.set("client_id", this.client_id);
////		this.set("client_secret", this.client_secret);
////
////		this.set("username", "user12354@mail.ru"); // user phone or mail
////		this.set("password", "user123456"); // user password
//
//	}

	public Boolean execute() throws Exception {
		return this.execRequest();
	}
	
	private Boolean execRequest() throws Exception {
		
		
		this.beforeRequest();
		this.clearURL();
		//setting params for request to server
		this.setGetParams();
		
		
		if ( this.requestParams.isEmpty()) {
			return false;
		}
		else {
			this.clearParams();
		}

		Console.log("execute request to: " + this.temporaryApiUrl);
		HttpKlass test = new HttpKlass();
		String responseString = "";
		try {
			responseString = test.executeHttpGet(this.temporaryApiUrl);
		} catch (Exception e) {
			this.addError("", e.getMessage().toString());

			Console.log("exec_request() exception: " + e.getMessage().toString()
					+ " " + e.toString());
		}
		if ( ! this.allErrors().isEmpty()) {
			return false;
		}
		this.clearData();
		this.parseResponse(responseString);
		this.afterRequest();
		return true;
	}
	
	protected void beforeRequest() {

	}
	
	public void clearParams() {
		this.requestParams.clear();
	}
	
	private void clearURL() {
		this.temporaryApiUrl = apiUrl;
	}
	
	public void clearData() {
		this.data.clear();
	}
	
	private void parseResponse(String responseString) {
		Console.log("start parseResponse");
		if (responseString != null) {
			try {
				JSONObject entries = new JSONObject(responseString);
				int i;
				JSONArray names = (JSONArray) entries.names();
				for (i = 0; i < entries.length(); i++) {
					String name = names.getString(i);
					this.data.put(name, entries.getString(name));
					Console.log("name: " + name + " value: "
							+ entries.getString(name));
				}
				if (!entries.isNull("access_token")) {
					this.access_token = entries.getString("access_token");
				}

			} catch (JSONException e) {
				Console.log("Error in response: " + e.getMessage().toString()
						+ "  " + e.toString());
			}
		}
	}
	
	protected void afterRequest() {

	}

	protected void setGetParams() {
		
		Console.log("setGetParams start");
		for (Map.Entry<String, String> entry: this.requestParams.entrySet()) {
			this.temporaryApiUrl += "&" + entry.getKey() + "=" + entry.getValue() + "&";
		}

		this.temporaryApiUrl = String.copyValueOf(this.temporaryApiUrl.toCharArray(), 0, this.temporaryApiUrl.length() - 1);
	}

	public void set(String key, String value) {
		if (this.requestParams.containsKey(key)) {
			this.requestParams.remove(key);
		}
		this.requestParams.put(key, value);
	}
	
	public String get(String key) {
		if (this.data.containsKey(key)) {
			return this.data.get(key);
		}
		return null;
	}
	
	public Map<String, String> asHash() {
		return this.data;
	}
	
	public final void addError(String name, String message) {
		this.errors.put(name, message);
	}

	public final Map<String, String> allErrors() {
		return this.errors;
	}

	
	// Parcel code start
	// Now We Can put to Extra (for activity) this Object (Model)
	
	@Override
	public int describeContents() {
		return 0;
	}

	@SuppressWarnings("rawtypes")
	public static final Parcelable.Creator CREATOR = new Parcelable.Creator() {

		@Override
		public Model createFromParcel(Parcel parcel) {
			Model model = new Model();
			model.access_token = parcel.readString();
			// TODO Auto-generated method stub
			return model;
		}

		@Override
		public Model[] newArray(int size) {
			return new Model[size];
		}
		
	};
	
	@Override
	public void writeToParcel(Parcel parcel, int i) {
		// TODO Auto-generated method stub
		parcel.writeString(this.access_token);
	}
	
}
