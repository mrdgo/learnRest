package org.mrdgo.messenger.resources;

import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;

import java.util.Collection;

import org.apache.log4j.Logger;

import org.mrdgo.messenger.service.CommentService;
import org.mrdgo.messenger.model.Message;
import org.mrdgo.messenger.model.Comment;
import org.mrdgo.messenger.database.Database;
/**
 * This doesn't need a path, because the class in instantiated within MessageResource
 * The annotation is optional, but if it is there "" or "/" is mandatory
 */
@Path("/")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CommentResource
{
    private static Logger log = Logger.getLogger(CommentResource.class);
    private CommentService commentService = Database.commentService;

    @GET
    public Collection<Comment> getComments(@PathParam("messageId") int messageId)
    {
        log.debug("JAX-RS: getComments("+messageId+")");
        return commentService.getComments(messageId);
    }

    /**
     * Access @PathParam from parent resource
     */
    @GET
    @Path("{commentId}")
    public String getComment(@PathParam("messageId") int messageId,
                             @PathParam("commentId") int commentId)
    {
        return "Message" + messageId + " Comment" + commentId;
    }

    @POST
    public Comment postComment(@PathParam("messageId") int messageId, Comment comment)
    {
        return commentService.postComment(messageId, comment);
    }

}
