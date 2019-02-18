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

import org.mrdgo.messenger.service.ProfileService;
import org.mrdgo.messenger.model.Profile;
import org.mrdgo.messenger.database.Database;

@Path("profiles")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ProfileResource
{
    private static Logger log = Logger.getLogger(ProfileResource.class);
    private ProfileService profileService = Database.profileService;

    public ProfileResource(){}

    @GET
    public Collection<Profile> getProfiles()
    {
        Collection<Profile> ret = profileService.getAllProfiles();
        return ret;
    }

    @POST
    public Profile addProfile(Profile profile)
    {
        return profileService.postProfile(profile);
    }

    @PUT
    @Path("{profileId}")
    public Profile putProfile(@PathParam("profileId") String id, Profile profile)
    {
        profile.setProfileName(id);
        return profileService.putProfile(profile);
    }

    @DELETE
    @Path("{profileId}")
    public Profile deleteProfile(@PathParam("profileId") String id)
    {
        return profileService.deleteProfile(id);
    }

    @GET
    @Path("{profileId}")
    public Profile getProfile(@PathParam("profileId") String profileId)
    {
        return profileService.getProfile(profileId);
    }
}
