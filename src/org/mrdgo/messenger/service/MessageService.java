package org.mrdgo.messenger.service;

import org.mrdgo.messenger.model.Message;
import org.apache.log4j.Logger;

import java.util.Collection;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.ConcurrentHashMap;

public class MessageService
{
    private static Logger log = Logger.getLogger(MessageService.class);
    private ConcurrentMap<Long, Message> messages;
    private AtomicLong msgCount;

    public MessageService()
    {
        this.messages = new ConcurrentHashMap<>();
        this.msgCount = new AtomicLong(0L);
    }

    public Collection<Message> getAllMessages()
    {
        log.debug("Has currently " + messages.size() + " messages.");
        return messages.values();
    }

    public Message getMessage(long id)
    {
        return messages.get(id);
    }

    public Message deleteMessage(long id)
    {
        return messages.remove(id);
    }

    public Message postMessage(Message message)
    {
        long id = msgCount.addAndGet(1L);
        message.setId(id);
        Message msg = messages.put(id, message);
        if(msg == null) log.debug("POST: successfully added new Message.\n" + message.getMessage());
        return messages.get(id);
    }

    public Message putMessage(Message message)
    {
        messages.put(message.getId(), message);
        return messages.get(message.getId());
    }
}
