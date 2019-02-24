package org.mrdgo.messenger.exception;

import org.apache.log4j.Logger;

import org.mrdgo.messenger.model.ErrorMessage;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class DataNotFoundExceptionMapper implements ExceptionMapper<DataNotFoundException>
{
    private static Logger log = Logger.getLogger(DataNotFoundException.class);

    @Override
    public Response toResponse(DataNotFoundException exp)
    {
        log.debug("In Mapper, converting Exception to response.");
        return Response.status(Response.Status.NOT_FOUND)
                       .entity(new ErrorMessage(exp.getMessage(), 404))
                       .type(MediaType.APPLICATION_JSON)
                       .build();
    }
}
