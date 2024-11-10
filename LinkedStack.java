public class LinkedStack<T> implements Stack<T> {
	 Node<T> top;
	@Override
	public boolean empty() {
		return top==null;
	}

	@Override
	public boolean full() {
		return false;
	}

	@Override
	public void push(T data) {
		Node<T> p=new Node<T>(data);
		p.next=top;
		top=p;
	}

	@Override
	public T pop() {
		T data=top.data;
		top=top.next;
		
		return data;
	}
}
