import java.util.concurrent.*;
import java.util.List;
import java.util.ArrayList;

public class Driver
{

    static final int MAX_T = 256;
    public static void main(String args[])
    {

        ExecutorService pool = Executors.newFixedThreadPool(MAX_T);
        
	List<Callable<Object>> tasks = new ArrayList<Callable<Object>>(1024);
        
	String host = args[0];
        
	for(int port = 1; port < 1025; ++port)
	{
	    Runnable r = new ProbeTask(host, port);
	    tasks.add(Executors.callable(r));
	}

	long startT = System.nanoTime();

        try
	{
	    pool.invokeAll(tasks);
        }
	catch(Exception e)
	{
	    System.out.println("caught exception " + e);
	}
	long endT = System.nanoTime();
        
	long elapsedTime = (endT - startT) / 1000000;

	System.out.println("total scan time: " + elapsedTime + " milliseconds");
       
	pool.shutdown();
	
    }
}
