import java.security.SecureRandom;

public class Producer implements Runnable{
	private static final SecureRandom generator = new SecureRandom();
	private final Buffer sharedLocation;
	
	//Constructor
	public Producer(Buffer sharedLocation) {
		this.sharedLocation = sharedLocation;
	}

	//store values from 1 to 10 in sharedLocaton
	@Override
	public void run() {
		int sum = 0;
		
		for (int count = 1; count <= 10; count++) {
			try { // sleep 0 to 3 seconds, then place value in Buffer
				Thread.sleep(generator.nextInt(3000)); // random sleep
				sharedLocation.blockingPut(count); //set value in buffer
				sum += count; //increment sum of values
				System.out.printf("\t%2d%n", sum);
			} catch (InterruptedException exception) {
				Thread.currentThread().interrupt();
			}
		}
		System.out.printf("Producer done producing%nTermination Producer%n");
	}	

}
