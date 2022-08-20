package com.protalento.controllers;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import twitter4j.Trend;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.User;

/**
 * 
 * @author jose0 Singleton Class
 */
public final class TwitterController {
	private static final Logger logger = LogManager.getLogger();
	private static TwitterController twitterController;
	private Twitter twitter;
	private List<Trend> trends;

	private TwitterController() {
		twitter = new TwitterFactory().getInstance();
		logger.debug("It's been created an instance of " + this);
	}

	/**
	 * Generates a single instance of Twitter reference
	 * 
	 * @return
	 */
	public static TwitterController geTwitterController() {
		if (twitterController == null) {
			twitterController = new TwitterController();
		}

		return twitterController;
	}

	/**
	 * 
	 * @return a reference to Twitter
	 */
	public Twitter geTwitter() {
		return twitter;
	}

	public List<Trend> getTrends(int woeid) {
		try {
			trends = Arrays.asList(twitter.getPlaceTrends(woeid).getTrends()).stream()
					.filter(trend -> trend.getTweetVolume() > 0).collect(Collectors.toList());
		} catch (TwitterException e) {
			logger.error(e);
		}
		return trends;
	}
	
	public User getUser() {
		try {
			return twitter.verifyCredentials();
		} catch (TwitterException e) { 
			logger.error(e);
		}
		
		return null;
	}

	@Override
	public String toString() {
		return "TwitterController [twitter=" + twitter + "]";
	}

}
