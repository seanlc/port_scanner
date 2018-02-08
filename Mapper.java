import java.io.*;
import java.net.*;
import java.util.*;

public class Mapper
{
    private Socket socket = null;
    private String addr = null;
    private int lPort = 0;
    private int hPort = 0;
    private List<Integer> openPorts = null;

    Mapper(String hostName, int lPort, int hPort)
    {
	addr = hostName;
        this.lPort = lPort;
	this.hPort = hPort;
	openPorts = new ArrayList<>();
    }
    private void try_port(int port)
    {
        try
	{
	    socket = new Socket(addr, port);
	    openPorts.add(port);
	    System.out.println("socket on port " + port + " created successfully");
	}
	catch(ConnectException e)
	{
	    //System.out.println(e);
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
    private void scan_ports()
    {
	System.out.println("SCAN IN PROGRESS");
        for(int i = lPort; i < hPort + 1; ++i)
            try_port(i);	
    }
    
    private void results()
    {
	System.out.println("Opens ports: ");
        for(Integer port: openPorts)
	{
	    System.out.println(port);
	}
    }
    public static void main(String args[])
    {
        Mapper myMap = new Mapper("127.0.0.1", 1, 1024);
	myMap.scan_ports();
	myMap.results();
    }
}
