import java.awt.DisplayMode;

public class SynchronizedBuffer implements Buffer{
	private int buffer = -1; // shared by producer and consumer threads
	private boolean occupied = false;
	

	@Override
	public synchronized void blockingPut(int value) throws InterruptedException {
		// TODO Auto-generated method stub
		//While there sre no empty locations, place thread in waiting state
		while (occupied) {
			//output thread information and buffer information, then wait
			System.out.println("Producer tries to write.");//for demo only
			displayState("Buffer full. Producer waits."); // for demo only
			wait();
		}
		buffer = value; // set new buffer value
		//indicate producer cannot store another value
		// until consumer retrieves current buffer value
		occupied = true;

		DisplayState("Producer writes " + buffer);//for demo only

		notifyAll();//tell waiting thread(s) to enter runnable state
	}//end method blockingPut; releases lock on SynchronizedBuffer

	@Override
	public synchronized int blockingGet() throws InterruptedException {
		// TODO Auto-generated method stub
		//while not data to read, place thread in waiting state
		while (!occupied) {
			// output thread information and buffer information, then wait
			System.out.println("Consumer tries to read."); // for demo only
			displayState("Buffer empty. Consumer waits."); // for demo only
			wait();
		}
		// indicate that producer can store another value
		// because consumer just retrieved buffer value 
		occupied = false; 
		
		displayState("Consumer reads " + buffer); // for demo only
		
		notifyAll(); // tell waiting thread(s) to enter runnable state
		return buffer;
	}
	
	// display current operation and buffer state; for demo only
	private void displayState(String operation) {
	System.out.printf("%-40s%d\t\t%b%n%n", operation, buffer, occupied);
	}

}
