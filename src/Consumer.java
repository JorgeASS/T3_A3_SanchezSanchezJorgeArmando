import java.security.SecureRandom;

public class Consumer implements Runnable{
	private static final SecureRandom generator = new SecureRandom();
	private final Buffer sharedLocation; // reference to shared objet
	
	//constructor
	public Consumer(Buffer sharedLocation) {
		this.sharedLocation = sharedLocation;
	}
	
	// read sharedLocation's value 10 times and sum the values
	@Override
	public void run() {
		int sum = 0;
		
		for (int count = 1; count <= 10; count++) {
			//sleep 0 to 3, second, read value from buffer and add to sum
			try {
				Thread.sleep(generator.nextInt(3000));
				sum += sharedLocation.blockingGet();
				System.out.printf("\t\t\t%2d%n");
			} catch (InterruptedException exception) {
				Thread.currentThread().interrupt();
			}
		}
		System.out.printf("%n%s %d%n%s%n", "Consumer read values totaling", sum, "Terminating Consumer");
	}

}
