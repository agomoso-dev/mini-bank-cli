package io.github.agomosodev;

public class App
{
    public static void main( String[] args )
    {
        Client client1 = new Client(1, "John", "Doe", "fds@gmail.com", "23", null);
        String c= client1.toString();
        System.out.println(c);
    }
}
