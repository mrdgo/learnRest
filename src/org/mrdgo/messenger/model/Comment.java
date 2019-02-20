package org.mrdgo.messenger.model;

import java.util.Date;

public class Comment
{
    private long commentId;
    private String comment;
    private String author;
    private Date created;

    public Comment(long id)
    {
        this.created   = new Date();
        this.comment   = "";
        this.author    = "";
        this.commentId = id;
    }

    public long getCommentId() { return commentId; }
    public void setCommentId(long commentId) { this.commentId = commentId; }

    public String getComment() { return comment; }
    public void setComment(String comment) { this.comment = comment; }

    public String getAuthor() { return author; }
    public void setAuthor(String author) { this.author = author; }

    public Date getCreated() { return created; }
    public void setCreated(Date created) { this.created = created; }
}
