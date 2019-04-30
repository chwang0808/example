package com.chwang.example.designmode;

//����ģʽ�����õ�ʵ����ʱ���ٴγ�ʼ��
public class SingletonMode {
 
	private static SingletonMode instance ;
	
	private SingletonMode() {}
	
	//�̲߳���ȫ,����߳�A��Bͬʱ�����������
	public static SingletonMode getInstance() {
		
		if(null == instance ) { //A�ж�Ϊtrue ������½�һ��ʵ�������Ǵ�ʱA
		/*	1���ڶ������������Ҫ���ڴ�
			2��������ʵ��������Ĭ��ֵ
			3��ִ��ʵ����ʼ������*/
			instance = new SingletonMode();
		}
		return instance;

	}
}
