import java.util.ArrayList;
import java.util.Scanner;
import java.util.concurrent.Semaphore;


public class Producer implements Runnable {
	
	private String producer;
	private Store store;
	private Good good;
	private int id;
	private int size;
	private idProducer idproducer;
	private Semaphore semID;
	private Semaphore semMutualExclusion;
	private Semaphore semEmptyStore;
	private Semaphore semCountPositions;

	public Producer(String producer,Store store,idProducer idproducer,Semaphore semID,Semaphore semMutualExclusion,Semaphore semEmptyStore,Semaphore semCountPositions,int size) {
		
		this.producer=producer;
		id=0;
		this.size=size;
		this.store=store;
		this.idproducer=idproducer;
		this.semID=semID;
		this.semMutualExclusion=semMutualExclusion;
		this.semEmptyStore=semEmptyStore;
		this.semCountPositions=semCountPositions;
	}


	@Override
	public void run() {
		
		int i=0;
		while(i<100) {
			
			acquireSemID();
			id=idproducer.getID();
			semID.release();
			
			good=produce(id);
			
			acquireSemCountPositions();
			acquireSemMutualExclusion();
			storeGood(good);
			semMutualExclusion.release();
			semEmptyStore.release();
			i++;
			
			print_prod(producer,good);
		}
	}
	
	private Good produce(int i) {
		
		String s = "Good" + String.valueOf(i);  
		Good good = new Good(i,s);
		
		return good;
	}
	
	private void storeGood(Good good) {
		
		try {
			store.storeGood(good);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void acquireSemID () {
		
		try {
			semID.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
	
	
	public void acquireSemCountPositions () {
		
		try {
			semCountPositions.acquire();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void print_prod(String producer,Good good) {
			
			String s1 = String.valueOf(good.getNum());
			String s = producer + " produced" + " good " + s1 + " and stored it.";
			System.out.println(s);
		}

}
