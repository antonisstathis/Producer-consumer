import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class App {

	public static void main(String[] args) {
		
		int size = 30;
		idProducer id = new idProducer();
		Semaphore semID = new Semaphore(1);
		ExecutorService executorService = Executors.newCachedThreadPool();
		Semaphore semMutualExclusion = new Semaphore(1);
		Semaphore semEmptyStore = new Semaphore(0);
		Semaphore semCountPositions = new Semaphore(size);
		
		Store store = new Store(size);
		Producer producer1 = new Producer("Producer1",store,id,semID,semMutualExclusion,semEmptyStore,semCountPositions,size);
		Producer producer2 = new Producer("Producer2",store,id,semID,semMutualExclusion,semEmptyStore,semCountPositions,size);
		Consumer consumer1 = new Consumer("Consumer1",store,semMutualExclusion,semEmptyStore,semCountPositions,size);
		Consumer consumer2 = new Consumer("Consumer2",store,semMutualExclusion,semEmptyStore,semCountPositions,size);
		
		executorService.execute(producer1);
		executorService.execute(producer2);
		executorService.execute(consumer1);
		executorService.execute(consumer2);
	}

}
