
public class RankingWithIndex {
	 LinkedList <DocumentScore> rankedDocument=new  LinkedList<DocumentScore> ();
	 Index x;
	 public RankingWithIndex (String g ,Index y){
		 x=y;
		 Rank( g ,x);
		 
	 }
	public void Rank(String g ,Index x) {
		int count=0;
		String t[]=g.split(" ");
		x.document.findFirst();
		while(!x.document.last()) {
			x.document.retrieve().words.findFirst();
			while(!x.document.retrieve().words.last()) {
				for (int i=0;i<t.length;i++) {
					if(x.document.retrieve().words.retrieve().equalsIgnoreCase(t[i]))
					count++;
					}
				
				x.document.retrieve().words.findNext();}
			for (int i=0;i<t.length;i++) {
				if(x.document.retrieve().words.retrieve().equalsIgnoreCase(t[i]))
				count++;
				}
				
			if(count!=0)
			rankedDocument.insert(new DocumentScore(x.document.retrieve().id,count));
			count=0;
			x.document.findNext();
			
		}
		
		while(!x.document.retrieve().words.last()) {
			for (int i=0;i<t.length;i++) {
				if(x.document.retrieve().words.retrieve().equalsIgnoreCase(t[i]))
				count++;
				}
			
			x.document.retrieve().words.findNext();
		for (int i=0;i<t.length;i++) {
			if(x.document.retrieve().words.retrieve().equalsIgnoreCase(t[i]))
			count++;
			}}
		if(count!=0)
		rankedDocument.insert(new DocumentScore(x.document.retrieve().id,count));
		count=0;
		
	}
	public void display() {
		if(rankedDocument.empty()) {
			System.out.print("empty");
			return;
		}
		System.out.printf("%-8s%-8s\n","docID","score");
		rankedDocument.findFirst();
		while(!rankedDocument.last()) { 
		rankedDocument.retrieve().display();
		
		rankedDocument.findNext();}
		rankedDocument.retrieve().display();
	}
	class DocumentScore {
	    int docID;
	    int score;

	    public DocumentScore(int docID, int score) {
	        this.docID = docID;
	        this.score = score;
	    }
	    public void  display() {
	    	System.out.printf("%-8s%-8s\n",docID,score);
	    }
}


	}
	
	
	

