
import java.util.*;
class  DocumentScore{
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

public class Ranking {
	static Index index;
	static Invertedindex inverted;
	static LinkedList <DocumentScore> rankedDocument;
	static LinkedList <Integer> documentid;
	static String text;
	public Ranking(Index m, Invertedindex i,String q) {
		index = m;
		inverted = i;
		rankedDocument=new LinkedList<DocumentScore>();
		documentid=new LinkedList<Integer>();
	  text=q;
		
	}
	public static boolean exist(LinkedList<Integer>l,int m ) {
		if(l.empty())
			return false;
		l.findFirst();
		while(!l.last()) {
			if (m==l.retrieve())
				return true;
			l.findNext();
		}
		if (m==l.retrieve())
			return true;
		return false;
		
	}
	public static int freq(Document d, String m) {
		int freq=0;
		LinkedList <String> words=d.words;
		if(words.empty())
			return 0;
		words.findFirst();
		while(!words.last()) {
			if(words.retrieve().equalsIgnoreCase(m))
				freq++;
			words.findNext();
	}
		if(words.retrieve().equalsIgnoreCase(m))
			freq++;
		return freq;
	}
	public static int AllWordFreq(Document d, String m) {
		if(m.length()==0)
			return 0;
		String []t=m.split(" ");
		int freq=0;
		for(int i=0;i<t.length;i++)
			freq+=freq(d,t[i].trim());
		return freq;
	}
	public static void RankQuery(String q) {
		
		
		LinkedList <Integer> s=new LinkedList<Integer>();
		if(q.length()==0)
			return;
		String []t=q.split(" ");
		boolean f=false;
		for(int i=0;i<t.length;i++) {
			// i have changed this methode SearchForWord()from void to boolean 
			f=inverted.SearchForWord(t[i].trim().toLowerCase());
			if(f) {
			
	            s=inverted.words.retrieve().indexs;
	            }
	            AddinList(s);
			
		}}
		public static void AddinList(LinkedList <Integer> s) {
			if(s.empty())
				return;
			s.findFirst();
			while(!s.last()) {
				boolean f=exist(documentid,s.retrieve());
				if(!f)
					documentid.insert(s.retrieve());
				s.findNext();
			}
			boolean f=exist(documentid,s.retrieve());
			if(!f)
				documentid.insert(s.retrieve());;
			
		}
		
		public void DocumentWithScore() {
			RankQuery(text);
			
			documentid.findFirst();
			while(!documentid.last()) {
				Document d=getDocByid(documentid.retrieve());
				int score=AllWordFreq(d,text);
				insertAndSort(new DocumentScore(documentid.retrieve(),score));
				documentid.findNext();
				}
			Document d=getDocByid(documentid.retrieve());
			int rank=AllWordFreq(d,text);
			insertAndSort(new DocumentScore(documentid.retrieve(),rank));
		}
		static void insertAndSort(DocumentScore s) {
		    if (rankedDocument.empty()) {
		        rankedDocument.insert(s);
		        return;
		    }
		    
		    rankedDocument.findFirst();
		    
		    while (!rankedDocument.last()) {
		        if (s.score > rankedDocument.retrieve().score) {
		            DocumentScore w = rankedDocument.retrieve();
		            rankedDocument.update(s);  
		            rankedDocument.insert(w);  
		            return;
		        }
		        rankedDocument.findNext();
		    }
		    

		    if (s.score > rankedDocument.retrieve().score) {
		        DocumentScore w = rankedDocument.retrieve();
		        rankedDocument.update(s);  
		        rankedDocument.insert(w);
		    }
		    else {
		    	
		    	rankedDocument.insert(s); 
		    }
		}
		public static Document getDocByid(int id) {
		    Document doc = index.searchDocumentById(id);
		    if (doc == null) {
		        System.out.println("there is no doc with this id:"+id);
		        return null;
		    }
		    return doc;
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
}
