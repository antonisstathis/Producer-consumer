import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;

public class Store {
	
	private Good[] array;
	private int head;
	private int tail;
	int size;
	private Good good;
	
	public Store(int size) {
		
		tail=0;
		head=0;
		array = new Good[size];
		this.size=size;
	}
	
	public void storeGood(Good good) throws InterruptedException {
		
		if (tail==size) {
			System.out.println("Buffer is full.");
		}
		else {
			array[tail]=good;
			tail=tail+1;
		}
		
	}
	
	public Good consumeGood() throws InterruptedException {
		
		if (tail==0)
			System.out.println("Buffer is empty.");
		else {
			good=array[head];
			array[head]=(Good) null;
			shiftLeft(tail);
			tail=tail-1;
		}
		
		return good;
	}
	
	public void shiftLeft(int tail) {
		
		for(int i=0;i<tail;i++) {
			if (i>size-2)
				break;
			else
				array[i]=array[i+1];
		}
	}

}
