package org.mrdgo.messenger;

import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;

import javax.inject.Singleton;

import javax.ws.rs.core.MediaType;

@Singleton
@Path("myresource")
public class MyResource
{
    public MyResource() {}

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String getIt()
    {
        return "Yay.";
    }
}
