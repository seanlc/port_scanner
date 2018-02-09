CC= javac
CFLAGS= -g

main: Driver.java ProbeTask.java
	$(CC) $(CFLAGS) Driver.java

clean:
	rm -rf *.class
