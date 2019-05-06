package com.chwang.example.thread;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 实现网站的流量统计，统计每个网站的访问数量
 * 
 * 对increase方法加synchronized关键字使 increase方法同步，这样的统计结果符合我们的预期。但是锁的开销很大。
 * 用cas实现同步:putIfAbsent() replace()
 */
public class CurrentHashMapExample1 {

	//存储对象
	private ConcurrentHashMap<String, Long> counterMap = new ConcurrentHashMap<>();
	//访问次数加1
	public synchronized void increase(String url){
		Long urlCount = counterMap.get(url);
		urlCount = (urlCount == null) ?  1L:++urlCount;//
		counterMap.put(url, urlCount);
	}
	//每个线程的任务就是实现+1，如果一次实现不了，就不断的重复操作
	public long increase2(String url) {
        Long oldValue, newValue;
        while (true) {
            oldValue = counterMap.get(url);
            if (oldValue == null) {
                newValue = 1l;
                //初始化成功，退出循环
                if (counterMap.putIfAbsent(url, 1l) == null)
                    break;
                //如果初始化失败，说明其他线程已经初始化过了
            } else {
                newValue = oldValue + 1;
                //+1成功，退出循环
                if (counterMap.replace(url, oldValue, newValue))
                    break;
                //如果+1失败，说明其他线程已经修改过了旧值,再重新循环
            }
        }
        return newValue;
    }
	
	public static void main(String[] args) throws InterruptedException {

		CurrentHashMapExample1 example = new CurrentHashMapExample1();
		 
 		//模拟并发的次数
		Integer callTime = 10000;
		CountDownLatch latch = new CountDownLatch(callTime);
		ExecutorService executorService = Executors.newFixedThreadPool(10);
		String url = "http://localhost:8080";
		//模拟并发
		for(int i = 0 ;i < callTime;i++){
			//每一个线程的任务就是实现+1
			executorService.execute(new Runnable() {
				@Override
				public void run() {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					example.increase(url);
					System.out.println("当前线程："+Thread.currentThread().getName()+"  "+example.counterMap.get(url));
					latch.countDown();
				}
			});
		}
		latch.await();
		executorService.shutdown();
		System.out.println(example.counterMap.get(url).longValue()); 
	}

}
