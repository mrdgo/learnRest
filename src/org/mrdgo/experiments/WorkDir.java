package org.mrdgo.experiments;

public class WorkDir
{
    public static void main(String[] args)
    {
        System.out.println("Current:    " + System.getProperty("user.dir"));
        System.out.println("Users home: " + System.getProperty("user.home"));
        System.out.println("Temporary:  " + System.getProperty("java.io.tmpdir"));
        System.out.println("OS:         " + System.getProperty("os.name"));
    }
}
