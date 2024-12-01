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
        LinkedList<Integer> wordIndexs=invertedindexBST.bst.findkey(word);
        if(wordIndexs==null){
            System.out.println("'"+word+"'"+" is not exist in the documents.");
            return;
        }
        int score=0;            // Document 3: "The bright green color known as emerald green"
        wordIndexs.findFirst();
        while (!wordIndexs.last()) {
            score=invertedindexBST.freq(wordIndexs.retrieve(), word);
            DocumentScore documentScore=find_or_createDcoumentScore(wordIndexs.retrieve());
            documentScore.incress_score(score);
            if(is_inScorelLinkedList(wordIndexs.retrieve()))
                 removeFromScorelLinkedList(index.documents.retrieve().id);
            documentScore.insertInArrange(scorelLinkedList);
              // scorelLinkedList.insert(documentScore);
                wordIndexs.findNext(); 
        }
        score=invertedindexBST.freq(wordIndexs.retrieve(), word);
        DocumentScore documentScore=find_or_createDcoumentScore(wordIndexs.retrieve());
            documentScore.incress_score(score);
            if(is_inScorelLinkedList(wordIndexs.retrieve()))
                removeFromScorelLinkedList(index.documents.retrieve().id);
             documentScore.insertInArrange(scorelLinkedList);
                //scorelLinkedList.insert(documentScore);
               
    }
    display();
}
public void invertedindexRanking(String str){
    str=str.trim().toLowerCase();
    String words[]=str.split(" ");

    for(String word: words){// national flag
        if(word==""){
            System.out.println("Empty words are not acceptable.");
            return;
        }
        int score=0;
        Word w=invertedindex.findWord(word);
        if(w==null){
            System.out.println("'"+word+"'"+" is not exist in the documents.");
            return;
        }
        w.indexs.findFirst();
        while (!w.indexs.last()) {
            score=invertedindex.freq(w.indexs.retrieve(), word);
            DocumentScore documentScore=find_or_createDcoumentScore(w.indexs.retrieve());
            documentScore.incress_score(score);
            if(is_inScorelLinkedList(w.indexs.retrieve()))
                removeFromScorelLinkedList(index.documents.retrieve().id);
            documentScore.insertInArrange(scorelLinkedList);
            w.indexs.findNext();       
        }
        score=invertedindex.freq(w.indexs.retrieve(), word);
        DocumentScore documentScore=find_or_createDcoumentScore(w.indexs.retrieve());
        documentScore.incress_score(score);
            if(is_inScorelLinkedList(w.indexs.retrieve()))
                removeFromScorelLinkedList(index.documents.retrieve().id);
            documentScore.insertInArrange(scorelLinkedList);
    }
    display();
}

public void indexRanking(String str){// national flag
    str=str.trim().toLowerCase();
    String words[]=str.split(" ");

    for(String word: words){
        if(word.isEmpty()){
            System.out.println("Empty words are not acceptable.");
            return;
        }
        if(!index.isExist(word)){
            System.out.println("'"+word+"'"+" is not exist in the documents.");
            return;
        }
        int score=0;   
        /*
         Document 1: "The national flag of the Kingdom of Saudi Arabia color is green and white"
         Document 2: "The green color extends from the pole to the end of the flag"
         Document 3: "The bright green color known as emerald green"
         Document 4: "The flag of Saudi Arabia has an Arabic shahada and a sword in snow white"
         */
        index.documents.findFirst();
        while(!index.documents.last()){
            score=index.documents.retrieve().count_apprence(word);
            if(score==0){
               index.documents.findNext();
                continue;
                }
            DocumentScore documentScore=find_or_createDcoumentScore(index.documents.retrieve().id);
            documentScore.incress_score(score);
                 if(is_inScorelLinkedList(index.documents.retrieve().id))
                    removeFromScorelLinkedList(index.documents.retrieve().id);
                    documentScore.insertInArrange(scorelLinkedList) ;      
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
    display();  
    }
  public boolean is_inScorelLinkedList(int id){
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
 public DocumentScore find_or_createDcoumentScore(int id){
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
public void removeFromScorelLinkedList(int docID){
    if(!scorelLinkedList.empty()){
        scorelLinkedList.findFirst();                //     c
        while(!scorelLinkedList.last()){             // 5 3 1
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
public void display(){
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
