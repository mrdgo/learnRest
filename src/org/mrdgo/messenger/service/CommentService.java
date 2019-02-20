package org.mrdgo.messenger.service;

import org.apache.log4j.Logger;

import org.mrdgo.messenger.model.Comment;
import org.mrdgo.messenger.model.Message;

import java.util.SortedSet;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;

public class CommentService
{
    private static Logger log = Logger.getLogger(MessageService.class);

    // maps message id to messages' comments
    private ConcurrentMap<Long, SortedSet<Comment>> comments;

    public CommentService()
    {
        this.comments = new ConcurrentHashMap<>();
    }

    public SortedSet<Comment> getComments(long messageId)
    {
        return comments.get(messageId);
    }

    public Comment getComment(long messageId, long commentId)
    {
        initComments(messageId); // make sure, the collection is present
        SortedSet<Comment> commentList = getComments(messageId);
        return null; // Comment not found
    }

    public void initComments(long messageId)
    {
        if(comments.get(messageId) != null) return; // already initialised
        comments.put(messageId, new ConcurrentSkipListSet<Comment>());
    }

}
