package org.mrdgo.messenger.service;

import org.mrdgo.messenger.model.Message;
import org.mrdgo.messenger.exception.DataNotFoundException;

import org.apache.log4j.Logger;

import java.util.Calendar;
import java.util.Collection;
import java.util.HashSet;

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
        postMessage(new Message("Hallo Welt", "Maxim"));
    }

    public Collection<Message> getAllMessages()
    {
        log.debug("Has currently " + messages.size() + " messages.");
        return messages.values();
    }

    public Collection<Message> getMessagesByYear(int year)
    {
        Collection<Message> ret = new HashSet<Message>();
        Calendar cal = Calendar.getInstance();
        for(Message msg : messages.values())
        {
            cal.setTime(msg.getCreated());
            if(cal.get(Calendar.YEAR) == year)
            {
                ret.add(msg);
            }
        }
        return ret;
    }

    public Collection<Message> getMessagesPaginated(int start, int size)
    {
        Collection<Message> ret = new HashSet<Message>();
        int index = 0;
        for(Message msg : messages.values())
        {
            if(index >= start && index < start+size) ret.add(msg);
            index++;
        }
        return ret;
    }

    public Message getMessage(long id) throws DataNotFoundException
    {
        Message mes = messages.get(id);
        if(mes == null) throw new DataNotFoundException("Message with id \"" + id + "\" not found");
        return mes;
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
        else log.debug("Failed to add Message");
        return messages.get(id);
    }

    public Message putMessage(Message message)
    {
        messages.put(message.getId(), message);
        return messages.get(message.getId());
    }
}
