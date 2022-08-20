package com.protalento.principal;

import twitter4j.Trend;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;

public class App {
	public static void main(String[] args) {
		// Get the twitter instance
		Twitter twitter = new TwitterFactory().getInstance();
//
		// check user credentials
		User user = null;
		try {
			user = twitter.verifyCredentials(); 
		} catch (TwitterException e) {
			e.printStackTrace();	
		}
		
		System.out.println(user.getName()); 
		System.out.println(user.getDescription());
		System.out.println(user.getURL());
		
		try {
			Trend[] trends = twitter.getPlaceTrends(23424787).getTrends();
			for (Trend trend : trends) {
				System.out.println(trend);
			}
		} catch (TwitterException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 	}
}
