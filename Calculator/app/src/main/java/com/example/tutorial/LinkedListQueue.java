package com.example.tutorial;


public class LinkedListQueue <E> implements Queue<E>{
	private LinkedList<E> list = new LinkedList<E>();

	@Override
	public int size() {
		return list.getSize();
	}

	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}

	@Override
	public E first() {
		return list.getFirst();
	}

	@Override
	public E dequeue() {
		return list.deleteFirst();	
	}

	@Override
	public void enqueue(E e) {
		list.addLast(e);
	}

}
 interface Queue<E> {
	int size(); 
	boolean isEmpty();
	E first();
	E dequeue();
	void enqueue(E e);
}


