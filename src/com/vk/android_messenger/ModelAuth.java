package com.vk.android_messenger;

public class ModelAuth extends Model {

	public String login(String login, String pass) {

		Console.log("ModelAuth login started...");

		this.set("grant_type", "password");
		this.set("client_id", this.client_id);
		this.set("client_secret", this.client_secret);

		this.set("username", login); // user phone or mail
		this.set("password", pass); // user password
		
		try {
			this.execute();
		} catch (Exception e) {
			return null;
		}
		return this.access_token;
	}
	
}
