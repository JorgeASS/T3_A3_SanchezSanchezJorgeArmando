import java.util.concurrent.ArrayBlockingQueue;

public class BlockingBuffer  implements Buffer{

	private final ArrayBlockingQueue<Integer> buffer;
	
	public BlockingBuffer() {
		buffer = new ArrayBlockingQueue<Integer>(1);
	}
	
	public void blockingPut(int value) throws InterruptedException {
		// TODO Auto-generated method stub
		buffer.put(value); //place value in buffer
		System.out.printf("%s%2d\t%s%d%n", "Prodecer writes ", value, "Buffer cells: ", buffer.size());
	}
	
	//return value from buffer
	@Override
	public int blockingGet() throws InterruptedException {
		int readValue = buffer.take(); // remove value from buffer
		System.out.printf("%s %2d\t%s%d%n", "Consume reads ", readValue, "Buffer cells occupied ", buffer.size());
		return readValue;
	}


}
