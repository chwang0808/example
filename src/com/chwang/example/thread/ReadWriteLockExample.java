package com.chwang.example.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * ReadWriteLock 适用于读操作远多于写操作场景
 * @author Administrator
 *
 */
public class ReadWriteLockExample {
	private static ReadWriteLock readWriteLock = new ReentrantReadWriteLock();//新建一个读写锁
	private static Integer THREAD_NUM = new Integer(10);
	public static void main(String[] args) throws InterruptedException {
		ReadWriteLockExample example = new ReadWriteLockExample();
		ExecutorService service = Executors.newFixedThreadPool(THREAD_NUM);
		 // 1 检测读锁与读锁之间是互斥还是共享
	/* 	for (int i = 0; i < THREAD_NUM/2; i++) {
			service.execute(example.new readThread());
			service.execute(example.new readThread());
		} */
		//2 检测读写锁之间是互斥还是共享
 	/* 	for (int i = 0; i < THREAD_NUM/2; i++) {
				service.execute(example.new readThread());
				service.execute(example.new writeThread());
			} */
		// 3 检测写写锁之间是互斥还是共享
/*	 	for (int i = 0; i < THREAD_NUM/2; i++) {
			service.execute(example.new writeThread());
			service.execute(example.new writeThread());
		} */
	 	
	 	//4 检测锁的可重入性
	/* 	readWriteLock.writeLock().lock();
	 	readWriteLock.writeLock().lock();
	 	service.execute(new Runnable() {
			
			@Override
			public void run() {
			 	readWriteLock.writeLock().lock();
			 	try {
					Thread.sleep(3000);
					System.out.println(" thread get lock");
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			 	readWriteLock.writeLock().unlock();

			}
		});
	 	System.out.println("main get lock");
	 	Thread.sleep(3000);
	 	readWriteLock.writeLock().unlock();
	 	readWriteLock.writeLock().unlock();*/
		
		//5 检测锁的升级,读锁不能升级为写锁    ---get read lock
	/*	readWriteLock.readLock().lock();
		System.out.println("get read lock");
		readWriteLock.writeLock().lock();
		System.out.println("get write lock");*/
		
		// 6 检测锁的降级，写锁能降级为读锁
        //     get write lock
		//     get read lock

		readWriteLock.writeLock().lock();
		System.out.println("get write lock");
		readWriteLock.readLock().lock();
		System.out.println("get read lock");
		//写锁虽然降级了，但是写锁依然被占用，读锁也被占用，需要释放读写锁
		readWriteLock.writeLock().unlock();
		readWriteLock.readLock().unlock();
		//此刻读写锁是否已经释放了呢？
        service.execute(example.new writeThread());

		service.shutdown();
	}
	

	/**
	 * 读数据线程
	 * @author Administrator
	 *
	 */
	private class readThread implements Runnable{
		/**
		 * 读取锁是共享锁
		 */

		@Override
		public void run() {

			//获取读锁
			readWriteLock.readLock().lock();
			   System.out.println("read--"+Thread.currentThread().getId()+" 获取锁！-"+System.currentTimeMillis());
			//开始读取
		   // System.out.println("read--"+Thread.currentThread().getId()+" 在读取！---"+System.currentTimeMillis());
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			   System.out.println("read--"+Thread.currentThread().getId()+" 释放锁！------"+System.currentTimeMillis());
			//释放锁
			readWriteLock.readLock().unlock();

		}
		
		
	}
	

	/**
	 * 读数据线程
	 * @author Administrator
	 *
	 */
	private class writeThread implements Runnable{

		@Override
		public void run() {
			//获取写锁
			readWriteLock.writeLock().lock();
			 System.out.println("write-"+Thread.currentThread().getId()+" 获取锁！-"+System.currentTimeMillis());
	
			//开始写入
			//System.out.println("write-"+Thread.currentThread().getId()+" 在写入！---"+System.currentTimeMillis());
			try {
				Thread.sleep(3000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			   System.out.println("write-"+Thread.currentThread().getId()+" 释放锁！------"+System.currentTimeMillis());
			//释放锁
			readWriteLock.writeLock().unlock();

		}
		
		
	}
	/* 1 可以看到线程几乎同时获取锁，证明读锁之间是共享的，这也是readWriteLock优于synchronized同步的地方。
 
	read--11 获取锁！-1557131121115
	read--10 获取锁！-1557131121115
	read--13 获取锁！-1557131121115
	read--12 获取锁！-1557131121115
	read--14 获取锁！-1557131121115
	read--17 获取锁！-1557131121115
	read--18 获取锁！-1557131121116
	read--15 获取锁！-1557131121116
	read--16 获取锁！-1557131121116
	read--19 获取锁！-1557131121116
	read--11 释放锁！------1557131124115
	read--12 释放锁！------1557131124115
	read--10 释放锁！------1557131124115
	read--16 释放锁！------1557131124116
	read--19 释放锁！------1557131124116
	read--15 释放锁！------1557131124116
	read--13 释放锁！------1557131124116
	read--14 释放锁！------1557131124117
	read--17 释放锁！------1557131124117
	read--18 释放锁！------1557131124117
	*/
	/*2 一共有5个读线程和5个写线程，分析得出读和写线程之间不会共享锁
	read--10 获取锁！-1557131166071
	read--12 获取锁！-1557131166072
	read--10 释放锁！------1557131169071
	read--12 释放锁！------1557131169072
	write-13 获取锁！-1557131169072
	write-13 释放锁！------1557131172072
	write-11 获取锁！-1557131172072
	write-11 释放锁！------1557131175072
	read--14 获取锁！-1557131175072
	read--14 释放锁！------1557131178072
	write-15 获取锁！-1557131178072
	write-15 释放锁！------1557131181073
	read--16 获取锁！-1557131181073
	read--16 释放锁！------1557131184073
	write-17 获取锁！-1557131184073
	write-17 释放锁！------1557131187073
	read--18 获取锁！-1557131187073
	read--18 释放锁！------1557131190073
	write-19 获取锁！-1557131190073
	write-19 释放锁！------1557131193073
	*/
	/*3 分析可以得出写写锁之间是互斥锁
	write-10 获取锁！-1557131333261
	write-10 释放锁！------1557131336261
	write-12 获取锁！-1557131336261
	write-12 释放锁！------1557131339262
	write-11 获取锁！-1557131339262
	write-11 释放锁！------1557131342262
	write-13 获取锁！-1557131342262
	write-13 释放锁！------1557131345262
	write-14 获取锁！-1557131345262
	write-14 释放锁！------1557131348263
	write-16 获取锁！-1557131348263
	write-16 释放锁！------1557131351264
	write-15 获取锁！-1557131351264
	write-15 释放锁！------1557131354264
	write-17 获取锁！-1557131354264
	write-17 释放锁！------1557131357265
	write-18 获取锁！-1557131357265
	write-18 释放锁！------1557131360265
	write-19 获取锁！-1557131360265
	write-19 释放锁！------1557131363265
     */
}
