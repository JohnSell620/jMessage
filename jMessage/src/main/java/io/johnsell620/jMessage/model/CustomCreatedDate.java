package io.johnsell620.jMessage.model;

import com.fasterxml.jackson.databind.SerializationConfig;
import com.fasterxml.jackson.databind.SerializationFeature;

public class CustomCreatedDate extends Message{
	public CustomObjectDate() {
        configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);            
//        setDateFormat(new ISO8601DateFormat());
    }
}
