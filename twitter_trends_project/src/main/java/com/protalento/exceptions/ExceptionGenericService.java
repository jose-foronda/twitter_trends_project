package com.protalento.exceptions;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ExceptionGenericService implements ExceptionMapper<Throwable> {
	private static final Logger logger = LogManager.getLogger();
	
	@Override
	public Response toResponse(Throwable exception) {
		logger.error(exception);
		return Response.serverError().type(MediaType.TEXT_HTML)
				.entity("<h1>Error service... please try it later.</h1> <br> <p>" + exception.getMessage() + " </p>")
				.build();
	}

}
