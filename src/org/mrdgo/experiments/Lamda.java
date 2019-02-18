package org.mrdgo.experiments;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Lamda
{
    public static void main(String[] args)
    {
        ExecutorService service = Executors.newFixedThreadPool(5);

        for(int i = 0; i < 10; i++)
        {
            final int index = i;
            Runnable thr = () ->  System.out.println("Thread " + index);
            service.submit(thr);
        }

        service.shutdown();
        while(!service.isShutdown()){}
        return;
        
    }
}
