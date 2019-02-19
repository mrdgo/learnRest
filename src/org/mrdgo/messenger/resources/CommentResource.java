package org.mrdgo.messenger.resources;

import javax.ws.rs.Path;
/**
 * This doesn't need a path, because the class in instantiated within MessageResource
 * The annotation is optional, but if it is there "" or "/" is mandatory
 */
@Path("/")
public class CommentResource
{
    @GET
    public String getComments()
    {
        return "Comments.";
    }

}
