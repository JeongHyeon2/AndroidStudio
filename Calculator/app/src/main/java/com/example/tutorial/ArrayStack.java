package com.example.tutorial;

public class ArrayStack<E>{
	
	private int top=-1;
	public static final int capacity=1000;
	private E[] s = (E[])new Object[capacity];

	public ArrayStack(){
		
	}
	public int getCapacity() {
		return capacity;
	}

	public boolean isEmpty() {
		return (top==-1);
	}
	public boolean isFull() {
		return (getCapacity()==top+1);
	}
	public void push(E e) throws Exception {
		if(isFull()) throw new Exception();
		s[++top]=e;
	}
	public E top() {
		if(isEmpty()) return null;
		return s[top];
	}
	public E pop() {
		if(isEmpty()) return null;
		E e = s[top];
		s[top]=null;
		top--;
		return e;
	}

}
