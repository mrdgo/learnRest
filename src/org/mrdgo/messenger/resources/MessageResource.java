package org.mrdgo.messenger.resources;

import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.BeanParam;
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
import org.mrdgo.messenger.resources.beans.MessageFilterBean;

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
     * Example:
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
    /**
     * 3. @BeanParam
     *
     * See beans/MessageFilterBean.java for reference
     * Example:
     */

    @GET
    public Collection<Message> getMessages(@BeanParam MessageFilterBean msg)
    {
        if(msg.getYear() > 0) return messageService.getMessagesByYear(msg.getYear());

        if(msg.getStart() >= 0 && msg.getSize() >= 0) return messageService.getMessagesPaginated(msg.getStart(), msg.getSize());
 
        return messageService.getAllMessages();
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
