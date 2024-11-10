
public class LinkedList<T> implements List<T> {
Node<T> head;
Node<T> current;


	@Override
	public boolean empty() {
		return head==null;
	}

	@Override
	public boolean full() {
		return false;
	}

	@Override
	public boolean last() {
		return current.next==null;
	}

	@Override
	public void insert(T data) {
		Node<T> p=new Node<T>(data);
		if(empty()) {
			head=p;
			current=head;
		}
		else {
			p.next=current.next;
			current.next=p;
			current=p;
			
		}
		
	}

	@Override
	public void update(T data) {
		current.data=data;
	}

	@Override
	public T retrieve() {
		return current.data;
	}

	@Override
	public void remove() {
	if(head==current)
		head=current=head.next;

		else {
		Node<T> prev=head;
		while(prev.next!=current){
			prev=prev.next;
		}
		prev.next=current.next;
		if(last()) {
			current=head;
		}
		else {
		current=current.next;
		}
		}
	}

	@Override
	public void findFirst() {
		current=head;
		
	}

	@Override
	public void findNext() {
		current=current.next;
		
	}
	public T mfe() {
		Node<T> tmp=head;
		int max=0;
		T maxel=null;
		int size=0;
		Node<T> index=head;
		while(index!=null) {
			int count=0;
			tmp=index;
			while(tmp!=null) {
				if(tmp.data.equals(index.data))
					count++;
				tmp=tmp.next;
			}
			if(count>max) {
				max=count;
				maxel=index.data;
			}
			index=index.next;
		}
		return maxel;
		
	}

	

	
}
