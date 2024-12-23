public class BST<T> {
public BSTNode<T> root,current;

public boolean search(String key) {
	BSTNode<T> p=root;
	while(p!=null) {
		if(key.equals(p.key)) 
			return true;
		else if(key.compareToIgnoreCase(p.key)>0) 
			p=p.right;
		else 
			p=p.left;
	}
	return false;
}
public LinkedList<Integer> findkey(String key) {
	BSTNode<T> p=root;
	BSTNode<T> q=root;
	while(p!=null) {
		q=p;
		if(key.equals(p.key)) { 
			current=p;
			return ((LinkedList<Integer>)(current.data));
		}
		else if(key.compareToIgnoreCase(p.key)>0) 
			p=p.right;
		else 
			p=p.left;
	}
	current=q;
	return new LinkedList<Integer>();
}
public BSTWord findWord(String key) {
	BSTNode<T> p=root;
	BSTNode<T> q=root;
	while(p!=null) {
		q=p;
		if(key.equals(p.key)) { 
			current=p;
			return new BSTWord<T>(p.data, p.key);
		}
		else if(key.compareToIgnoreCase(p.key)>0) 
			p=p.right;
		else 
			p=p.left;
	}
	current=q;
	return null;
}

public boolean inseart(T data,String key) {
	if(root==null){ root=new BSTNode<T>(data,key); return false;}
	BSTNode<T> p=root;
	BSTNode<T> q=root;
	while(p!=null) {
		q=p;
		if(key.equals(p.key))  
			return false;
		
		else if(key.compareToIgnoreCase(p.key)>0) 
			p=p.right;
		else 
			p=p.left;
	}

	if(key.compareToIgnoreCase(q.key)>0) q.right=new BSTNode<T>(data,key);
	else q.left=new BSTNode<T>(data,key);
	return true;
}
private void inOrder(BSTNode<T> p) {
	if(p==null) return;
	LinkedList<Integer> data=(LinkedList<Integer>)p.data;
	inOrder(p.left);
	System.out.print(p.key+" [");
	data.findFirst();
	while(!data.last()){
		System.out.print(data.retrieve()+",");
		data.findNext();
	}
	System.out.println(data.retrieve()+ "]");
	inOrder(p.right);
}
public void inOrder() {
	inOrder(root);
}
public void swapdata(String key) {
BSTNode<T> p=root;
BSTNode<T> q=root;
	while(p!=null) {
		if(key.equals(p.key)) {
			T tmp=q.data;
			q.data=p.data;
			p.data=tmp;		
			break;
		}
		else {
			q=p;
			if(key.compareToIgnoreCase(p.key)>0) 
				p=p.right;
			else
				p=p.left;
		}
}
	
}

}
