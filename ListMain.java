

public class ListMain {

	public static void main(String[] args) {
		LinkedList<Integer> l1=new LinkedList<Integer>();
		LinkedList<Integer> l2=new LinkedList<Integer>();
		l2.insert(1);l2.insert(3);l2.insert(5);
		l1.insert(1);l1.insert(6);l1.insert(7);
	 	LinkedList<Integer> l3=concat(l1, l2, 2);
		display(l3);
	}
	public static<T> void display(List<T> l) {
		if(l.empty()) {System.out.println("empty"); return;}
		l.findFirst();
		while(!l.last()) {
			System.out.print(l.retrieve()+",");
			l.findNext();
		}
		System.out.println(l.retrieve());
		
	}
	
	
	public static<T> T removelast(List<T> l) {
		if(l.empty()) return null;
		while(!l.last()) {
			l.findNext();
		}
		T data=l.retrieve();
		l.remove();
		return data; 
	}
	public static<T> int length(List<T> l) {
		if(l.empty()) return 0;
		l.findFirst();int count=0;
		
		while(!l.last()) {
			l.findNext();
			count++;
		}
		return ++count;
		
	}
	public static<T> boolean search(List<T> l,T data) {
		if(l.empty()) return false;
		l.findFirst();
		while(!l.last()) {
			if(l.retrieve().equals(data)) return true;
			l.findNext();
		}
		if(l.retrieve().equals(data)) return true;
		return false;
	}
	
	public static<T> T getindex(List<T> l,int index) {
		if(l.empty()) return null;
		l.findFirst();
		for(int i=0;i<index;i++) {
			l.findNext();
		}
		return l.retrieve();
	}
	public static<T> void inseartafterindex(List<T> l,int index,T data) {
		//if(l.empty()) return;
		l.findFirst();
		for(int i=0;i<index;i++) {
			l.findNext();
		}
		l.insert(data);
	}
	public static<T> void insertinfirst(List<T> l,T data){
		if(l.empty()) {l.insert(data); return;}
		
		List<T> tmp=new LinkedList<T>();
		l.findFirst();
		while(!l.last()) {
			tmp.insert(l.retrieve());
			l.remove();
		}
		tmp.insert(l.retrieve()); l.remove();
		l.insert(data);
		tmp.findFirst();
		while(!tmp.last()) {
			l.insert(tmp.retrieve());
			tmp.remove();
		}
		l.insert(tmp.retrieve());
		
	}
	// public static<T> void insertinfirstQ(List<T> l,T data){
	// 	if(l.empty()) {l.insert(data); return;}
		
	// 	Queue<T> tmp=new LinkedQueue<T>();
	// 	l.findFirst();
	// 	while(!l.last()) {
	// 		tmp.enqueue(l.retrieve());
	// 		l.remove();
	// 	}
	// 	tmp.enqueue(l.retrieve()); l.remove();
	// 	l.insert(data);
	// 	while(tmp.length()>0) {
	// 		l.insert(tmp.serve());
	// 	}
	// }
	public static<T> void movefirtoccur(List<T> l,T data) {
		if(l.empty()) return;
		T d=null;
	
		l.findFirst();
		while(!l.last()) {
			if(l.retrieve().equals(data)) {
				d=l.retrieve();
				l.remove();
				break;
			}
			l.findNext();
		}
		if(l.retrieve().equals(data)) return;
		
		while(!l.last()) {
			l.findNext();
		}
		l.insert(d);
		
	}
	public static<T> void clear(List<T> l) {
		if(l.empty()) return;
		l.findFirst();
		while(!l.last()) {
			l.remove();
			if(l.last()) break;
		}
		l.remove();
		// i=1
	}// l1: c1 2 
	//  l2: 10 12
	//  tmp: 3 4
	public static<T> void inseartAll(List<T> l1,List<T> l2,int i) {
		if(l1.empty() && l2.empty()) return;
		
		l1.findFirst();
		for(int k=0;k<=i;k++) {
			l1.findNext();
		}
		List<T> tmp=new LinkedList<T>();
		while(!l1.last()) {
			tmp.insert(l1.retrieve());
			l1.remove();
			if(l1.last()) break;
			
			l1.findNext();
		}
		tmp.insert(l1.retrieve());
		l1.remove();
		l2.findFirst();
		while(!l1.last()) {l1.findNext();}
		while(!l2.last()) {
			l1.insert(l2.retrieve());
			l2.findNext();
		}
		l1.insert(l2.retrieve()); tmp.findFirst();
		while(!tmp.last()) {
			l1.insert(tmp.retrieve());
			tmp.findNext();
		}
		l1.insert(tmp.retrieve());
	}
//	public static<T> void insertList(List<T> l1,List<T> l2,int i) {
//		if(l1.empty() || l2.empty()) return;
//		
//		l1.findFirst();
//		
//		for(int k=0;k<i;k++) {
//			l1.findNext();
//		}
//		if(l1.last()){
//			l2.findFirst();
//			while(!l2.last()) {
//				l1.insert(l2.retrieve());
//				l2.findNext();
//			}
//			l1.insert(l2.retrieve());
//			return;
//			}
//		l1.findNext();
//		l2.findFirst();
//		while(!l2.last()) {
//			T x=l1.retrieve();
//			l1.update(l2.retrieve());
//			l1.insert(x);
//			l2.findNext();
//		}
//		T x=l1.retrieve();
//		l1.update(l2.retrieve());
//		l1.insert(x);
//	}
	// public static<T> void insertList(List<T> l1,List<T> l2,int i) {
	// 	if(l1.empty() || l2.empty()) return;
		
	// 	l1.findFirst();
		
	// 	for(int k=0;k<i;k++) {
	// 		l1.findNext();
	// 	}
	// 	l2.findFirst();
	// 	while(!l2.last()) {
	// 		l1.insert(l2.retrieve());
	// 		l2.findNext();
	// 	}
	// 	l1.insert(l2.retrieve());
	// }
	public static<T> LinkedList<Integer> concat(LinkedList<Integer> l1,LinkedList<Integer> l2, int i ){
		if(l1.empty() || l2.empty()) return null;
		l1.findFirst();l2.findFirst();
		
		LinkedList<Integer> list=new LinkedList<Integer>();
		for(int k=0;k<=i;k++) {
			list.insert(l1.retrieve());
			l1.findNext();
			if(!l2.empty())
			l2.remove();
		}
		if(!l2.empty()) {
		while(!l2.last()) {
			list.insert(l2.retrieve());
			l2.findNext();
		}
		list.insert(l2.retrieve());
		}
		
		
		return list;
	}
	public static<T> void circualrLeftshift(List<T> l,int i  ) {
		for(int k=0;k<i;k++) {
			l.findFirst();
			T x=l.retrieve();
			l.remove();
			while(!l.last()) {
				l.findNext();
			}
			l.insert(x);
		}
	}
	
}
