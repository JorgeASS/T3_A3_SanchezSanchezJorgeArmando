
public interface Buffer {
	// place int value into Buffer
	public void blockingPut(int value) throws InterruptedException;
	// return int value from Buffer
	public int blockingGet() throws InterruptedException;

}
