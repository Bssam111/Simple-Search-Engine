public class Ranking{
Index index;
Invertedindex invertedindex;
InvertedindexBST invertedindexBST;
LinkedList <DocumentScore> scorelLinkedList;


public Ranking(Index index,Invertedindex invertedindex, InvertedindexBST invertedindexBST) {
    scorelLinkedList =new LinkedList<DocumentScore>();
    this.index = index;
    this.invertedindex = invertedindex;
    this.invertedindexBST = invertedindexBST;
}
public void createNewLinkedList(){
    scorelLinkedList =new LinkedList<DocumentScore>();
}
public void invertedindexBSTRanking(String str){ // green color
    str=str.trim().toLowerCase(); 
    String words[]=str.split(" ");
    
    for(String word: words){
        if(word.isEmpty()){
            System.out.println("Empty words are not acceptable.");
            return;
        }
        LinkedList<Integer> wordIndexs=invertedindexBST.bst.findkey(word);// w:n, a:log(n)
        if(wordIndexs==null){
            System.out.println("'"+word+"'"+" is not exist in the documents.");
            return;
        }
        int score=0;            
        wordIndexs.findFirst();
        while (!wordIndexs.last()) {    //m
            score=invertedindexBST.freq(wordIndexs.retrieve(), word);
            DocumentScore documentScore=find_or_createDcoumentScore(wordIndexs.retrieve()); // s
            documentScore.incress_score(score); // 1
            if(is_inScorelLinkedList(wordIndexs.retrieve()))// s
                 removeFromScorelLinkedList(index.documents.retrieve().id);//1
            documentScore.insertInArrange(scorelLinkedList);// s
              
                wordIndexs.findNext(); 
        }
        score=invertedindexBST.freq(wordIndexs.retrieve(), word);
        DocumentScore documentScore=find_or_createDcoumentScore(wordIndexs.retrieve());
            documentScore.incress_score(score);
            if(is_inScorelLinkedList(wordIndexs.retrieve()))
                removeFromScorelLinkedList(index.documents.retrieve().id);
             documentScore.insertInArrange(scorelLinkedList);
                
               
    }
    display();// s
    //worst: o(n * m) : m=wordIndexs & n =key in bst
    //avr: o(log(n) * m) : s=ScorelLinkedList & m=wordIndexs & n =key in bst
}
public void invertedindexRanking(String str){
    str=str.trim().toLowerCase();
    String words[]=str.split(" ");

    for(String word: words){// national flag
        if(word.isEmpty()){
            System.out.println("Empty words are not acceptable.");
            return;
        }
        int score=0;
        Word w=invertedindex.findWord(word);// avr: o(n/2)=o(n), worst=o(n)
        if(w==null){
            System.out.println("'"+word+"'"+" is not exist in the documents.");
            return;
        }
        w.indexs.findFirst();
        while (!w.indexs.last()) {// o(m)
            score=invertedindex.freq(w.indexs.retrieve(), word);
            DocumentScore documentScore=find_or_createDcoumentScore(w.indexs.retrieve());// o(s)
            documentScore.incress_score(score);
            if(is_inScorelLinkedList(w.indexs.retrieve()))//o(s)
                removeFromScorelLinkedList(index.documents.retrieve().id);//o(s)
            documentScore.insertInArrange(scorelLinkedList);//o(s)
            w.indexs.findNext();       
        }
        score=invertedindex.freq(w.indexs.retrieve(), word);
        DocumentScore documentScore=find_or_createDcoumentScore(w.indexs.retrieve());//o(s)
        documentScore.incress_score(score);
            if(is_inScorelLinkedList(w.indexs.retrieve()))//o(s)
                removeFromScorelLinkedList(index.documents.retrieve().id);//o(s)
            documentScore.insertInArrange(scorelLinkedList);//o(s)
    }
    display();// o(s)
    //worst: o(n * m) : m=wordIndexs & n = Worsds LinkedList(in invertedindex)
    //avr: o(n * m) : m=wordIndexs & n = Worsds LinkedList(in invertedindex)
}

public void indexRanking(String str){
    str=str.trim().toLowerCase();
    String words[]=str.split(" ");

    for(String word: words){
        if(word.isEmpty()){
            System.out.println("Empty words are not acceptable.");
            return;
        }
        if(!index.isExist(word)){//o(n*m)
            System.out.println("'"+word+"'"+" is not exist in the documents.");
            return;
        }
        int score=0;   
      
        index.documents.findFirst();
        while(!index.documents.last()){//o(n)
            score=index.documents.retrieve().count_apprence(word);
            if(score==0){
               index.documents.findNext();
                continue;
                }
            DocumentScore documentScore=find_or_createDcoumentScore(index.documents.retrieve().id);//o(s)
            documentScore.incress_score(score);
                 if(is_inScorelLinkedList(index.documents.retrieve().id))//o(s)
                    removeFromScorelLinkedList(index.documents.retrieve().id);//o(s)
                    documentScore.insertInArrange(scorelLinkedList);//o(s)      
            index.documents.findNext();
        }
        score=index.documents.retrieve().count_apprence(word);
        if(score==0) continue;
        DocumentScore documentScore=find_or_createDcoumentScore(index.documents.retrieve().id);     
        documentScore.incress_score(score);
        if(is_inScorelLinkedList(index.documents.retrieve().id))
            removeFromScorelLinkedList(index.documents.retrieve().id);
                documentScore.insertInArrange(scorelLinkedList)  ;     
    }
    display();//o(s)
    //worst: o(n * m ) : m=words & n = document LinkedList(in index)
    //avr: o(n * m ) : m=words & n = document LinkedList(in index)  
    }
  public boolean is_inScorelLinkedList(int id){//o(s)
     if(!scorelLinkedList.empty()){
            scorelLinkedList.findFirst();
            while (!scorelLinkedList.last()) {
                    if(scorelLinkedList.retrieve().docID==id)
                        return true;
                    scorelLinkedList.findNext();
            }
            if(scorelLinkedList.retrieve().docID==id)
                return true;
        }
    return false;
 }
 public DocumentScore find_or_createDcoumentScore(int id){ // o(s)
     if(!scorelLinkedList.empty()){
          scorelLinkedList.findFirst();
          while(!scorelLinkedList.last()){
              if(scorelLinkedList.retrieve().docID==id)
                   return scorelLinkedList.retrieve();
               scorelLinkedList.findNext();  
        }
        if(scorelLinkedList.retrieve().docID==id)
                return scorelLinkedList.retrieve();     
     }
     return new DocumentScore(id); 
}
public void removeFromScorelLinkedList(int docID){// o(s)
    if(!scorelLinkedList.empty()){
        scorelLinkedList.findFirst();                
        while(!scorelLinkedList.last()){             
            if(docID==scorelLinkedList.retrieve().docID)
                scorelLinkedList.remove();

            if(scorelLinkedList.last()) 
                return;

            scorelLinkedList.findNext();    
        }
        if(docID==scorelLinkedList.retrieve().docID)
        scorelLinkedList.remove();
    }
}
public void display(){//o(s)
    if(!scorelLinkedList.empty()){
        System.out.println("DocID    Score");
        scorelLinkedList.findFirst();
        while(!scorelLinkedList.last()){
            scorelLinkedList.retrieve().display();
            scorelLinkedList.findNext();
        }
        scorelLinkedList.retrieve().display();
    }
    else System.out.println("empty");
}       

}
