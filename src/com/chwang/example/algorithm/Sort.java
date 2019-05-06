package com.chwang.example.algorithm;

import java.util.Arrays;
import java.util.Iterator;


public class Sort {

	public static Integer index= 0;
	public static void main(String[] args) {

		int[] array = new int[]{12,1,3,6,8,0,34,56,100,87,65,23};
		int[] temp = new int[array.length];
		recursiveSort(array, 0, array.length-1, temp);
		/*insertionSort(array);*/
		//quickSort(array, 0, array.length-1);
		System.out.println(Arrays.toString(temp));
	}
	//快速排序
	static void quickSort(int[] array,int low,int high){
		int left = low,right=high,base = array[left],temp;//left right���α� temp�洢�м�ֵ
		 while(right > left){
	 
			 //TODO
		 }
		 array[right] = base;
		 quickSort(array, low, right-1);
		 quickSort(array, right+1, high);
	}
	//选择排序
	static void selectionSort(int[] array){

		for (int i = 0; i < array.length; i++) {
			for (int j = i+1; j < array.length; j++) {
				if(array[i] > array[j]){
					change(array,i,j);
				}
			}
		}
	}
	//冒泡排序
	static void bubbleSort(int[] array){
		for (int i = 0; i < array.length; i++) {
			for (int j = 0; j < array.length -i-1; j++) {
				if(array[j] > array[j+1])
					change(array,j,j+1);
			}
		}
	}
	
	//插入排序
	static void insertionSort(int[] array){
		for (int i = 0; i < array.length; i++) {
			for (int j = i; j > 0; j--) {
				if(array[j] < array[j-1]){
					change(array,j,j-1);
				}
			}
		}
	}
	/**
	 * 归并排序
	 * @param array  原始数组
	 * @param first  开始下标
	 * @param end    结束下标
	 * @param temp  array的副本 存储排序后的数组
	 */
	static void recursiveSort(int[] array,int first ,int end,int[] temp){
		System.out.println("当前递归层数："+index);
		index ++;
		
		int mid= (first+end)/2;  //数组中间的下标

		int i= first , j= mid+1;  //i j 分别表示左右子数组中的下标
		int k = first; //temp数组中的下标
		if(first < end){//子数组的元素个数>1
			 recursiveSort( array, first, mid, temp);//左边排序
			 recursiveSort( array, mid, end, temp);//右边排序
			 //左右两个子数组  合并到temp数组中
			 while(i <= mid && j <= end){
				 if(array[i] < array[j]){
					 temp[k++] = array[i++];
				 }else{
					 temp[k++]= array[j++];
				 }
			 }
			 while(i <= mid){
				 temp[k++] = array[i++];
			 }
			 while(j <= end){
				 temp[k++] = array[j++];
			 }
			//temp更新到array
			 for (int k2 = first; k2 <= end; k2++) {
				array[k2] = temp[k2];
			}
		 }else{//返回到上一层
			 return;
		 }
	}

	
	//交换
	private static void change(int[] array,int i,int j ){
		int val = array[i];
		array[i] = array[j];
		array[j] = val;
	}
	
	
	
	
	

}
