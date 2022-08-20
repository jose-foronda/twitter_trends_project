package com.protalento.settings;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;

@ApplicationPath(value = "/services")
public class ResourceConfiguration extends ResourceConfig{
	public ResourceConfiguration() {
		packages("com.protalento");
	}
}
