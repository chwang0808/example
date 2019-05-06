package com.chwang.example.thread;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 实现网站的流量统计，统计每个网站的访问数量
 * 
 * concurrentHashMap是个线程安全的并发容器，所以没有显示加同步，实际效果呢并不如所愿。问题
 * 就出在increase方法，concurrentHashMap能保证的是每一个操作（put，get,delete…）
 * 本身是线程安全的，但是我们的increase方法，对concurrentHashMap的操作是一个组合，先get
 * 再put，所以多个线程的操作出现了覆盖。
 */
public class CurrentHashMapExample {

	//存储对象
	private ConcurrentHashMap<String, Long> counterMap = new ConcurrentHashMap<>();
	//访问次数加1
	public  void increase(String url){
		Long urlCount = counterMap.get(url);
		urlCount = (urlCount == null) ?  1L:++urlCount;//
		counterMap.put(url, urlCount);
	}
	
	public static void main(String[] args) throws InterruptedException {

		CurrentHashMapExample example = new CurrentHashMapExample();
		//模拟并发的次数
		Integer callTime = 10000;
		CountDownLatch latch = new CountDownLatch(callTime);
		ExecutorService executorService = Executors.newFixedThreadPool(10);
		String url = "http://localhost:8080";
		//模拟并发
		for(int i = 0 ;i < callTime;i++){
			executorService.execute(new Runnable() {
				
				@Override
				public void run() {
					example.increase(url);
					latch.countDown();
				}
			});
		}
		latch.await();
		executorService.shutdown();
		System.out.println(example.counterMap.get(url).longValue());
	}

}
