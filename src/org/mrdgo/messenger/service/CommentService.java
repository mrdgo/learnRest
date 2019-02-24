package org.mrdgo.messenger.service;

import org.apache.log4j.Logger;

import org.mrdgo.messenger.model.Comment;
import org.mrdgo.messenger.model.Message;

import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListSet;

import java.util.stream.Collectors;
import java.util.Arrays;
import java.util.Set;
import java.util.Collection;

public class CommentService
{
    private static Logger log = Logger.getLogger(CommentService.class);

    // maps message id to messages' comments
    private ConcurrentMap<Integer, ConcurrentSkipListSet<Comment>> comments;

    public CommentService()
    {
        this.comments = new ConcurrentHashMap<>();
        postComment(1, new Comment("Wow klasse!", "Anonym"));
    }

    public Collection<Comment> getComments(int messageId)
    {
        ConcurrentSkipListSet<Comment> commentSet = comments.get(messageId);
        if(commentSet == null) return null;
        return (Collection<Comment>) commentSet;
    }

    public Comment[] getCommentsPaginated(int messageId, int from, int size)
    {
        if(from < 0 || size < 0 ) return null;
        ConcurrentSkipListSet<Comment> commentSet = comments.get(messageId);

        if(commentSet == null || commentSet.size() <= from) return null;
        return (Comment[]) Arrays.copyOfRange((Object[]) commentSet.toArray(), from, from+size);
    }

    public Comment getComment(int messageId, int commentId)
    {
        if(comments.get(messageId) == null) initComments(messageId); // make sure, the collection is present
        return comments.get(messageId).stream().filter(c -> c.getCommentId() == commentId).findFirst().orElse(null);
    }

    private void initComments(int messageId)
    {
        // This Lambda compares two comments by their date of creation
        comments.put(messageId, new ConcurrentSkipListSet<Comment>( (c1, c2) -> (c1.getCreated().compareTo(c2.getCreated())) ));
    }

    public Comment postComment(int messageId, Comment comment)
    {
        if(comments.get(messageId) == null) initComments(messageId);
        comment.setCommentId();
        comments.get(messageId).add(comment);
        return getComment(messageId, comment.getCommentId());
    }

}
