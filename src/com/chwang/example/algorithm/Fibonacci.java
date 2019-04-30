package com.chwang.example.algorithm;

/**
 * 쳲��������У�1��1��2��3��5��8��13��21��34������
 * ����Ҫ������һ������n���������쳲��������еĵ�n� n<=39
 * @author Administrator
 *
 */
public class Fibonacci {

	//������ʵ��
	public   int fibonacci(int n){
		if(n <=0) return 0;
		if(n==1 ||n==2) return 1;
		int first = 1,second= 1,third=0;
		for(int i=3;i<=n;i++){
			third = first + second;
			first = second;
			second = third;
		}
		return third;
	}
	//�ݹ�ʵ��
	/**
	 * �ݹ���ԭ��
		һ����һ�ֿ����˳����������� n=1 �ǵݹ����
		�����ڳ��Խ�һ�����⻯�򵽸�С�Ĺ�ģ    f(n) ����Ϊf(n-1)��f(n-2)
		�������������ⲻ�����ص��Ĳ���  Υ���˵�����ԭ�� ���ڲ�ͬ�ݹ��������ظ��ԵĹ���  
	 * @param n
	 * @return
	 */
	public   int fib(int n){
		if(n <=0) return 0;
		if(n == 1) return 1;
		return  fib(n-1)+fib(n-2);
	}
	
	/**  ��̨������
		��Ŀ������
		һֻ����һ�ο�������1��̨�ף�Ҳ��������2���������������һ��n����̨���ܹ��ж�����������
		
		���������
		������������ a.�������������1�׻���2�ף���ô�ٶ���һ��������һ�ף���ôʣ�µ���n-1��̨�ף�������f(n-1); 
		b.�ٶ���һ��������2�ף���ôʣ�µ���n-2��̨�ף�������f(n-2) 
		c.��a��b������Եó�������Ϊ: f(n) = f(n-1) + f(n-2) 
		d.Ȼ��ͨ��ʵ�ʵ�������Եó���ֻ��һ�׵�ʱ�� f(1) = 1 ,ֻ�����׵�ʱ������� f(2) = 2 
		�ҹ��ɷ������� f(1) = 1, f(2) = 2, f(3) = 3, f(4) = 5�� �����ܽ��f(n) = f(n-1) + f(n-2)�Ĺ��ɡ�
		 ����Ϊʲô����������Ĺ����أ���������6��̨�ף����ǿ��Դӵ�5��һ����6�������Ļ��ж����ַ�������5���ж����ַ�������6��
		 ��������Ҳ���Դ�4����������6������4�ж����ַ����Ļ������ж����ַ�������6�������Ĳ��ܴ�3����6ʲô����������������f(6) = f(5) + f(4)��������Ҳ�ܺ�����̬��̨�׵������ˡ�
	 * @param n
	 * @return
	 */
	
	public  int jumpFloor(int n){
		if(n <= 0) return 0;
		if(n == 1) return 1;
		if(n == 2) return 2;
		int first = 1,second = 2,third =0;
		for(int i = 3;i <= n;i++){
			third = first + second;
			first = second;
			second = third;
		}
		return third;
	}
	/**��̬��̨������
	��Ŀ������
	һֻ����һ�ο�������1��̨�ף�Ҳ��������2��������Ҳ��������n���������������һ��n����̨���ܹ��ж�����������
	���������
	����n>=2����һ����n����������1������2��������n�� ����1����ʣ��n-1������ʣ��������f(n-1) ����2����ʣ��n-2������ʣ��������f(n-2) ...... 
	��n-1����ʣ��1������ʣ��������f(1) ����n����ʣ��0������ʣ��������f(0) ��
	������n>=2������£� f(n)=f(n-1)+f(n-2)+...+f(1) ��Ϊf(n-1)=f(n-2)+f(n-3)+...+f(1) ����f(n)=2*f(n-1) ��f(1)=1,���Կɵ�f(n)=2^(number-1)
	**/
	public int JumpFloorII(int number) {
		return 1 << --number;//2^(number-1)��λ�Ʋ������У�����
	}

}
