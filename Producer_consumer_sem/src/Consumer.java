import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.Semaphore;

public class Consumer implements Runnable {
	
	private String consumer;
	private Store store;
	private Good good;
	private int size;
	private Semaphore semMutualExclusion;
	private Semaphore semEmptyStore;
	private Semaphore semCountPositions;

	public Consumer(String consumer,Store store,Semaphore semMutualExclusion,Semaphore semEmptyStore,Semaphore semCountPositions,int size) {
		
		this.consumer=consumer;
		this.store=store;
		this.size=size;
		this.semMutualExclusion = semMutualExclusion;
		this.semEmptyStore=semEmptyStore;
		this.semCountPositions=semCountPositions;
	}


	@Override
	public void run() {
		
		int i = 0;
		while(i<100) {
			
			acquireSemEmptyStore();
			acquireSemMutualExclusion();
			good=consume();
			semMutualExclusion.release();
			semCountPositions.release();
			i++;
			
			print_con(good,consumer);
		}
	}
	
	public void acquireSemMutualExclusion () {
		
		try {
			semMutualExclusion.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void acquireSemEmptyStore () {
			
			try {
				semEmptyStore.acquire();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	
	public Good consume() {
		try {
			good=store.consumeGood();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return good;
	}
	
	public void print_con(Good good,String consumer) {
			
			int i = good.getNum();
			String s1 = String.valueOf(i);
			String s = consumer + " consumed" + " good " + s1 + ".";
			System.out.println(s);
		}
	

}
