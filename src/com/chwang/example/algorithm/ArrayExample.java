package com.chwang.example.algorithm;

import java.util.Arrays;

public class ArrayExample {

	
	public static void main(String[] args) {

		int[] a = new int[]{1,5,7,10,15};	
		int[] b = new int[]{2,4,12,13,19};	
		ArrayExample mergeArray = new ArrayExample();
		int[] c = mergeArray.mergeSortedArray(a, b);
		System.out.println( Arrays.toString(c));
		}
	/**
	 * 合并两个有序数组并排序 
	 * @param a{1,5,7,10,15}
	 * @param b{2,4,12,13,19}
	 * @return c[1, 2, 4, 5, 7, 10, 12, 13, 15, 19]
	 */
	public  int[] mergeSortedArray(int[] a, int[] b){
		int lengthA = a.length;
		int lengthB = b.length;
		
		int[] c = new int[lengthA + lengthB];
		
		int i=0,j=0,k=0;
		
		while (i<lengthA && j<lengthB) {
			if(a[i] < b[j]){
				c[k++] = a[i++];
			}else{
				c[k++] = b[j++];
			}
		}
		//a没有遍历完
		while (i < lengthA) {
			c[k++] = a[i++];
		}
		//b没有遍历完
		while(j < lengthB){
			c[k++] = b[j++];
		}
		return c;
	}
	
	 
	
 

}
