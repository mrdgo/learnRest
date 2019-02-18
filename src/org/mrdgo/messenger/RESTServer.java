package org.mrdgo.messenger;

import org.mrdgo.messenger.resources.*;
import org.mrdgo.messenger.service.*;

import javax.ws.rs.core.UriBuilder;

import java.net.URI;
import java.net.InetAddress;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;

public class RESTServer 
{
    public static void main(String[] args)
    {
        try{
            URI myUri = UriBuilder.fromUri("http://0.0.0.0").port(8080).build();
			ResourceConfig config = new ResourceConfig(MessageResource.class);
            // add more resources by
            // -> config.register(xxx.class);
            config.register(ProfileResource.class);

            GrizzlyHttpServerFactory.createHttpServer(myUri, config);
        } catch (Exception e)
        {
            e.printStackTrace();
        }

    }
}
