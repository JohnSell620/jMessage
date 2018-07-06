package io.johnsell620.jMessage.model;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
//import com.fasterxml.jackson.databind.SerializationConfig;
//import com.fasterxml.jackson.databind.SerializationFeature;
/**
 * 
 * @author johnny
 *
 */
public class CustomCreatedDate extends JsonDeserializer<Date> {
//	public CustomObjectDate() {
//        configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);            
////        setDateFormat(new ISO8601DateFormat());
//    }
	private SimpleDateFormat dateFormat = new SimpleDateFormat(
            "yyyy-MM-dd HH:mm:ss");

    @Override
    public Date deserialize(JsonParser paramJsonParser,
            DeserializationContext paramDeserializationContext)
            throws IOException, JsonProcessingException {
        String str = paramJsonParser.getText().trim();
        try {
            return dateFormat.parse(str);
        } catch (ParseException e) {
            // Handle exception here
        }
        return paramDeserializationContext.parseDate(str);
    }
}
