package org.mrdgo.messenger.service;

import org.mrdgo.messenger.model.Profile;
import org.apache.log4j.Logger;

import java.util.Collection;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentHashMap;

public class ProfileService
{
    private static Logger log = Logger.getLogger(ProfileService.class);
    private ConcurrentMap<String, Profile> profiles;

    public ProfileService()
    {
        this.profiles = new ConcurrentHashMap<>();
        profiles.put("maxim", new Profile("maxim", "Maxim", "Ritter"));
    }

    public Collection<Profile> getAllProfiles()
    {
        return profiles.values();
    }

    public Profile getProfile(String profileName)
    {
        return profiles.get(profileName);
    }

    public Profile deleteProfile(String profileName)
    {
        return profiles.remove(profileName);
    }

    public Profile postProfile(Profile profile)
    {
        if(profile.getProfileName().isEmpty() || profiles.get(profile.getProfileName()) != null) return null;
        Profile msg = profiles.put(profile.getProfileName(), profile);
        return profiles.get(profile.getProfileName());
    }

    public Profile putProfile(Profile profile)
    {
        if(profile.getProfileName().isEmpty() || profiles.get(profile.getProfileName()) == null) return null;
        profiles.put(profile.getProfileName(), profile);
        return profiles.get(profile.getProfileName());
    }
}
