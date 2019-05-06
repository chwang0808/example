package com.chwang.example.thread;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;


/**
 * 
 * @author Administrator
 *
 */
public class ScheduledThreadPoolExample {
	
	
	public static void main(String[] args) {
		//创建一个计划任务线程池服务
		ScheduledExecutorService service = Executors.newScheduledThreadPool(10);
 
		//创建一个计划任务 0表示启动后即执行 2表示任务间隔时间
		//由于任务执行需要1秒，任务调度必须等待前一个任务完成。也就是这里的每隔2秒的意思是，前一个任务完成后2秒再开启新的一个任务。
/*		service.scheduleWithFixedDelay(new Runnable() {
			
			@Override
			public void run() {

				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println(Thread.currentThread().getId()+"---执行一个计划任务！");
			}
		}, 0, 2, TimeUnit.SECONDS);
		*/
		service.scheduleAtFixedRate(new Runnable() {
			
			@Override
			public void run() {

				try {
					Thread.sleep(3000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println(System.currentTimeMillis() +" "+Thread.currentThread().getId());
				
			}
		}, 0, 1, TimeUnit.SECONDS);
	}

}
