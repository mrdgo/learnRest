package org.mrdgo.messenger.model;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.Date;

public class Comment
{
    private int commentId;
    private String comment;
    private String author;
    private Date created;
    private static AtomicInteger id = new AtomicInteger(0);

    public Comment(){}

    public Comment(String comment, String author)
    {
        this.created   = new Date();
        this.comment   = comment;
        this.author    = author;
        this.commentId = id.addAndGet(1);
    }

    public void setCommentId() { commentId = id.addAndGet(1); }

    public int getCommentId() { return commentId; }
    public void setCommentId(int commentId) { this.commentId = commentId; }

    public String getComment() { return comment; }
    public void setComment(String comment) { this.comment = comment; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public Date getCreated() { return created; }
    public void setCreated(Date created) { this.created = created; }
}
