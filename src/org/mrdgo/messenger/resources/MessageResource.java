package org.mrdgo.messenger.resources;

import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.POST;
import javax.ws.rs.DELETE;
import javax.ws.rs.core.MediaType;

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

    @GET
    public Collection<Message> getMessages()
    {
        log.debug("MessageResource.getMessages()");
        Collection<Message> ret = messageService.getAllMessages();
        if(ret == null) log.debug("getMessages(): Collection is null.");
        return ret;
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
}
