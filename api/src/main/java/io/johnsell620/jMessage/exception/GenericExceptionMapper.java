package io.johnsell620.jMessage.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
//import javax.ws.rs.ext.Provider;

import io.johnsell620.jMessage.model.ErrorMessage;
/**
 * 
 * @author johnny
 * There is a hierarchy so that the DataNotFoundException takes precedence over
 * this generic one.
 */
//@Provider
public class GenericExceptionMapper implements ExceptionMapper<Throwable> {

	@Override
	public Response toResponse(Throwable ex) {
		ErrorMessage errorMessage = new ErrorMessage(ex.getMessage(), 500, "documentation");
		// TODO Auto-generated method stub
		return Response.status(Status.INTERNAL_SERVER_ERROR)
				.entity(errorMessage)
				.build();
	}

}
