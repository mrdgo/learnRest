package org.mrdgo.messenger.model;

import java.util.Date;
import java.util.Collection;
                
import javax.xml.bind.annotation.XmlTransient;

import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

public class Message
{
    private long id;
    private Date created;
    private String message;
    private String author;
    private ConcurrentMap<Long, Comment> comments;
    private AtomicLong currCommentIdx;

    public Message(){}

    public Message(String message, String author)
    {
        this.id = 0L;
        this.message = message;
        this.author = author;
        this.created = new Date();
        this.currCommentIdx = new AtomicLong(0);
        this.comments = new ConcurrentHashMap<>();
    }

    public long getId() { return id; }
    public void setId(long id) { this.id = id; }

    public Date getCreated() { return this.created; }
    public void setCreated(Date created) { this.created = created; }

    public String getMessage() { return this.message; }
    public void setMessage(String message) { this.message = message; }

    public String getAuthor() { return this.author; }
    public void setAuthor(String author) { this.author = author; }

    @XmlTransient
    public Collection<Comment> getComments() { return comments.values(); }
    public void setComments(ConcurrentMap<Long, Comment> comments) { this.comments = comments; }
    
    @XmlTransient
    public AtomicLong getCurrCommentIdx() { return currCommentIdx; }
    public void setCurrCommentIdx(AtomicLong currCommentIdx) { this.currCommentIdx = currCommentIdx; }
}

