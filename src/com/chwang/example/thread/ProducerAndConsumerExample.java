package com.chwang.example.thread;

/**
 * 演示生产者和消费者问题
 * 
 * @author Administrator
 *
 */
public class ProducerAndConsumerExample {

	private int count = 0;
	private static int FULL = 10;// 容量是10
	private String LOCK = "lock"; // 锁

	public static void main(String[] args) {

		ProducerAndConsumerExample cake = new ProducerAndConsumerExample();
		Thread producer = new Thread(cake.new producer(), "producer1");
		Thread consumer = new Thread(cake.new consumer(), "consumer1");

		producer.start();
		consumer.start();

	}

	// 生产者
	class producer implements Runnable {

		public producer() {

		}

		@Override
		public void run() {
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			while (true) {
				synchronized (LOCK) {
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					while (count == FULL) {
						try {
							LOCK.wait();//当前线程等待，让出锁
							//System.out.println("生产者等待。。。。。。");
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}
					count++;
					System.out.println("生产者生产一个商品。目前商品数目是：" + count + "...");
					LOCK.notifyAll();//唤醒等待该锁的线程
					//System.out.println("生产者唤醒消费者。");
				}
			}
		}
	}

	// 生产者
	class consumer implements Runnable {

		public consumer() {

		}

		@Override
		public void run() {
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			while (true) {
				synchronized (LOCK) {

					try {
						Thread.sleep(1000);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					while (count == 0) {
						try {
							LOCK.wait();
						//	System.out.println("消费者等待。。。。。。");
						} catch (InterruptedException e) {
							e.printStackTrace();
						}

					}
					count--;
					System.out.println("消费者消费一个商品。目前商品数目是：" + count + "...");
					LOCK.notifyAll();
					//System.out.println("消费者唤醒生产者。");
				}
			}

		}

	}

}
