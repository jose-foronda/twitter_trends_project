package com.protalento.jdbc.implementations;

import com.octaviorobleto.sql.jdbc.entities.GenericImplementation;
import com.protalento.entities.Log;
import com.protalento.jdbc.ConnectionManager;

public class LogImplementation extends GenericImplementation<Log, Object> {

	public LogImplementation() {
		super(Log.class, ConnectionManager.getConnectionManager().getConnection()); 
	}

}
