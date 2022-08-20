package com.protalento.services;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.protalento.controllers.TwitterController;
import com.protalento.entities.Log;
import com.protalento.jdbc.implementations.LogImplementation;

import twitter4j.Trend;
import twitter4j.User;

@Path(value = "/twitter")
@Produces(value = MediaType.APPLICATION_JSON)
public class TwitterService {
	private static final Logger logger = LogManager.getLogger();
	private TwitterController twitterController;

	public TwitterService() {
		twitterController = TwitterController.geTwitterController();
	}

	@GET
	@Path("/ping")
	public Response ping() { 
		String message = "pong desde " + System.getenv("ENVIRONMENT");
		logger.info(message);
		return Response.ok(message).build();
	}

	@GET
	@Path("/trends/{woeid}")
	public Response getTrends(@PathParam(value = "woeid") int woeid) {
		List<Trend> trends = twitterController.getTrends(woeid);
		logger.info(trends);
		
		Log log = new Log((long)woeid, trends.toString());
		LogImplementation logImplementation = new LogImplementation();
		logImplementation.save(log);
		return Response.ok(trends).build();
	}
	
	@GET
	@Path("/trends/{woeid}/{order}")
	public Response getTrends(@PathParam(value = "woeid") int woeid, @PathParam(value = "order") String order) {
		List<Trend> trends = twitterController.getTrends(woeid);
		if (order != null && order.equalsIgnoreCase("desc")) {
			trends.sort((trend1, trend2) -> trend1.getTweetVolume() - trend2.getTweetVolume() );
		}else {
			trends.sort((trend1, trend2) -> trend2.getTweetVolume() - trend1.getTweetVolume() );
		}
		logger.info(trends);
		return Response.ok(trends).build();
	}
	
	@GET
	@Path(value = "/user")
	public Response getUser() {
		User user = TwitterController.geTwitterController().getUser();
		
		return Response.ok(user).build();
	}

}
