import java.io.*;
import java.net.*;
import java.util.*;

public class ProbeTask implements Runnable
{
    private Socket socket = null;
    private String addr = null;
    private int port;
    
    ProbeTask(String hostName, int port)
    {
	addr = hostName;
	this.port = port;
    }
    private void try_port(int port)
    {
        try
	{
	    socket = new Socket(addr, port);
	    System.out.println("socket on port " + port + " created successfully");
	}
	catch(ConnectException e)
	{
	   // System.out.println("port: " + port + " connect failed");
	}
	catch(UnknownHostException e)
	{
	    System.out.println(e);
	}
	catch(IOException e)
	{
	    System.out.println(e);
	}
        try
	{
	    if(socket != null)
                socket.close();	    
	}
	catch(IOException e)
	{
	    System.out.println(e);
	}

    }
    @Override
    public void run()
    {
	try
	{
            try_port(port);
	}
	catch(Exception e)
	{
	    System.out.println("exception caught: " + e);
	}
    }
}
