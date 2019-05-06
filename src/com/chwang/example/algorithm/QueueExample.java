package com.chwang.example.algorithm;

import java.util.Stack;

/**
 * 用两个栈来实现一个队列，完成队列的Push和Pop操作。 队列中的元素为int类型。
 * @author Administrator
 *
 */
public class QueueExample {

    Stack<Integer> stack1 = new Stack<Integer>();
    Stack<Integer> stack2 = new Stack<Integer>();
    
    /**
     * 添加元素都是添加在stack1中
     * @param node
     */
    public void push(int node) {
     while(!stack2.empty()){ 
        stack1.push(stack2.pop());
     } 
     stack1.push(node);
    }
    
    /**
     * 弹出元素都是在stack2中
     * @return
     */
    public int pop() {
    	while(!stack1.empty()){
    		stack2.push(stack1.pop());
    	}
    	return stack2.pop();
    }
}
