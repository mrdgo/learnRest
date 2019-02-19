package org.mrdgo.messenger.resources;

import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.Context;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.POST;
import javax.ws.rs.DELETE;
import javax.ws.rs.core.MediaType;

import javax.ws.rs.core.UriInfo;
import javax.ws.rs.core.HttpHeaders;

import java.util.Collection;

import org.apache.log4j.Logger;

import org.mrdgo.messenger.service.MessageService;
import org.mrdgo.messenger.model.Message;
import org.mrdgo.messenger.database.Database;

@Path("messages")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class MessageResource
{
    private static Logger log = Logger.getLogger(MessageResource.class);
    private MessageService messageService = Database.messageService;

    public MessageResource(){}

    /**
     * Accessing parameters:
     * 1.
     *
     * @MatrixParam("param") if URI is: .../messages;param=value
     * @QueryParam("param")  if URI is: .../messages?param=value
     * @HeaderParam("param") if in Header, a parameter is specified
     * @CookieParam("cookieName") for cookies
     * @FormParam("param") for HTML Form parameters
     *
     * 2. @Context
     *
     * Get Parameters in Collection mode.
     * Example below comment..
     *
     * 3. @BeanParam, well this needs a class to be defined.
     */
    @GET
    @Path("context")
    public String context(@Context UriInfo uriInfo, @Context HttpHeaders headers)
    {
        // Some handy examples
        String path = uriInfo.getAbsolutePath().toString();
        String cookies = headers.getCookies().toString();
        return "Path: " + path + "\nCookies: " + cookies;
    }

    @GET
    public Collection<Message> getMessages(@QueryParam("year") int year,
                                           @DefaultValue("-1") @QueryParam("start") int start, 
                                           @QueryParam("size") int size)
    {
        if(year > 0)                
        {
                log.debug("GET messages by year");
                return messageService.getMessagesByYear(year);
        }
        else if(start >= 0 && size >= 0) {
                log.debug("GET messages paginated");
                return messageService.getMessagesPaginated(start, size);
        }
        else {
                log.debug("GET all messages");
                return messageService.getAllMessages();
        }
    }

    @POST
    public Message addMessage(Message message)
    {
        return messageService.postMessage(message);
    }

    @PUT
    @Path("{messageId}")
    public Message putMessage(@PathParam("messageId") long id, Message message)
    {
        message.setId(id);
        return messageService.putMessage(message);
    }

    @DELETE
    @Path("{messageId}")
    public Message deleteMessage(@PathParam("messageId") long id)
    {
        return messageService.deleteMessage(id);
    }

    @GET
    @Path("{messageId}")
    public Message getMessage(@PathParam("messageId") long messageId)
    {
        return messageService.getMessage(messageId);
    }

    /**
     * Delegate subpath "comments"
     */
    @Path("{messageId}/comments")
    public CommentResource getCommRsrc()
    {
        return new CommentResource();
    }
}
