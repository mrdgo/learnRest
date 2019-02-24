package org.mrdgo.messenger.model;

public class ErrorMessage
{
    private String message;
    private int status;

    public ErrorMessage() {}

    public ErrorMessage(String msg, int s)
    {
        this.message = msg;
        this.status  = s;
    }
    
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    
    public int getStatus() { return status; }
    public void setStatus(int status) { this.status = status; }
}
