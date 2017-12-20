package io.johnsell620.jMessage.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import io.johnsell620.jMessage.model.ErrorMessage;

@Provider
public class DataNotFoundExceptionMapper implements ExceptionMapper<DataNotFoundException> {

	@Override
	public Response toResponse(DataNotFoundException ex) {
		ErrorMessage errorMessage = new ErrorMessage(ex.getMessage(), 404, "documentation");
		// TODO Auto-generated method stub
		return Response.status(Status.NOT_FOUND)
				.entity(errorMessage)
				.build();
	}
	
}
