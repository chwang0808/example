package com.chwang.example.algorithm;

public class halfsearch {

	public static void main(String[] args) {
		int[] array = new int[]{21,22,32,34,56,78,81, 96,96,96,96,96,96, 96, 99,100,101 };
		//System.out.println(binarySerach(array,96));
		System.out.println(findFirstEqual(array,96));
	}
	
	
	// ���ҵ�һ����ȵ�Ԫ��
	static int findFirstEqual(int[] array, int key) {
	    int left = 0;
	    int right = array.length - 1;

	    // ��������� <=
	    while (left <= right) {
	        int mid = (left + right) / 2;
	        if (array[mid] >= key) {
	            right = mid - 1;
	        }
	        else {
	            left = mid + 1;
	        }
	    }
	    if (left < array.length && array[left] == key) {
	        return left;
	    }
	    
	    return -1;
	}
 
	/**
	 * 0  1  2  3  4  5  6   7   8   9 
	 *{21,22,32,34,56,78,81, 90, 96, 99 }
	 * ��array�������ҵ�key��ֵ���±꣬�Ҳ����򷵻�-1
	 * @param array
	 * @param key
	 * @return
	 */
	static int binarySerach(int[] array, int value) {
		int left = 0,right = array.length-1,mid;
		
		while(left <= right){
			mid = (left + right)/2;
			if(array[mid] == value)
				return mid;
			if(array[mid] < value)
				left = mid+1;
			if(array[mid] > value)
				right = mid-1;
		}
		return -1;
	}
	
	
	//���ҵ�һ����value��ȵ�Ԫ���±�
	static int findFirstEqualElement(int[] array, int value) {
		int left = 0,right = array.length-1,mid;
		
		while(left <= right){
			mid = (left + right)/2;
			if(array[mid] == value){//���������ҵ��˸�Ԫ��
				int index = mid-1;
				//�ӵ�ǰ�±���ǰ����
				for(;index >= left;index--){
					if(array[index] != value){
						 ++index;
						 break;
					} 
				}
				return index;
			}
			if(array[mid] < value)
				left = mid+1;
			if(array[mid] > value)
				right = mid-1;
		}
		return -1;
	}

}
