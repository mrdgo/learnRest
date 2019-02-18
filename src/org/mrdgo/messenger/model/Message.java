package org.mrdgo.messenger.model;

import java.util.Date;

public class Message
{
    private long id;
    private Date created;
    private String message;
    private String author;

    public Message(){}

    public Message(String message, String author)
    {
        this.id = 0L;
        this.message = message;
        this.author = author;
        this.created = new Date();
    }

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public Date getCreated() { return this.created; }
    public void setCreated(Date created) { this.created = created; }

    public String getMessage() { return this.message; }
    public void setMessage(String message) { this.message = message; }

    public String getAuthor() { return this.author; }
    public void setAuthor(String author) { this.author = author; }
}

