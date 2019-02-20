package org.mrdgo.messenger.model;

import java.util.Date;

public class Profile
{
    private Date created;
    private String profileName;
    private String firstName;
    private String lastName;

    public Profile(){}

    public Profile(String profileName, String firstName, String lastName)
    {
        this.profileName = profileName;
        this.lastName    = lastName;
        this.firstName   = firstName;
        this.created     = new Date();
    }

    public String getFirstName() { return firstName; }
    public void setFirstName(String firstName) { this.firstName = firstName; }

    public Date getCreated() { return this.created; }
    public void setCreated(Date created) { this.created = created; }

    public String getProfileName() { return this.profileName; }
    public void setProfileName(String profileName) { this.profileName = profileName; }

    public String getLastName() { return this.lastName; }
    public void setLastName(String lastName) { this.lastName = lastName; }
}

