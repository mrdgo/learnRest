package org.mrdgo.experiments;

import java.util.Optional;

public class OptionalStuff
{
    private static Optional<String> name = Optional.ofNullable("mrdgo");
    private static Optional<String> mail = Optional.ofNullable(null);

    public static void main(String[] args)
    {
        // Usage 1
        System.out.println("Is name set? " + name.isPresent());
        System.out.println("Is mail set? " + mail.isPresent() + "\n");
        // Usage 2
        System.out.println("Name: " + name.orElseGet( () -> "[none]" ) );
        System.out.println("Mail: " + mail.orElseGet( () -> "[none]" ) + "\n");
        // Usage 3
        System.out.println(name.map( s -> "Hello " + s + "!").orElse("Hi stranger!"));
        System.out.println(mail.map( s -> "Mail to " + s + "!").orElse("Do not mail!"));

    }
}
