package org.mrdgo.messenger.exception;

public class DataNotFoundException extends RuntimeException
{
    private static final long serialVersionUID = -23412378329243587L;

    public DataNotFoundException(String msg)
    {
        super(msg);
    }
}
