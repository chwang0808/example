package com.chwang.example.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * CopyOnWriteArrayList 类的例子
优点:
1.解决的开发工作中的多线程的并发问题。---用于读取多于修改的场景

缺点:
1.内存占有问题:很明显，两个数组同时驻扎在内存中，如果实际应用中，数据比较多，而且比较大的情况下，占用内存会比较大，针对这个其实可以用ConcurrentHashMap来代替。
2.数据一致性:CopyOnWrite容器只能保证数据的最终一致性，不能保证数据的实时一致性。所以如果你希望写入的的数据，马上能读到，请不要使用CopyOnWrite容器
 
 *
 */
public class CopyOnWriteArrayListExample {

	private static Integer THREAD_NUM = new Integer(10);
	//private static List<Integer> list = new ArrayList<Integer>();
	private static List<Integer> list = new CopyOnWriteArrayList<Integer>();
	
	public static void main(String[] args) throws InterruptedException {
 
		ExecutorService service = Executors.newFixedThreadPool(THREAD_NUM*2);

		for (int i = 0; i < THREAD_NUM; i++) {
			//移除一个元素
			service.execute(new Runnable() {
				@Override
				public void run() {
			
				   list.add(1);
				   System.out.println("add---"+list.toString());
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
			//移除一个元素
			service.execute(new Runnable() {
				@Override
				public void run() {
			
					if (list.size() > 0) {
						list.remove(0);
					}
					  System.out.println("rem---"+list.toString());
						try {
							Thread.sleep(1000);
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				}
			});
			
		}
		service.shutdown();

	}

}
