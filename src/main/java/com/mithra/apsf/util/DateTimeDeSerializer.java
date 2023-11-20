package com.mithra.apsf.util;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.format.datetime.DateFormatter;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdScalarDeserializer;

public class DateTimeDeSerializer extends StdScalarDeserializer<Date> 
{
	
	/**
	 * Reference to the logger
	 */
	private static final Logger logger = LoggerFactory.getLogger(DateTimeDeSerializer.class);
	
	/**
	 * serialVersionUID
	 */
	private static final long serialVersionUID = 1L;

	private DateFormatter dateFormatter =new DateFormatter("yyyy-MM-dd HH:mm:ss.SSSXXX");

	private Locale locale=new Locale("en");
	
	protected DateTimeDeSerializer() {
		super(Date.class);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public Date deserialize(JsonParser jp, DeserializationContext ctxt)
			throws IOException, JsonProcessingException {
	
		logger.debug("Entering deserialize");
		
		//dateFormatter.setTimeZone(DateTimeZone.UTC.toTimeZone());
		
        JsonToken currentToken = jp.getCurrentToken();
        if (currentToken == JsonToken.VALUE_STRING) 
        {
            String dateTimeAsString = jp.getText().trim();
            try 
            {
            	return dateFormatter.parse(dateTimeAsString, locale);
            }
            catch(ParseException e)
    		{
    			e.printStackTrace();
    			DateTimeDeSerializer.logger.error( "Exception while parsing Date : " + e );
    			throw ctxt.mappingException(getValueClass());
    		}
        }
        else
        {
        	throw ctxt.mappingException(getValueClass()); 
        }
        
       
       
    }
}


