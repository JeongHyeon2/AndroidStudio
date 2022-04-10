package com.example.tutorial;

public class LinkedList<E> {
	private Node<E> head;
	private Node<E> tail;
	private int size ;
	

	public LinkedList() {
		head=null;
		tail=null;
		size=0;
	}

	public boolean isEmpty() {
		return size == 0;
	}

	public int getSize() {
		return size;
	}
	public E getFirst() {
		if (isEmpty())
			return null;
		return head.getElement();
	}
	
	public void addFirst(E e) {
		if (size == 0) {
			head = new Node<E>(e, null);
			tail=head;
		}
		else
			head = new Node<E>(e, head);
		size++;
	}

	public void addLast(E e) {
		if (isEmpty())
			addFirst(e);
		else {
			Node<E> tmp = new Node<E>(e,null);
			tail.setNext(tmp);
			tail=tmp;
			size++;
		}
	}
	public E deleteFirst() {
		if (isEmpty()) return null;
		
		E e = head.getElement();
		head = head.getNext();
		size--;
		return e;
	}

}

class Node<E> {
	private E element;
	private Node<E> next;

	public Node(E e, Node<E> n) { // ������
		element = e;
		next = n;
	}
	public void setElement(E e) {
		element = e;
	}

	public void setNext(Node<E> n) {
		next = n;
	}

	public E getElement() {
		return element;
	}

	public Node<E> getNext() {
		return next;
	}

}
