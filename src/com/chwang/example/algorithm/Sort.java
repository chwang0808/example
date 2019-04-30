package com.chwang.example.algorithm;

import java.util.Arrays;

public class Sort {

	public static void main(String[] args) {

		int[] array = new int[]{12,1,3,6,8,0,34,56,100,87,65,23};
		/*insertionSort(array);*/
		quickSort(array, 0, array.length-1);
		System.out.println(Arrays.toString(array));
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
	

	
	//交换
	private static void change(int[] array,int i,int j ){
		int val = array[i];
		array[i] = array[j];
		array[j] = val;
	}
	
	
	
	
	

}
