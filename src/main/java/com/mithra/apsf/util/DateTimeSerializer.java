package com.mithra.apsf.util;

import java.io.IOException;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdScalarSerializer;


public class DateTimeSerializer  extends StdScalarSerializer<Date>{

	/**
	 * Reference to the logger
	 */
	private static final Logger logger = LoggerFactory.getLogger(DateTimeSerializer.class);
	
	protected DateTimeSerializer() {
		super(Date.class);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void serialize(Date value, JsonGenerator jgen,
			SerializerProvider provider) throws IOException,
			JsonProcessingException {
		logger.debug("Entering serialize");
		
		jgen.writeString(value.toString());
		
		logger.debug("Leaving Serialize");
		
	}

}
