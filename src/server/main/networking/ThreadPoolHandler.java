package server.main.networking;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolHandler implements Runnable{
	
	private ThreadPoolExecutor threadPool;
	private SynchronousQueue<Runnable> workQueue;
	
	public void run() {
		initializeThreadPoolHandler();
	}
	
	private void initializeThreadPoolHandler() {
		workQueue = new SynchronousQueue<Runnable>();
		threadPool = new ThreadPoolExecutor(8, 8, 1, TimeUnit.SECONDS, workQueue);
	}
	
	public void addTask(Runnable runnable) {
		workQueue.add(runnable);
	}
}
